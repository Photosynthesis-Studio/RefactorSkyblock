package com.photosynthesisstudio.refactorskyblock.item;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class BarkItem extends Item {
    public BarkItem(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                        .nutrition(3)
                        .saturationModifier(1f)
                        .build())
                .component(DataComponents.CONSUMABLE,
                        Consumable.builder()
                                .consumeSeconds(5f)
                                .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(
                                        MobEffects.SLOWNESS,
                                        600,
                                        0), 0.7f))
                                .build())
        );
    }
}
