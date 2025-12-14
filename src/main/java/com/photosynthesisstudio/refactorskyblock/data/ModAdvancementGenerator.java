package com.photosynthesisstudio.refactorskyblock.data;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.RecipeCraftedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class ModAdvancementGenerator implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver) {
        recipeCraftedAdvancement(Items.STONE_SWORD,
                "battle",
                "crafting_wooden_sword",
                "crafting/tools/stone/stone_sword",
                saver);

        recipeCraftedAdvancement(Items.BONE_BLOCK,
                "minecraft",
                "crafting_bone_meal",
                "crafting/bone_block",
                saver);
        recipeCraftedAdvancement(Items.BONE_MEAL,
                "minecraft",
                "placed_wheat_seeds",
                "crafting/bone_meal/wheat",
                saver);
        recipeCraftedAdvancement(Items.LEATHER,
                "minecraft",
                "kill_zombie",
                "crafting/leather",
                saver);
        recipeCraftedAdvancement(ModItems.STONE_NUGGET.get(),
                "minecraft",
                "crafting_wooden_pickaxe",
                "crafting/stone_nugget",
                saver);
        recipeCraftedAdvancement(Items.STONE_PICKAXE,
                "minecraft",
                "crafting_stone_nugget",
                "crafting/tools/stone/stone_pickaxe",
                saver);
        recipeCraftedAdvancement(Items.WOODEN_AXE,
                "minecraft",
                "minecraft",
                "crafting/tools/tools/wood/wooden_axe",
                saver);
        recipeCraftedAdvancement(Items.WOODEN_HOE,
                "minecraft",
                "placed_wheat_seeds",
                "crafting/tools/tools/wood/wooden_hoe",
                saver);
        recipeCraftedAdvancement(Items.WOODEN_PICKAXE,
                "minecraft",
                "crafting_wooden_hoe",
                "crafting/tools/tools/wood/wooden_pickaxe",
                saver);
        inventoryChangedAdvancement(Items.BEETROOT_SEEDS,
                "minecraft",
                "get_melon_seeds",
                saver);
        inventoryChangedAdvancement(Items.COPPER_NUGGET,
                "minecraft",
                "crafting_leather",
                saver);
        inventoryChangedAdvancement(Items.MELON_SEEDS,
                "minecraft",
                "get_pumpkin_seeds",
                saver);
        inventoryChangedAdvancement(Items.PUMPKIN_SEEDS,
                "minecraft",
                "placed_wheat_seeds",
                saver);
        inventoryChangedAdvancement(ModItems.STONE_NUGGET.get(),
                "minecraft",
                "crafting_leather",
                saver);
        inventoryChangedAdvancement(Items.WHEAT_SEEDS,
                "minecraft",
                "crafting_planks",
                Items.WHEAT,
                saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent,
                                             Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(icon, category, parent, icon, saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent, Item item,
                                          Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(icon, category, parent, icon.toString()
                        .replaceAll("minecraft:", "")
                        .replaceAll(MODID + ":", ""), item, saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent,
                                          String name, Item item,
                                          Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(icon, category, parent, name, 10, item, saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent,
                                          String name, int exp, Item item,
                                          Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(icon, category, parent, name, AdvancementType.TASK, exp, item, saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent,
                                          String name, AdvancementType advancementType,
                                          int exp, Item item, Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(icon, category, parent, name, advancementType,
                true, true, false,
                exp, item, saver);
    }

    private void inventoryChangedAdvancement(Item icon, String category, String parent,
                                          String name, AdvancementType advancementType,
                                          boolean showToast, boolean announceToChat, boolean hidden,
                                          int exp, Item item, Consumer<AdvancementHolder> saver) {
        advancement(icon, category, parent, name, advancementType, showToast,
                announceToChat, hidden, "get_",
                InventoryChangeTrigger.TriggerInstance.hasItems(item),
                exp, saver);
    }


    private void recipeCraftedAdvancement(Item icon, String category, String parent,
                                          String recipeId, Consumer<AdvancementHolder> saver) {
        recipeCraftedAdvancement(icon, category, parent, icon.toString()
                        .replaceAll("minecraft:", "")
                        .replaceAll(MODID + ":", ""),
                recipeId, saver);
    }

    private void recipeCraftedAdvancement(Item icon, String category, String parent,
                                          String name, String recipeId,
                                          Consumer<AdvancementHolder> saver) {
        recipeCraftedAdvancement(icon, category, parent, name,
                recipeId, 10, saver);
    }

    private void recipeCraftedAdvancement(Item icon, String category, String parent,
                                          String name, String recipeId, int exp,
                                          Consumer<AdvancementHolder> saver) {
        recipeCraftedAdvancement(icon, category, parent, name, AdvancementType.TASK,
                recipeId, exp, saver);
    }

    private void recipeCraftedAdvancement(Item icon, String category, String parent,
                                          String name, AdvancementType advancementType,
                                          String recipeId, int exp,
                                          Consumer<AdvancementHolder> saver) {
        recipeCraftedAdvancement(icon, category, parent, name, advancementType,
                true, true, false,
                recipeId, exp, saver);
    }

    private void recipeCraftedAdvancement(Item icon, String category, String parent,
                                          String name, AdvancementType advancementType,
                                          boolean showToast, boolean announceToChat, boolean hidden,
                                          String recipeId, int exp,
                                          Consumer<AdvancementHolder> saver) {
        advancement(icon, category, parent, name, advancementType, showToast,
                announceToChat, hidden, "crafting_",
                RecipeCraftedTrigger.TriggerInstance.craftedItem(
                        ResourceKey.create(Registries.RECIPE, identifier(MODID, recipeId))),
                exp, saver);
    }

    private void advancement(Item icon, String category, String parent,
                             String name, AdvancementType advancementType,
                             boolean showToast, boolean announceToChat, boolean hidden,
                             String criterionKey, Criterion<?> criterion,
                             int exp, Consumer<AdvancementHolder> saver) {
        Advancement.Builder builder = new Advancement.Builder();
        String criterionName = criterionKey + name;
        builder.display(
                new ItemStack(icon),
                Component.translatable(title(category, criterionName)),
                Component.translatable(description(category,  criterionName)),
                null,
                advancementType,
                showToast,
                announceToChat,
                hidden
        );
        builder.parent(parent(category, parent));
        builder.addCriterion(criterionName, criterion);
        builder.requirements(AdvancementRequirements.allOf(List.of(criterionName)));
        builder.rewards(AdvancementRewards.Builder.experience(exp));
        builder.save(saver, identifier(MODID, category(category, criterionName)));
    }

    private Identifier identifier(String nameSpace, String name) {
        return Identifier.fromNamespaceAndPath(nameSpace, name);
    }

    private String title(String category, String name) {
        return "advancement.%s.%s.%s.title".formatted(MODID, category, name);
    }

    private String description(String category, String name) {
        return "advancement.%s.%s.%s.description".formatted(MODID, category, name);
    }

    private AdvancementHolder parent(String category, String name) {
        return AdvancementSubProvider.createPlaceholder(MODID + ":" + category(category, name));
    }

    private String category(String category, String name) {
        return category + "/" + name;
    }
}