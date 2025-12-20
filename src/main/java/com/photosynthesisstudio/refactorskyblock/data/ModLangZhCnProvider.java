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

import java.util.List;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;
import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig.CAN_PLAY_MODE;
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
        addConfiguration(CAN_PLAY_MODE, "能玩模式", "...能玩就行");

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

        add(SIMPLE_AXE, "简易斧");
        add(SIMPLE_HOE, "简易锄");
        add(SIMPLE_PICKAXE, "简易镐");
        add(SIMPLE_SHOVEL, "简易锹");

        addCreativeModeTab(ModCreativeModeTabs.ITEMS_TAB, "异构空岛 | 物品");

        addSound(ModSoundEvents.BONE_BOWL_EMPTY, "骨碗：倒空");
        addSound(ModSoundEvents.BONE_BOWL_FILL, "骨碗：装满");

        add("text.onPlayerLoggedIn.1", "欢迎游玩 异构空岛 v0.1.0");
        add("text.onPlayerLoggedIn.2", "能玩模式 字面意思 记得死亡不掉落");

        addAdvancement("battle", "crafting_stone_sword", "战斗升级", "使用1个圆石和1个木棍来合成石剑，增强你的战斗能力");
        addAdvancement("battle", "crafting_wooden_sword", "战斗", "合成了1把木剑，你会发现方块和实体交互距离变短了!\n你没感觉错，初始值分别为2.5和1.5，所有武器有特殊距离，战斗需要使用武器!");
        addAdvancement("battle", "kill_drowned", "杀死一只溺尸", "你真是坏到骨子里了!");
        addAdvancement("battle", "kill_skeleton", "你真是坏到骨子里了", "杀死1只骷髅证明你不是软骨头");
        addAdvancement("battle", "kill_spider", "腿越多生物越邪恶", "杀死1只蜘蛛证明你没有蜘蛛恐惧症");
        addAdvancement("battle", "kill_zombie", "杀死姜丝", "杀死1只僵尸证明你的石粒");
        addAdvancement("minecraft", "minecraft", "Minecraft", "欢迎!");
        addAdvancement("minecraft", "get_wood", "我木头呢?", "空手挖掘木头有概率掉落去皮原木和树皮, 用去皮原木才能合成木板");
        addAdvancement("minecraft", "get_planks", "1:1合成?!", "黑心商人这一块");
        addAdvancement("minecraft", "get_bone_block", "头盖骨建材", "用4个骨粉合成骨块，用于各种建筑");
        addAdvancement("minecraft", "eat_bark", "不是你真吃啊?", "孩子牙口挺好, 但是嘴有点馋");
        addAdvancement("minecraft", "get_bone_bowl", "头盖骨饭碗", "用2个骨头来合成骨碗");
        addAdvancement("minecraft", "get_plant_fertilizer", "回收再利用", "用植物合成植物肥, 催熟作物");
        addAdvancement("minecraft", "get_bone_meal", "金坷垃", "这个是正宗的");
        addAdvancement("minecraft", "get_bone", "硬骨头", "嘟嘟哒嘟嘟哒");
        addAdvancement("minecraft", "get_simple_axe", "这真的能用吗?", "合成一把简易斧");
        addAdvancement("minecraft", "get_simple_hoe", "开垦荒岛", "制作简易锄");
        addAdvancement("minecraft", "get_simple_pickaxe", "……这玩意有用吗", "制作简易镐");
        addAdvancement("minecraft", "get_simple_shovel", "小心点挖!", "制作简易锹");
        addAdvancement("minecraft", "get_seeds", "光合作用", "种植作物, 开始科技的第一步, 也就是 光 合 作 用~");
        addAdvancement("minecraft", "get_beetroot", "天才!", "我是农民，这就是甜菜!");
        addAdvancement("minecraft", "get_melon_slice", "窝头咩乐", "吃瓜ing~");
        addAdvancement("minecraft", "get_pumpkin", "呱瓜", "瓜瓜瓜");

        addAdvancement("technology", "technology", "科技", "记录你的科技进度");
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

    private String configPath(String configuration) {
        return configuration
                .replaceAll("\\[", "")
                .replaceAll("]", "");
    }

    private void addSound(Holder<SoundEvent> sound, String name) {
        add(sound.getRegisteredName().replaceAll(MODID + ":", "subtitles."), name);
    }
}
