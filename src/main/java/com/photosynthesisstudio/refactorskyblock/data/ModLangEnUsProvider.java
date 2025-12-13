package com.photosynthesisstudio.refactorskyblock.data;

import com.photosynthesisstudio.refactorskyblock.init.ModCreativeModeTabs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;
import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig.INITIAL_FOOD_NUMBER;
import static com.photosynthesisstudio.refactorskyblock.init.ModItems.*;

public class ModLangEnUsProvider extends LanguageProvider {
    public ModLangEnUsProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(MODID + ".description", "这里写描述");

        add("generator." + MODID + ".skyblock", "Refactor Skyblock");

        addConfiguration(INITIAL_FOOD_NUMBER, "Initial Food Number", "If a new player logs into the world, give him the amount of food.");

        // 物品
        add(PLANT_FERTILIZER, "Plant Fertilizer");

        add(BONE_BOWL, "Bone Bowl");
        add(WATER_BONE_BOWL, "Water Bone Bowl");

        add(STONE_ARROW, "Stone Arrow");
        add(STONE_NUGGET, "Stone Nugget");

        add(ACACIA_BARK, "Acacia Bark");
        add(BIRCH_BARK, "Birch Bark");
        add(CHERRY_BARK, "Cherry Bark");
        add(CRIMSON_BARK, "Crimson Bark");
        add(DARK_OAK_BARK, "Dark Oak Bark");
        add(JUNGLE_BARK, "Jungle Bark");
        add(MANGROVE_BARK, "Mangrove Bark");
        add(OAK_BARK, "Oak Bark");
        add(PALE_OAK_BARK, "Pale Oak Bark");
        add(SPRUCE_BARK, "Spruce Bark");
        add(WARPED_BARK, "Warped Bark");

        addCreativeModeTab(ModCreativeModeTabs.ITEMS_TAB, "Refactor Skyblock | Items");
    }

    public <R, T extends R> void addCreativeModeTab(DeferredHolder<R, T> itemGroup, String name) {
        add("itemGroup." + itemGroup.getId().toString().replace(":", "."), name);
    }

    private void addAdvancement(String name, String title, String description) {
        add("advancements." + MODID + ".%s.title".formatted(name), title);
        add("advancements." + MODID + ".%s.description".formatted(name), description);
    }

    private void addConfiguration(ModConfigSpec.IntValue configuration, String name, String tooltip) {
        add(MODID + ".configuration.%s".formatted(configPath(String.valueOf(configuration.getPath()))), name);
        add(MODID + ".configuration.%s.tooltip".formatted(configPath(String.valueOf(configuration.getPath()))), tooltip);
    }

    private void add(DeferredItem<Item> item, String name) {
        this.add(item.get(), name);
    }

    private String configPath(String configuration) {
        return configuration
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }
}
