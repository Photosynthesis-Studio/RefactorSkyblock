package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.RefactorSkyblock;
import com.photosynthesisstudio.refactorskyblock.item.BarkItem;
import com.photosynthesisstudio.refactorskyblock.item.BoneBowlItem;
import com.photosynthesisstudio.refactorskyblock.item.PlantFertilizerItem;
import com.photosynthesisstudio.refactorskyblock.item.BoneBowlWaterItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REG = DeferredRegister.createItems(RefactorSkyblock.MODID);

    public static final DeferredItem<Item> PLANT_FERTILIZER = registerItem("plant_fertilizer", PlantFertilizerItem::new);

    public static final DeferredItem<Item> BONE_BOWL = registerItem("bone_bowl", BoneBowlItem::new);
    public static final DeferredItem<Item> BONE_BOWL_WATER = registerItem("bone_bowl_water", BoneBowlWaterItem::new);

    public static final DeferredItem<Item> STONE_ARROW = registerSimpleItem("stone_arrow");
    public static final DeferredItem<Item> STONE_NUGGET = registerSimpleItem("stone_nugget");

    // 树
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

    private static <I extends Item> DeferredItem<I> registerItem(String name, Function<Item.Properties, ? extends I> func) {
        return ITEM_REG.registerItem(name, func);
    }

    private static DeferredItem<Item> registerSimpleItem(String name) {
        return ITEM_REG.registerSimpleItem(name);
    }

    private static DeferredItem<Item> bark(String logName) {
        return ITEM_REG.registerItem(logName + "_bark", BarkItem::new);
    }
}
