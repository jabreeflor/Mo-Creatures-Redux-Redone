package io.github.mestretramador.mocreaturesrr.entity.golem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * The 23 block "cubes" a Big Golem is made of (original: the golemCubes byte array). Server-side it
 * keeps the exact block of every cube plus whether it may be given back to the world; clients only
 * receive the texture index of each cube, through {@link #toSyncString()}.
 */
public final class GolemBody {

    public static final int SLOT_COUNT = 23;
    /** Original: slot 4 is the precious back cube (see GolemCore). */
    public static final int CORE_SLOT = 4;
    public static final int EMPTY_TEXTURE = -1;

    // Slot layout, as in the original model.
    public static final int CHEST_FIRST = 0;
    public static final int CHEST_LAST = 3;
    public static final int LEFT_SHOULDER = 9;
    public static final int LEFT_ARM = 10;
    public static final int LEFT_HAND = 11;
    public static final int RIGHT_SHOULDER = 12;
    public static final int RIGHT_ARM = 13;
    public static final int RIGHT_HAND = 14;
    public static final int LEFT_THIGH = 15;
    public static final int LEFT_KNEE = 16;
    public static final int LEFT_FOOT = 17;
    public static final int RIGHT_THIGH = 18;
    public static final int RIGHT_KNEE = 19;
    public static final int RIGHT_FOOT = 20;

    private static final String TAG_CUBES = "Cubes";
    private static final String TAG_SLOT = "Slot";
    private static final String TAG_BLOCK = "Block";
    private static final String TAG_RETURNABLE = "Returnable";
    /** Character standing for texture 0 in the sync string; EMPTY_TEXTURE (-1) is the one before it. */
    private static final char SYNC_CHAR_BASE = 'B';

    /**
     * One cube of the body.
     *
     * @param returnable true when the block was really taken out of the world (or is the core), so it
     *                   must be given back as an item; false for cheap blocks conjured with mobGriefing off
     */
    public record Cube(BlockState state, boolean returnable) {
    }

    private final Cube[] cubes = new Cube[SLOT_COUNT];

    @Nullable
    public Cube get(int slot) {
        return this.cubes[slot];
    }

    public void set(int slot, @Nullable Cube cube) {
        this.cubes[slot] = cube;
    }

    public boolean isEmpty(int slot) {
        return this.cubes[slot] == null;
    }

    public boolean isComplete() {
        for (Cube cube : this.cubes) {
            if (cube == null) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> emptySlots() {
        List<Integer> slots = new ArrayList<>();
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            if (this.cubes[slot] == null) {
                slots.add(slot);
            }
        }
        return slots;
    }

    public List<Integer> usedSlots() {
        List<Integer> slots = new ArrayList<>();
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            if (this.cubes[slot] != null) {
                slots.add(slot);
            }
        }
        return slots;
    }

    // ---------------------------------------------------------------------
    // Client sync — texture indices only
    // ---------------------------------------------------------------------

    /**
     * Encodes the per-slot texture indices as a short string (one character per slot), since 26.x has no
     * synced-data serializer for compound tags or byte arrays any more.
     */
    public String toSyncString() {
        StringBuilder builder = new StringBuilder(SLOT_COUNT);
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            Cube cube = this.cubes[slot];
            int texture = cube == null ? EMPTY_TEXTURE : GolemCubeTextures.textureOf(cube.state());
            builder.append((char) (SYNC_CHAR_BASE + texture));
        }
        return builder.toString();
    }

    /** Reads the per-slot texture indices out of a sync string; always returns SLOT_COUNT entries. */
    public static byte[] texturesFromSyncString(String sync) {
        byte[] textures = new byte[SLOT_COUNT];
        if (sync.length() == SLOT_COUNT) {
            for (int slot = 0; slot < SLOT_COUNT; slot++) {
                textures[slot] = (byte) (sync.charAt(slot) - SYNC_CHAR_BASE);
            }
            return textures;
        }
        Arrays.fill(textures, (byte) EMPTY_TEXTURE);
        return textures;
    }

    // ---------------------------------------------------------------------
    // Persistence — the exact blocks
    // ---------------------------------------------------------------------

    public void save(ValueOutput output) {
        ValueOutput.ValueOutputList list = output.childrenList(TAG_CUBES);
        for (int slot = 0; slot < SLOT_COUNT; slot++) {
            Cube cube = this.cubes[slot];
            if (cube != null) {
                ValueOutput cubeTag = list.addChild();
                cubeTag.putByte(TAG_SLOT, (byte) slot);
                cubeTag.store(TAG_BLOCK, BlockState.CODEC, cube.state());
                cubeTag.putBoolean(TAG_RETURNABLE, cube.returnable());
            }
        }
    }

    public void load(ValueInput input) {
        Arrays.fill(this.cubes, null);
        for (ValueInput cubeTag : input.childrenListOrEmpty(TAG_CUBES)) {
            int slot = cubeTag.getByteOr(TAG_SLOT, (byte) -1);
            BlockState state = cubeTag.read(TAG_BLOCK, BlockState.CODEC).orElse(Blocks.AIR.defaultBlockState());
            if (slot >= 0 && slot < SLOT_COUNT && !state.isAir()) {
                this.cubes[slot] = new Cube(state, cubeTag.getBooleanOr(TAG_RETURNABLE, false));
            }
        }
    }
}
