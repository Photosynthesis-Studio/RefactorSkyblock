package com.photosynthesisstudio.refactorskyblock.data;

import com.photosynthesisstudio.refactorskyblock.init.ModCreativeModeTabs;
import com.photosynthesisstudio.refactorskyblock.init.ModSoundEvents;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;
import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig.INITIAL_FOOD_NUMBER;
import static com.photosynthesisstudio.refactorskyblock.init.ModItems.*;

public class ModLangZhCnProvider extends LanguageProvider {
    public ModLangZhCnProvider(PackOutput output) {
        super(output, MODID, "zh_cn");
    }

    @Override
    protected void addTranslations() {
        add(MODID + ".description", "这里写描述");

        add("generator." + MODID + ".skyblock", "异构空岛");

        addConfiguration(INITIAL_FOOD_NUMBER, "初始化食物数", "当有新玩家加入世界时, 给予他这个数量的食物.");

        add(PLANT_FERTILIZER, "植物肥");

        add(BONE_BOWL, "骨碗");
        add(BONE_BOWL_WATER, "盛有水的骨碗");

        add(STONE_ARROW, "石箭");
        add(STONE_NUGGET, "石粒");

        add(ACACIA_BARK, "金合欢树皮");
        add(BIRCH_BARK, "白桦树皮");
        add(CHERRY_BARK, "樱花树皮");
        add(CRIMSON_BARK, "绯红树皮");
        add(DARK_OAK_BARK, "深色橡木树皮");
        add(JUNGLE_BARK, "丛林树皮");
        add(MANGROVE_BARK, "红树树皮");
        add(OAK_BARK, "橡木树皮");
        add(PALE_OAK_BARK, "苍白橡木树皮");
        add(SPRUCE_BARK, "云杉树皮");
        add(WARPED_BARK, "扭曲树皮");

        addCreativeModeTab(ModCreativeModeTabs.ITEMS_TAB, "异构空岛 | 物品");

        addSound(ModSoundEvents.BONE_BOWL_EMPTY, "骨碗：倒空");
        addSound(ModSoundEvents.BONE_BOWL_FILL, "骨碗：装满");
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

    private void addSound(Holder<SoundEvent> sound, String name) {
        add(sound.getRegisteredName().replaceAll(MODID + ":", "subtitles."), name);
    }
}
