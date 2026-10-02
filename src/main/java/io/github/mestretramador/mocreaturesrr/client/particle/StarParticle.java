package io.github.mestretramador.mocreaturesrr.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

public class StarParticle extends SingleQuadParticle {

    protected StarParticle(ClientLevel level, double x, double y, double z,
            double dx, double dy, double dz, TextureAtlasSprite sprite,
            float r, float g, float b) {
        super(level, x, y, z, sprite);
        this.gravity = 0.0F;
        this.lifetime = 12 + this.random.nextInt(6);
        this.quadSize = 0.15F;
        this.xd = dx;
        this.yd = dy;
        this.zd = dz;
        this.rCol = r;
        this.gCol = g;
        this.bCol = b;
    }

    @Override
    public SingleQuadParticle.Layer getLayer() {
        return SingleQuadParticle.Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;
        private final float r, g, b;

        public Provider(SpriteSet sprites) {
            this(sprites, 1.0F, 0.9F, 0.4F);
        }

        public Provider(SpriteSet sprites, float r, float g, float b) {
            this.sprites = sprites;
            this.r = r;
            this.g = g;
            this.b = b;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z,
                double dx, double dy, double dz, RandomSource random) {
            return new StarParticle(level, x, y, z, dx, dy, dz, sprites.get(random), r, g, b);
        }
    }
}