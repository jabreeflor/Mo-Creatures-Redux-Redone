"""Server smoke test: waits for the dev server (./gradlew runServer) to finish starting, then summons
every mocreaturesrr entity type over RCON, lets them tick, and reports what happened.

Usage: python tools/smoke_test.py            (reads RCON settings from run/server.properties)
"""
import os
import socket
import struct
import sys
import time

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PROPS = os.path.join(ROOT, 'run', 'server.properties')
LOG = os.path.join(ROOT, 'run', 'logs', 'latest.log')


def props():
    out = {}
    for line in open(PROPS, encoding='utf-8'):
        if '=' in line and not line.startswith('#'):
            k, v = line.strip().split('=', 1)
            out[k] = v
    return out


class Rcon:
    def __init__(self, port, password):
        self.s = socket.create_connection(('127.0.0.1', port), timeout=30)
        self.id = 0
        if self._send(3, password) == -1:
            raise RuntimeError('RCON auth failed')

    def _packet(self):
        raw = b''
        while len(raw) < 4:
            chunk = self.s.recv(4 - len(raw))
            if not chunk:
                raise ConnectionError('RCON connection closed (server stopped or crashed)')
            raw += chunk
        (length,) = struct.unpack('<i', raw)
        data = b''
        while len(data) < length:
            chunk = self.s.recv(length - len(data))
            if not chunk:
                raise ConnectionError('RCON connection closed (server stopped or crashed)')
            data += chunk
        rid, _type = struct.unpack('<ii', data[:8])
        return rid, data[8:-2].decode('utf-8', 'replace')

    def _send(self, kind, body):
        self.id += 1
        payload = struct.pack('<ii', self.id, kind) + body.encode('utf-8') + b'\0\0'
        self.s.sendall(struct.pack('<i', len(payload)) + payload)
        rid, text = self._packet()
        self.last = text
        return rid

    def cmd(self, command):
        self._send(2, command)
        return self.last


def entity_ids():
    import re
    src = open(os.path.join(ROOT, 'src/main/java/io/github/mestretramador/mocreaturesrr/init/ModEntities.java'),
               encoding='utf-8').read()
    return sorted(set(re.findall(r'ENTITY_TYPES\.register\(\s*"([a-z0-9_]+)"', src)))


def main():
    p = props()
    deadline = time.time() + 600
    rcon = None
    while time.time() < deadline:
        try:
            rcon = Rcon(int(p['rcon.port']), p['rcon.password'])
            break
        except OSError:
            time.sleep(3)
    if rcon is None:
        print('SERVER DID NOT START (no RCON within 10 min)')
        return 2

    print(rcon.cmd('forceload add -32 -32 32 32'))
    print(rcon.cmd('time set day'))
    print(rcon.cmd('gamerule spawn_mobs false'))
    ok, bad = [], []
    for i, eid in enumerate(entity_ids()):
        x, z = (i % 10) * 6 - 30, (i // 10) * 6 - 30
        res = rcon.cmd(f'execute positioned {x} 0 {z} positioned over motion_blocking_no_leaves run summon mocreaturesrr:{eid} ~ ~ ~')
        (ok if 'Summoned' in res else bad).append((eid, res.strip()))
    print(f'summoned {len(ok)} / {len(ok) + len(bad)} entity types')
    for eid, res in bad:
        print(f'  FAILED {eid}: {res}')

    time.sleep(20)  # let them fall, tick AI, etc.
    gone = []
    for eid, _ in ok:
        res = rcon.cmd(f'execute if entity @e[type=mocreaturesrr:{eid}]')
        if 'passed' not in res:
            gone.append(eid)
    print(f'still present after 20s: {len(ok) - len(gone)} / {len(ok)}')
    if gone:
        print('  not present (despawned, died, or hatched/converted):', ', '.join(gone))
    print(rcon.cmd('save-all flush'))
    try:
        rcon.cmd('stop')
    except OSError:
        pass
    return 0 if not bad else 1


if __name__ == '__main__':
    sys.exit(main())
