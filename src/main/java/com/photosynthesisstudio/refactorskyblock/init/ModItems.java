package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.RefactorSkyblock;
import com.photosynthesisstudio.refactorskyblock.item.BarkItem;
import com.photosynthesisstudio.refactorskyblock.item.PlantFertilizerItem;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REG = DeferredRegister.createItems(RefactorSkyblock.MODID);

    public static final DeferredItem<Item> PLANT_FERTILIZER = ITEM_REG.registerItem("plant_fertilizer", PlantFertilizerItem::new);

    public static final DeferredItem<Item> BONE_BOWL = registerSimpleItem("bone_bowl");
    public static final DeferredItem<Item> WATER_BONE_BOWL = registerSimpleItem("water_bone_bowl");

    public static final DeferredItem<Item> STONE_ARROW = registerSimpleItem("stone_arrow");
    public static final DeferredItem<Item> STONE_NUGGET = registerSimpleItem("stone_nugget");

    // 树皮
    public static final DeferredItem<Item> ACACIA_BARK = bark("acacia");
    public static final DeferredItem<Item> BIRCH_BARK = bark("birch");
    public static final DeferredItem<Item> CHERRY_BARK = bark("cherry");
    public static final DeferredItem<Item> CRIMSON_BARK = bark("crimson");
    public static final DeferredItem<Item> DARK_OAK_BARK = bark("dark_oak");
    public static final DeferredItem<Item> JUNGLE_BARK = bark("jungle");
    public static final DeferredItem<Item> MANGROVE_BARK = bark("mangrove");
    public static final DeferredItem<Item> OAK_BARK = bark("oak");
    public static final DeferredItem<Item> PALE_OAK_BARK = bark("pale_oak");
    public static final DeferredItem<Item> SPRUCE_BARK = bark("spruce");
    public static final DeferredItem<Item> WARPED_BARK = bark("warped");

    private static DeferredItem<Item> registerSimpleItem(String name) {
        return ITEM_REG.registerSimpleItem(name);
    }

    private static DeferredItem<Item> bark(String logName) {
        return ITEM_REG.registerItem(logName + "_bark", BarkItem::new);
    }
}
