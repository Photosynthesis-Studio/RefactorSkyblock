package com.photosynthesisstudio.refactorskyblock.item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class BarkItem extends Item {
    public BarkItem(Properties properties) {
        super(properties.food(new FoodProperties.Builder()
                .nutrition(3)
                .saturationModifier(1)
                .build())
        );
    }
}
