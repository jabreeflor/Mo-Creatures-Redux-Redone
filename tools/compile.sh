#!/usr/bin/env bash
# Compiles the mod and prints javac errors, optionally filtered to paths matching $1 (an awk regex).
# Serialized with a lock directory so parallel callers do not collide. A caller that waited for the
# lock reuses the result of a compile that started after its request instead of compiling again.
cd "$(dirname "$0")/.."
mkdir -p build
REQ=$(date +%s%N)
LOCK=.compile.lock
until mkdir "$LOCK" 2>/dev/null; do sleep 2; done
trap 'rmdir "$LOCK" 2>/dev/null' EXIT
STARTED=$(cat build/compile-started 2>/dev/null || echo 0)
if [ "$STARTED" -lt "$REQ" ]; then
  date +%s%N > build/compile-started.tmp
  ./gradlew compileJava --console=plain -q > build/compile-log.txt 2>&1
  cp build/compile-log.txt build/compile-log-last.txt
  mv build/compile-started.tmp build/compile-started
fi
rmdir "$LOCK"; trap - EXIT
TOTAL=$(grep -c ': error:' build/compile-log-last.txt)
echo "TOTAL ERRORS (whole project): $TOTAL"
if [ "$TOTAL" = "0" ] && ! grep -q 'BUILD FAILED\|FAILURE' build/compile-log-last.txt; then
  echo "COMPILE OK"
fi
if [ -n "$1" ]; then
  grep -E ': error:' -A3 build/compile-log-last.txt | tr '\\' '/' \
    | awk -v pat="$1" 'BEGIN{p=0} /: error:/{p = ($0 ~ pat)} p' \
    | sed -E 's|^.*/mocreaturesrr/|mocreaturesrr/|'
fi
