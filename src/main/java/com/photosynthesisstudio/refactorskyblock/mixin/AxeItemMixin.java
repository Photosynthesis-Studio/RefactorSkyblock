package com.photosynthesisstudio.refactorskyblock.mixin;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.Supplier;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    private static final Map<Block, Supplier<Item>> LOG_TO_BARK = Map.ofEntries(
            Map.entry(Blocks.STRIPPED_ACACIA_LOG, ModItems.ACACIA_BARK),
            Map.entry(Blocks.STRIPPED_BIRCH_LOG, ModItems.BIRCH_BARK),
            Map.entry(Blocks.STRIPPED_CHERRY_LOG, ModItems.CHERRY_BARK),
            Map.entry(Blocks.STRIPPED_CRIMSON_STEM, ModItems.CRIMSON_BARK),
            Map.entry(Blocks.STRIPPED_DARK_OAK_LOG, ModItems.DARK_OAK_BARK),
            Map.entry(Blocks.STRIPPED_JUNGLE_LOG, ModItems.JUNGLE_BARK),
            Map.entry(Blocks.STRIPPED_MANGROVE_LOG, ModItems.MANGROVE_BARK),
            Map.entry(Blocks.STRIPPED_OAK_LOG, ModItems.OAK_BARK),
            Map.entry(Blocks.STRIPPED_PALE_OAK_LOG, ModItems.PALE_OAK_BARK),
            Map.entry(Blocks.STRIPPED_SPRUCE_LOG, ModItems.SPRUCE_BARK),
            Map.entry(Blocks.STRIPPED_WARPED_STEM, ModItems.WARPED_BARK));

    private static final Map<Block, Supplier<Item>> WOOD_TO_BARK = Map.ofEntries(
            Map.entry(Blocks.STRIPPED_ACACIA_WOOD, ModItems.ACACIA_BARK),
            Map.entry(Blocks.STRIPPED_BIRCH_WOOD, ModItems.BIRCH_BARK),
            Map.entry(Blocks.STRIPPED_CHERRY_WOOD, ModItems.CHERRY_BARK),
            Map.entry(Blocks.STRIPPED_CRIMSON_HYPHAE, ModItems.CRIMSON_BARK),
            Map.entry(Blocks.STRIPPED_DARK_OAK_WOOD, ModItems.DARK_OAK_BARK),
            Map.entry(Blocks.STRIPPED_JUNGLE_WOOD, ModItems.JUNGLE_BARK),
            Map.entry(Blocks.STRIPPED_MANGROVE_WOOD, ModItems.MANGROVE_BARK),
            Map.entry(Blocks.STRIPPED_OAK_WOOD, ModItems.OAK_BARK),
            Map.entry(Blocks.STRIPPED_PALE_OAK_WOOD, ModItems.PALE_OAK_BARK),
            Map.entry(Blocks.STRIPPED_SPRUCE_WOOD, ModItems.SPRUCE_BARK),
            Map.entry(Blocks.STRIPPED_WARPED_HYPHAE, ModItems.WARPED_BARK));

    // 根据方块获取对应的掉落物
    @Unique
    private static Optional<ItemStack> refactorSkyblock_NeoForge_1_21_11$getBarkDrop(Block logBlock, Level level, boolean hasSilkTouch) {
        Supplier<Item> logBarkSupplier = LOG_TO_BARK.get(logBlock);
        Supplier<Item> woodBarkSupplier = WOOD_TO_BARK.get(logBlock);

        if (logBarkSupplier != null) {
            int count = 4;
            // 随机1-4个
            if (!hasSilkTouch) {
                count = level.random.nextInt(4) + 1;
            }
            return Optional.of(new ItemStack(logBarkSupplier.get(), count));
        }

        if (woodBarkSupplier != null) {
            int count = 4;
            // 随机1-4个
            if (!hasSilkTouch) {
                count = level.random.nextInt(4) + 1;
            }
            return Optional.of(new ItemStack(woodBarkSupplier.get(), count));
        }

        return Optional.empty();
    }

    @Inject(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z",
                    shift = At.Shift.AFTER
            )
    )
    private void useOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level mlevel = context.getLevel();
        BlockPos mblockPos = context.getClickedPos();
        Player mplayer = context.getPlayer();
        boolean hasSilkTouch;

        if (mplayer != null) {
            hasSilkTouch = EnchantmentHelper.getTagEnchantmentLevel(
                    mlevel.registryAccess().getOrThrow(Enchantments.SILK_TOUCH),
                    mplayer.getMainHandItem()) != 0;

            Optional<ItemStack> barkDrop = refactorSkyblock_NeoForge_1_21_11$getBarkDrop(mlevel.getBlockState(mblockPos).getBlock(), mlevel, hasSilkTouch);

            if (barkDrop.isPresent()) {
                mlevel.addFreshEntity(new ItemEntity(
                        mlevel,
                        mblockPos.getX() + 0.5,
                        mblockPos.getY() + 0.5,
                        mblockPos.getZ() + 0.5,
                        barkDrop.get()
                ));
            }
        }
    }
}
