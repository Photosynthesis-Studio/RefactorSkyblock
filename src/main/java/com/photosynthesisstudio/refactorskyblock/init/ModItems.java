package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.item.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEM_REG = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> PLANT_FERTILIZER = registerItem("plant_fertilizer", BoneMealItem::new);

    public static final DeferredItem<Item> BONE_BOWL = registerItem("bone_bowl", BoneBowlItem::new);
    public static final DeferredItem<Item> BONE_BOWL_WATER = registerItem("bone_bowl_water", BoneBowlWaterItem::new);

    public static final DeferredItem<Item> STONE_ARROW = registerSimpleItem("stone_arrow");
    public static final DeferredItem<Item> STONE_NUGGET = registerSimpleItem("stone_nugget");

    //简易工具
    public static final DeferredItem<Item> SIMPLE_SHOVEL = ITEM_REG.registerItem("simple_shovel",
            props -> new Item(props.shovel(
                    ModToolMaterial.SIMPLE, 1.0f, -3.0f)));
    public static final DeferredItem<Item> SIMPLE_PICKAXE = ITEM_REG.registerItem("simple_pickaxe",
            props -> new Item(props.pickaxe(
                    ModToolMaterial.SIMPLE, 0.5f, -3.0f)));
    public static final DeferredItem<Item> SIMPLE_AXE = ITEM_REG.registerItem("simple_axe",
            props -> new Item(props.axe(
                    ModToolMaterial.SIMPLE, 4.0f, -3.5f)));
    public static final DeferredItem<Item> SIMPLE_HOE = ITEM_REG.registerItem("simple_hoe",
            props -> new Item(props.hoe(
                    ModToolMaterial.SIMPLE, 0.0f, -3.0f)));

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

    public static AttributeModifier attributeModifier(String name, float amount, AttributeModifier.Operation attributeModifier) {
        return new AttributeModifier(Identifier.fromNamespaceAndPath(MODID, name), amount, attributeModifier);
    }
}
