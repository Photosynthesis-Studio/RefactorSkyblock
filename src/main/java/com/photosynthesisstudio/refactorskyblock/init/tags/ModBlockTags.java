package com.photosynthesisstudio.refactorskyblock.init.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    public static final TagKey<Block> INCORRECT_FOR_SIMPLE_TOOL = create("incorrect_for_simple_tool");

    private static TagKey<Block> create(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.withDefaultNamespace(name));
    }

    public static TagKey<Block> create(Identifier name) {
        return TagKey.create(Registries.BLOCK, name);
    }
}
