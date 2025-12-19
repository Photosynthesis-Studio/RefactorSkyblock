package com.photosynthesisstudio.refactorskyblock.item;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static com.photosynthesisstudio.refactorskyblock.init.ModItems.attributeModifier;

public class SimpleToolItem extends Item {
    public SimpleToolItem(Properties properties, float attackDamage, float attackSpeed,
                          float blockInteractionRange, float entityInteractionRange) {
        super(properties.attributes(ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, attributeModifier(attackDamage), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, attributeModifier(attackSpeed), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.BLOCK_INTERACTION_RANGE, attributeModifier(blockInteractionRange), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ENTITY_INTERACTION_RANGE, attributeModifier(entityInteractionRange), EquipmentSlotGroup.MAINHAND)
                .build()));
    }
}
