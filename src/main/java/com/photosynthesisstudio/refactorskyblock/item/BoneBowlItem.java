package com.photosynthesisstudio.refactorskyblock.item;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import com.photosynthesisstudio.refactorskyblock.init.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BoneBowlItem extends Item {
    public BoneBowlItem(Properties properties) {
        super(properties.stacksTo(16));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Player player = context.getPlayer();
        InteractionHand hand = context.getHand();

        if (player == null) {
            return InteractionResult.PASS;
        }

        // 检查方块是否为水或包含水
        if (blockState.getFluidState().is(FluidTags.WATER) ||
                blockState.getBlock() == Blocks.WATER) {
            level.playSound(null, blockPos, ModSoundEvents.BONE_BOWL_FILL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PICKUP, blockPos);
            return fillBowlWithWater(context, level, player, hand);
        }

        return InteractionResult.PASS;
    }

    private InteractionResult fillBowlWithWater(UseOnContext context, Level level, Player player, InteractionHand hand) {
        // 客户端?
        if (!level.isClientSide()) {
            ItemStack heldStack = player.getItemInHand(hand);

            // 不是创造 就减少物品
            if (!player.isCreative()) {
                if (heldStack.isEmpty()) {
                    return InteractionResult.PASS;
                }
                heldStack.shrink(1);
                // 如果减少后堆叠为空，则设置手为空
                if (heldStack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }

            // 给予装水骨碗
            ItemStack waterBoneBowl = new ItemStack(ModItems.BONE_BOWL_WATER.get());
            if (!player.getInventory().add(waterBoneBowl)) {
                // 如果背包满了，则掉落在世界中
                player.drop(waterBoneBowl, false);
            }

            level.playSound(null, context.getClickedPos(), ModSoundEvents.BONE_BOWL_FILL.value(), SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.FLUID_PICKUP, context.getClickedPos());
        }
        return InteractionResult.SUCCESS;
    }

    // 右键空气检测水
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        // 进行射线追踪检测玩家看向的方块
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockState blockState = level.getBlockState(blockPos);

            // 检查是否为水
            if (blockState.getFluidState().is(FluidTags.WATER)) {
                // 模拟UseOnContext
                UseOnContext context = new UseOnContext(player, hand, blockHitResult);
                return fillBowlWithWater(context, level, player, hand);
            }
        }

        return InteractionResult.PASS;
    }

    public static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluidMode) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(Attributes.BLOCK_INTERACTION_RANGE).getValue();
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluidMode, player));
    }
}
