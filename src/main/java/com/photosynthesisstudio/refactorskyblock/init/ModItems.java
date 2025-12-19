package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.item.*;
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
    public static final DeferredItem<Item> SIMPLE_AXE = registerItem("simple_axe",
            props -> new SimpleToolItem(props
                    .axe(ModToolMaterial.SIMPLE, 3.0f, -2.4f),
                    3, 3, 0.75f, 0.75f));

    public static final DeferredItem<Item> SIMPLE_HOE = registerItem("simple_hoe",
            props -> new Item(props
                    .hoe(ModToolMaterial.SIMPLE, 3.0f, -2.4f)
                    .attributes(ItemAttributeModifiers.builder()
                            .add(Attributes.ATTACK_DAMAGE, attributeModifier(0.5f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ATTACK_SPEED, attributeModifier(2.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.BLOCK_INTERACTION_RANGE, attributeModifier(1.25f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE, attributeModifier(1.25f), EquipmentSlotGroup.MAINHAND)
                            .build())));

    public static final DeferredItem<Item> SIMPLE_PICKAXE = registerItem("simple_pickaxe",
            props -> new Item(props
                    .pickaxe(ModToolMaterial.SIMPLE, 1.0f, -2.8f)
                    .attributes(ItemAttributeModifiers.builder()
                            .add(Attributes.ATTACK_DAMAGE, attributeModifier(0.5f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ATTACK_SPEED, attributeModifier(3.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.BLOCK_INTERACTION_RANGE, attributeModifier(1.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE, attributeModifier(0.5f), EquipmentSlotGroup.MAINHAND)
                            .build())));

    public static final DeferredItem<Item> SIMPLE_SHOVEL = registerItem("simple_shovel",
            props -> new Item(props
                    .shovel(ModToolMaterial.SIMPLE, 1.5f, -3.0f)
                    .attributes(ItemAttributeModifiers.builder()
                            .add(Attributes.ATTACK_DAMAGE, attributeModifier(2.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ATTACK_SPEED, attributeModifier(3.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.BLOCK_INTERACTION_RANGE, attributeModifier(1.0f), EquipmentSlotGroup.MAINHAND)
                            .add(Attributes.ENTITY_INTERACTION_RANGE, attributeModifier(1.0f), EquipmentSlotGroup.MAINHAND)
                            .build())));

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

    public static AttributeModifier attributeModifier(String name, float amount) {
        return attributeModifier(name, amount, AttributeModifier.Operation.ADD_VALUE);
    }

    public static AttributeModifier attributeModifier(float amount) {
        return attributeModifier("tool_modifier", amount);
    }
}
