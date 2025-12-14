package com.photosynthesisstudio.refactorskyblock;

import net.neoforged.neoforge.common.ModConfigSpec;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class RefactorSkyblockConfig {
    public static final ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder SERVER = new ModConfigSpec.Builder();

    private static final String NAME_KEY = MODID + ".configuration.%s";

    public static final ModConfigSpec.BooleanValue CAN_PLAY_MODE = COMMON
            .translation(NAME_KEY.formatted("canPlayMode"))
            .define("canPlayMode", false);

    public static final ModConfigSpec.IntValue INITIAL_FOOD_NUMBER = COMMON
            .comment("If a new player logs into the world, give him the amount of food.")
            .translation(NAME_KEY.formatted("initialFoodNumber"))
            .defineInRange("initialFoodNumber", 64, 0, Integer.MAX_VALUE);
}
