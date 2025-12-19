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

        add(PLANT_FERTILIZER, "Plant Fertilizer");

        add(BONE_BOWL, "Bone Bowl");
        add(BONE_BOWL_WATER, "Water Bone Bowl");

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

        add(SIMPLE_AXE, "Simple Axe");
        add(SIMPLE_HOE, "Simple Hoe");
        add(SIMPLE_PICKAXE, "Simple Pickaxe");
        add(SIMPLE_SHOVEL, "Simple Shovel");

        addCreativeModeTab(ModCreativeModeTabs.ITEMS_TAB, "Refactor Skyblock | Items");

        addSound(ModSoundEvents.BONE_BOWL_EMPTY, "Bone Bowl empties");
        addSound(ModSoundEvents.BONE_BOWL_FILL, "Bone Bowl fills");

        add("text.onPlayerLoggedIn.1", "Welcome to Refactor Skyblock v0.1.0");
        add("text.onPlayerLoggedIn.2", "Can Play Mode, just can play... remember open keepInventory!");

        addAdvancement("battle", "crafting_stone_sword", "战斗升级", "使用1个圆石和1个木棍来合成石剑，增强你的战斗能力");
        addAdvancement("battle", "crafting_wooden_sword", "战斗", "合成了1把木剑，你会发现方块和实体交互距离变短了!\n你没感觉错，初始值分别为2.5和1.5，所有武器有特殊距离，战斗需要使用武器!");
        addAdvancement("battle", "kill_drowned", "杀死一只溺尸", "你真是坏到骨子里了!");
        addAdvancement("battle", "kill_skeleton", "你真是坏到骨子里了", "杀死1只骷髅证明你不是软骨头");
        addAdvancement("battle", "kill_spider", "腿越多生物越邪恶", "杀死1只蜘蛛证明你没有蜘蛛恐惧症");
        addAdvancement("battle", "kill_zombie", "杀死姜丝", "杀死1只僵尸证明你的石粒");

        addAdvancement("minecraft", "crafting_bone_block", "头盖骨建材", "用4个骨粉合成骨块，用于各种建筑");
        addAdvancement("minecraft", "crafting_bone_bowl", "头盖骨饭碗", "用2个骨头来合成骨碗");
        addAdvancement("minecraft", "crafting_bone_meal", "金坷垃", "用小麦合成骨粉，增加你的产量，种子也可以合成骨粉");
        addAdvancement("minecraft", "crafting_cobblestone", "石材切制", "将石头切制成圆石，我们MC也有自己的圆石");
        addAdvancement("minecraft", "crafting_dirt", "这就是光合作用!", "使用1个小麦、1个甜菜、1个西瓜片、1个南瓜来合成泥土，甜菜根最有用的1集!");
        addAdvancement("minecraft", "crafting_iron_ore", "原铁矿", "用4个铁粒合成铁矿石，挖掘后获得粗铁和圆石");
        addAdvancement("minecraft", "crafting_leather", "腐肉烧皮革?并不是......", "用4个腐肉来合成皮革");
        addAdvancement("minecraft", "crafting_planks", "1:1合成?!", "拿着斧头对着原木右键即可获得去皮原木，再将去皮原木合成木板");
        addAdvancement("minecraft", "crafting_stone_nugget", "石粒!", "这么强?");
        addAdvancement("minecraft", "crafting_stone_pickaxe", "石器时代", "使用2个圆石和1个棍木来制作1把石镐");
        addAdvancement("minecraft", "crafting_stonecutter", "切木机还是切石机?", "科技第二步，利用切石机增加产量，并制作更多物品，使用2个铁粒、1个石头、1个皮革来合成");
        addAdvancement("minecraft", "crafting_wooden_axe", "奇怪的配方", "使用2个木棍和1个木板来合成木斧");
        addAdvancement("minecraft", "crafting_wooden_hoe", "开垦荒岛", "用3个木棍来合成1把木锄");
        addAdvancement("minecraft", "crafting_wooden_pickaxe", "采石时间到", "使用2个木板和1个棍木来制作1把木镐。赶快用你的新镐去挖石头吧");
        addAdvancement("minecraft", "eat_bark", "牙口好", "牙口虽好，但是胃不好");
        addAdvancement("minecraft", "get_beetroot_seeds", "这是天才种子", "获得甜菜种子\n我是农民，这就是甜菜!");
        addAdvancement("minecraft", "get_copper_nugget", "Mojang最爱——恋铜癖", "将僵尸泡水里一段时间将其传化成溺尸，杀死它并获得铜粒");
        addAdvancement("minecraft", "get_melon_seeds", "这是水瓜种子", "获得西瓜种子");
        addAdvancement("minecraft", "get_pumpkin_seeds", "这是南瓜种子", "获得南瓜种子\n所有种子可以通过打草掉落");
        addAdvancement("minecraft", "get_stone_nugget", "打磨", "使用1张皮革和1支石箭打磨出石粒，杀死骷髅会掉落石箭");
        addAdvancement("minecraft", "kill_zombie", "暗面血肉", "杀死一只僵尸并获得它的腐肉，使用怪物的血肉作为资源。使用1个木板和一个木棍来合成木剑");
        addAdvancement("minecraft", "minecraft", "Minecraft", "欢迎!\n奖励：面包*n 红色收纳袋\n望远镜*1");
        addAdvancement("minecraft", "placed_wheat_seeds", "光合作用", "种植小麦，开始科技的第一步，也就是 光 合 作 用~");
        addAdvancement("technology", "technology", "科技", "记录你的科技进度");

    }

    public <R, T extends R> void addCreativeModeTab(DeferredHolder<R, T> itemGroup, String name) {
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
