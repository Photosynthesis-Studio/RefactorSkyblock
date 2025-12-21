package com.photosynthesisstudio.refactorskyblock.data;

import com.photosynthesisstudio.refactorskyblock.init.ModBlocks;
import com.photosynthesisstudio.refactorskyblock.init.ModCreativeModeTabs;
import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import com.photosynthesisstudio.refactorskyblock.init.ModSoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;
import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig.CAN_PLAY_MODE;
import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig.INITIAL_FOOD_NUMBER;

public class ModLangEnUsProvider extends LanguageProvider {
    public ModLangEnUsProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(MODID + ".description", "这里写描述");

        add("generator." + MODID + ".skyblock", "Refactor Skyblock");

        addConfiguration(INITIAL_FOOD_NUMBER, "Initial Food Number", "If a new player logs into the world, give him the amount of food.");
        addConfiguration(CAN_PLAY_MODE, "Can Play Mode", "...just can play");

        add(ModItems.PLANT_FERTILIZER, "Plant Fertilizer");

        add(ModItems.BONE_BOWL, "Bone Bowl");
        add(ModItems.BONE_BOWL_WATER, "Water Bone Bowl");

        add(ModItems.STONE_NUGGET, "Stone Nugget");

        add(ModItems.ACACIA_BARK, "Acacia Bark");
        add(ModItems.BIRCH_BARK, "Birch Bark");
        add(ModItems.CHERRY_BARK, "Cherry Bark");
        add(ModItems.CRIMSON_BARK, "Crimson Bark");
        add(ModItems.DARK_OAK_BARK, "Dark Oak Bark");
        add(ModItems.JUNGLE_BARK, "Jungle Bark");
        add(ModItems.MANGROVE_BARK, "Mangrove Bark");
        add(ModItems.OAK_BARK, "Oak Bark");
        add(ModItems.PALE_OAK_BARK, "Pale Oak Bark");
        add(ModItems.SPRUCE_BARK, "Spruce Bark");
        add(ModItems.WARPED_BARK, "Warped Bark");

        add(ModItems.SIMPLE_AXE, "Simple Axe");
        add(ModItems.SIMPLE_HOE, "Simple Hoe");
        add(ModItems.SIMPLE_PICKAXE, "Simple Pickaxe");
        add(ModItems.SIMPLE_SHOVEL, "Simple Shovel");

        add(ModBlocks.UNFINISHED_CAMPFIRE, "未完成的篝火");

        addCreativeModeTab(ModCreativeModeTabs.ITEMS_TAB, "Refactor Skyblock | Items");

        addSound(ModSoundEvents.BONE_BOWL_EMPTY, "Bone Bowl empties");
        addSound(ModSoundEvents.BONE_BOWL_FILL, "Bone Bowl fills");

        add("text.onPlayerLoggedIn.1", "Welcome to Refactor Skyblock v0.1.3");
        add("text.onPlayerLoggedIn.2", "Can Play Mode, just can play... remember open keepInventory!");
    }

    public <R, T extends R> void addCreativeModeTab(DeferredHolder<R, @org.jetbrains.annotations.NotNull T> itemGroup, String name) {
        add("itemGroup." + itemGroup.getId().toString().replace(":", "."), name);
    }

    private void addAdvancement(String category, String name, String title, String description) {
        add("advancement." + MODID + ".%s.%s.title".formatted(category, name), title);
        add("advancement." + MODID + ".%s.%s.description".formatted(category, name), description);
    }

    private <T> void addConfiguration(ModConfigSpec.ConfigValue<T> configValue, String name, String tooltip) {
        List<String> pathParts = configValue.getPath();
        String path = String.join(".", pathParts);

        // 生成翻译键
        String configKey = MODID + ".configuration." + configPath(path);
        add(configKey, name);
        add(configKey + ".tooltip", tooltip);
    }

    private void add(DeferredItem<@org.jetbrains.annotations.NotNull Item> item, String name) {
        this.add(item.get(), name);
    }

    private void add(DeferredBlock<@NotNull Block> block, String name) {
        this.add(block.get(), name);
    }

    private String configPath(String configuration) {
        return configuration
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }

    private void addSound(Holder<SoundEvent> sound, String name) {
        add(sound.getRegisteredName().replaceAll(MODID + ":", "subtitles."), name);
    }
}
