package io.github.mestretramador.mocreaturesrr.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class UndeadDecayParticle extends SingleQuadParticle {

    protected UndeadDecayParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, sprite);
        this.gravity = 0.03F;
        this.lifetime = 20 + this.random.nextInt(10);
        this.quadSize = 0.15F;
        this.xd = 0.0D;
        this.yd = -0.05D;
        this.zd = 0.0D;
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                double dx, double dy, double dz, RandomSource random) {
            return new UndeadDecayParticle(level, x, y, z, sprites.get(random));
        }
    }
}