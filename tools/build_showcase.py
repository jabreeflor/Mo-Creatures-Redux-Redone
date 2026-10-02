"""Builds a showcase world on the running dev server (via RCON): a fenced sky platform at y=150 with a
water pool, and one persistent specimen of every mocreaturesrr creature. Copy run/smoketest into
run/saves afterwards to open it in the client."""
import sys, time, os
sys.path.insert(0, os.path.dirname(os.path.abspath(__file__)))
import smoke_test as st

AQUATIC = {'moc_bass', 'moc_cod', 'moc_salmon', 'moc_small_fish', 'moc_medium_fish', 'moc_fishy', 'moc_shark',
           'moc_dolphin', 'moc_jellyfish', 'moc_manta_ray', 'moc_stingray'}
SKIP = {'moc_summoned_rock', 'moc_throwable_rock'}

p = st.props(); r = st.Rcon(int(p['rcon.port']), p['rcon.password'])
for c in ['forceload add -48 -48 48 48', 'time set day', 'weather clear', 'gamerule spawn_mobs false',
          'gamerule advance_time false', 'gamerule advance_weather false',
          'fill -30 149 -30 30 149 30 grass_block', 'fill -30 150 -30 30 160 30 air',
          'fill -31 150 -31 31 152 31 barrier hollow', 'fill -30 150 -30 30 152 30 air',
          'fill 6 140 -30 30 149 -6 glass', 'fill 7 141 -29 29 149 -7 water',
          'setworldspawn 0 150 0']:
    print(c, '->', r.cmd(c).strip()[:80])
land = [e for e in st.entity_ids() if e not in AQUATIC and e not in SKIP]
for i, e in enumerate(land):
    x = -26 + (i % 9) * 6
    z = -2 + (i // 9) * 5 if i // 9 < 7 else 0
    res = r.cmd(f'summon mocreaturesrr:{e} {x} 151 {z} {{PersistenceRequired:1b}}')
    print(e, res.strip()[:60])
for i, e in enumerate(sorted(AQUATIC)):
    x = 9 + (i % 4) * 5; z = -27 + (i // 4) * 7
    res = r.cmd(f'summon mocreaturesrr:{e} {x} 145 {z} {{PersistenceRequired:1b}}')
    print(e, res.strip()[:60])
time.sleep(5)
print(r.cmd('save-all flush'))
r.cmd('stop')
