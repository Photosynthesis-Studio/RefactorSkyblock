package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.init.tags.ModBlockTags;
import com.photosynthesisstudio.refactorskyblock.init.tags.ModItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public record ModToolMaterial(TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float attackDamageBonus, int enchantmentValue, TagKey<Item> repairItems) {
    public static final ToolMaterial SIMPLE;

    static {
        SIMPLE = new ToolMaterial(
                ModBlockTags.INCORRECT_FOR_SIMPLE_TOOL,
                59,
                2.0F,
                0.0F,
                15,
                ModItemTags.SIMPLE_TOOL_MATERIALS);
    }
}
