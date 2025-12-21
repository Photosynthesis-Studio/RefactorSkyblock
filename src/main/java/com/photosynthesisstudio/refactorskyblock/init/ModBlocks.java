package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.block.UnfinishedCampfireBlock;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCK_REG = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> UNFINISHED_CAMPFIRE = BLOCK_REG.registerBlock("unfinished_campfire", UnfinishedCampfireBlock::new);
}
