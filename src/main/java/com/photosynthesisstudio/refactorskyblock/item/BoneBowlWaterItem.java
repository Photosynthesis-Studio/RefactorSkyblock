package com.photosynthesisstudio.refactorskyblock.item;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import com.photosynthesisstudio.refactorskyblock.init.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;

public class BoneBowlWaterItem extends Item {
    public BoneBowlWaterItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        Block block = level.getBlockState(blockPos).getBlock();
        Player player = context.getPlayer();

        if (player == null) {
            return InteractionResult.PASS;
        }

        if (block == Blocks.DIRT) {
            // 客户端?
            if (!level.isClientSide()) {
                ItemStack heldStack = player.getMainHandItem();
                // 不是创造 就减少物品
                if (!player.isCreative()) {
                    if (heldStack.isEmpty()) {
                        return InteractionResult.PASS;
                    }
                    heldStack.shrink(1);
                    // 如果减少后堆叠为空，则设置主手为空
                    if (heldStack.isEmpty()) {
                        player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    }
                }

                level.playSound(null, blockPos, ModSoundEvents.BONE_BOWL_EMPTY.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
                level.gameEvent(null, GameEvent.FLUID_PLACE, blockPos);
                level.setBlockAndUpdate(blockPos, Blocks.MUD.defaultBlockState());

                if (!level.isClientSide()) {
                    ServerLevel serverlevel = (ServerLevel)level;

                    for (int i = 0; i < 5; i++) {
                        serverlevel.sendParticles(
                                ParticleTypes.SPLASH,
                                blockPos.getX() + level.random.nextDouble(),
                                blockPos.getY() + 1,
                                blockPos.getZ() + level.random.nextDouble(),
                                1,
                                0.0,
                                0.0,
                                0.0,
                                1.0
                        );
                    }
                }

                // 给予骨碗
                ItemStack boneBowl = new ItemStack(ModItems.BONE_BOWL.get());
                if (!player.getInventory().add(boneBowl)) {
                    // 如果背包满了，则掉落在世界中
                    player.drop(boneBowl, false);
                } else {
                    player.addItem(boneBowl);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
