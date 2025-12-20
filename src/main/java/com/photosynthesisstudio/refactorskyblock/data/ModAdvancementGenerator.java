package com.photosynthesisstudio.refactorskyblock.data;

import com.photosynthesisstudio.refactorskyblock.init.ModItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class ModAdvancementGenerator implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.@NotNull Provider registries, @NotNull Consumer<AdvancementHolder> saver) {
        inventoryChangedAdvancement(Items.BONE_BLOCK,
                "minecraft",
                "get_bone_meal",
                saver);
        inventoryChangedAdvancement(Items.BONE_MEAL,
                "minecraft",
                "get_wood",
                saver);
        inventoryChangedAdvancement(Items.COPPER_NUGGET,
                "minecraft",
                "crafting_leather",
                saver);
        inventoryChangedAdvancement(ModItems.STONE_NUGGET.get(),
                "minecraft",
                "crafting_leather",
                saver);
        inventoryChangedAdvancement(ModItems.BONE_BOWL.get(),
                "minecraft",
                "get_bone_meal",
                saver);
        inventoryChangedAdvancement(ModItems.SIMPLE_HOE.get(),
                "minecraft",
                "get_planks",
                saver);
        inventoryChangedAdvancement(ModItems.SIMPLE_AXE.get(),
                "minecraft",
                "get_plank",
                saver);
        inventoryChangedAdvancement(ModItems.SIMPLE_PICKAXE.get(),
                "minecraft",
                "get_plank",
                saver);
        inventoryChangedAdvancement(ModItems.SIMPLE_SHOVEL.get(),
                "minecraft",
                "get_plank",
                saver);
        inventoryChangedAdvancement(Items.MELON_SLICE,
                "minecraft",
                "get_seeds",
                saver);
        inventoryChangedAdvancement(Items.PUMPKIN,
                "minecraft",
                "get_seeds",
                saver);
        inventoryChangedAdvancement(Items.BEETROOT,
                "minecraft",
                "get_seeds",
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
//
//    private void playerKilledEntity(Item icon, String category, String parent,
//                                    String name, AdvancementType advancementType,
//                                    boolean showToast, boolean announceToChat, boolean hidden,
//                                    Entity entity, int exp,
//                                    Consumer<AdvancementHolder> saver) {
//        EntityPredicate entityPredicate = EntityPredicate.Builder.entity().build();
//        KilledTrigger.TriggerInstance trigger = KilledTrigger.TriggerInstance.playerKilledEntity(eneityPredicate);
//
//        advancement(icon, category, parent, name, advancementType, showToast,
//                announceToChat, hidden, "crafting_",
//                KilledTrigger.TriggerInstance.playerKilledEntity(
//                        new EntityPredicate.Builder().of(EntityType.DROWNED)),
//                exp, saver);
//    }

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