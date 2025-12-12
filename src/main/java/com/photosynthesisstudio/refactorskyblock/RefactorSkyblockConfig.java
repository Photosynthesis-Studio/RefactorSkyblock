package com.photosynthesisstudio.refactorskyblock;

import net.neoforged.neoforge.common.ModConfigSpec;

public class RefactorSkyblockConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    static final ModConfigSpec SPEC = BUILDER.build();

    public static final ModConfigSpec.IntValue TEST_NUMBER = BUILDER
            .comment("A test number")
            .defineInRange("testNumber", 33, 0, Integer.MAX_VALUE);
}
