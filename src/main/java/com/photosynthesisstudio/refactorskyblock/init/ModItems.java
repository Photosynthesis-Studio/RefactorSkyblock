package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.RefactorSkyblock;
import com.photosynthesisstudio.refactorskyblock.item.PlantFertilizerItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REG = DeferredRegister.createItems(RefactorSkyblock.MODID);

    public static final DeferredItem<Item> PLANT_FERTILIZER = ITEM_REG.registerItem("plant_fertilizer", PlantFertilizerItem::new);

    public static final DeferredItem<Item> BONE_BOWL = registerSimpleItem("bone_bowl");
    public static final DeferredItem<Item> WATER_BONE_BOWL = registerSimpleItem("water_bone_bowl");

    public static final DeferredItem<Item> STONE_ARROW = registerSimpleItem("stone_arrow");
    public static final DeferredItem<Item> STONE_NUGGET = registerSimpleItem("stone_nugget");


    // 树皮
    public static final DeferredItem<Item> ACACIA_BARK = registerSimpleItem("acacia_bark");
    public static final DeferredItem<Item> BIRCH_BARK = registerSimpleItem("birch_bark");
    public static final DeferredItem<Item> CHERRY_BARK = registerSimpleItem("cherry_bark");
    public static final DeferredItem<Item> CRIMSON_BARK = registerSimpleItem("crimson_bark");
    public static final DeferredItem<Item> DARK_OAK_BARK = registerSimpleItem("dark_oak_bark");
    public static final DeferredItem<Item> JUNGLE_BARK = registerSimpleItem("jungle_bark");
    public static final DeferredItem<Item> MANGROVE_BARK = registerSimpleItem("mangrove_bark");
    public static final DeferredItem<Item> OAK_BARK = registerSimpleItem("oak_bark");
    public static final DeferredItem<Item> PALE_OAK_BARK = registerSimpleItem("pale_oak_bark");
    public static final DeferredItem<Item> SPRUCE_BARK = registerSimpleItem("spruce_bark");
    public static final DeferredItem<Item> WARPED_BARK = registerSimpleItem("warped_bark");

    private static DeferredItem<Item> registerSimpleItem(String name) {
        return ITEM_REG.registerSimpleItem(name);
    }
}
