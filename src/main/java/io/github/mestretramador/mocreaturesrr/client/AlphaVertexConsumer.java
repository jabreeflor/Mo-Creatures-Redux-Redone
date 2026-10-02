package io.github.mestretramador.mocreaturesrr.client;

import com.mojang.blaze3d.vertex.VertexConsumer;

public class AlphaVertexConsumer implements VertexConsumer {
    private final VertexConsumer delegate;
    private final int alpha; // 0-255
    private final int tintR;
    private final int tintG;
    private final int tintB;

    /** Original behaviour: fully opaque, no colour tint at all. */
    public AlphaVertexConsumer(VertexConsumer delegate, int alpha) {
        this(delegate, alpha, 255, 255, 255);
    }

    /** Same alpha-only behaviour, plus a colour tint (0-255 each) multiplied onto every vertex. */
    public AlphaVertexConsumer(VertexConsumer delegate, int alpha, int tintR, int tintG, int tintB) {
        this.delegate = delegate;
        this.alpha = alpha;
        this.tintR = tintR;
        this.tintG = tintG;
        this.tintB = tintB;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        delegate.addVertex(x, y, z);
        return this;
    }

    @Override
    public VertexConsumer setColor(int r, int g, int b, int a) {
        delegate.setColor(r * this.tintR / 255, g * this.tintG / 255, b * this.tintB / 255, this.alpha);
        return this;
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        delegate.setUv(u, v);
        return this;
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        delegate.setUv1(u, v);
        return this;
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        delegate.setUv2(u, v);
        return this;
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        delegate.setNormal(x, y, z);
        return this;
    }

    @Override
    public VertexConsumer setColor(int color) {
        // Packed ARGB: route through the per-channel path so the tint/alpha still apply.
        return this.setColor((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, (color >>> 24) & 0xFF);
    }

    @Override
    public VertexConsumer setUv3(float u, float v) {
        delegate.setUv3(u, v);
        return this;
    }

    @Override
    public VertexConsumer setLineWidth(float width) {
        delegate.setLineWidth(width);
        return this;
    }
}
