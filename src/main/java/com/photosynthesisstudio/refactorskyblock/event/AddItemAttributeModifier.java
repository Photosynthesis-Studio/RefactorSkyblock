package com.photosynthesisstudio.refactorskyblock.event;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;

public class AddItemAttributeModifier {
    @SubscribeEvent
    public void addItemAttributeModifier(ItemAttributeModifierEvent event) {
        tool(ModItems.SIMPLE_AXE.get(), 0.75f, 0.75f, event);
        tool(ModItems.SIMPLE_HOE.get(), 1.0f, 1.0f, event);
        tool(ModItems.SIMPLE_PICKAXE.get(), 1.0f, 1.0f, event);
        tool(ModItems.SIMPLE_SHOVEL.get(), 1.0f, 1.0f, event);
    }

    private void tool(Item item, float blockRange, float entityRange, ItemAttributeModifierEvent event) {
        add(item, Attributes.BLOCK_INTERACTION_RANGE, blockRange, event);
        add(item, Attributes.ENTITY_INTERACTION_RANGE, entityRange, event);
    }

    private void add(Item item, Holder<Attribute> attributes, float amount, ItemAttributeModifierEvent event) {
        add(item, attributes, amount, EquipmentSlotGroup.MAINHAND, event);
    }

    private void add(Item item, Holder<Attribute> attributes, float amount, EquipmentSlotGroup equipmentSlots, ItemAttributeModifierEvent event) {
        if (event.getItemStack().getItem() == item) {
            event.addModifier(attributes, toolModifier(amount), equipmentSlots);
        }
    }

    private static AttributeModifier toolModifier(float amount) {
        return ModItems.attributeModifier("tool_modifier", amount, AttributeModifier.Operation.ADD_VALUE);
    }
}
