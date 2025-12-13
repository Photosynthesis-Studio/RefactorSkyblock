package com.photosynthesisstudio.refactorskyblock.data;

import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.MODID;

public class ModAdvancementGenerator implements AdvancementSubProvider {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver) {
        getItemAdvancement(dirt, "is_dirt", "story/root", Items.DIRT, saver);
    }

    public Advancement.Builder dirt = Advancement.Builder.advancement();

    private void getItemAdvancement(Advancement.Builder builder, String name,
                                    String parent, Item icon, Consumer<AdvancementHolder> saver) {
        getItemAdvancement(builder, name, parent, icon, icon, saver);
    }

    private void getItemAdvancement(Advancement.Builder builder, String name,
                                    String parent, Item icon,
                                    Item item, Consumer<AdvancementHolder> saver) {
        getItemAdvancement(builder, name, parent, icon,
                AdvancementType.TASK, item, saver);
    }

    private void getItemAdvancement(Advancement.Builder builder, String name,
                                    String parent, Item icon, AdvancementType advancementType,
                                    Item item, Consumer<AdvancementHolder> saver) {
        getItemAdvancement(builder, name, parent, icon,
                advancementType, true, true, false, item, saver);
    }
    
    private void getItemAdvancement(Advancement.Builder builder, String name,
                                    String parent, Item icon, AdvancementType advancementType,
                                    boolean showToast, boolean announceToChat, boolean hidden,
                                    Item item, Consumer<AdvancementHolder> saver) {
        builder.parent(parent(parent));
        builder.display(
                new ItemStack(icon),
                Component.translatable(title(name)),
                Component.translatable(description(name)),
                null,
                advancementType,
                showToast,
                announceToChat,
                hidden
        );
        builder.addCriterion("pickup_" + item.getName().getString(), InventoryChangeTrigger.TriggerInstance.hasItems(item));
        builder.requirements(AdvancementRequirements.allOf(List.of("pickup_" + item.getName().getString())));
        builder.save(saver, identifier(name));
    }

    private Identifier identifier(String name) {
        return identifier(MODID, name);
    }

    private Identifier identifier(String nameSpace, String name) {
        return Identifier.fromNamespaceAndPath(nameSpace, name);
    }

    private String title(String name) {
        return "advancements.%s.%s.title".formatted(MODID, name);
    }

    private String description(String name) {
        return "advancements.%s.%s.description".formatted(MODID, name);
    }

    private AdvancementHolder parent(String name) {
        return AdvancementSubProvider.createPlaceholder(MODID + ":" +name);
    }
}