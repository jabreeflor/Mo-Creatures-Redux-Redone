#!/usr/bin/env bash
# Boots a fresh dev server with the mod, runs tools/smoke_test.py against it, and reports errors.
cd "$(dirname "$0")/.."
mkdir -p run
[ -f run/eula.txt ] || echo "eula=true" > run/eula.txt
if ! grep -q '^enable-rcon=true' run/server.properties 2>/dev/null; then
  cat > run/server.properties <<PROPS
enable-rcon=true
rcon.port=25599
rcon.password=$(python -c "import secrets;print(secrets.token_hex(12))")
online-mode=false
level-name=smoketest
spawn-protection=0
view-distance=6
simulation-distance=6
PROPS
fi
rm -rf run/smoketest run/logs run/crash-reports
./gradlew runServer --console=plain > build/server-run.txt 2>&1 &
until grep -qE 'Done \(|Crash report saved|BUILD FAILED' build/server-run.txt 2>/dev/null; do sleep 2; done
if grep -q 'Done (' build/server-run.txt; then
  timeout 300 python tools/smoke_test.py
fi
for i in $(seq 1 60); do grep -qE 'BUILD (FAILED|SUCCESSFUL)' build/server-run.txt && break; sleep 2; done
echo "---- errors in server log ----"
grep -nE '/(ERROR|FATAL)\]|^Caused by|Exception:' build/server-run.txt | grep -vE 'HkeyPerformance|SystemReport|Win32Exception|oshi' | cut -c1-300 | head -40
ls run/crash-reports 2>/dev/null
