package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.RefactorSkyblock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.photosynthesisstudio.refactorskyblock.init.ModItems.*;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> TAB_REG = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RefactorSkyblock.MODID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB =
            register("items", (name) -> registerCreativeModeTab(
                    name,
                    (parameters, output) -> {
                        output.accept(PLANT_FERTILIZER);

                        output.accept(ACACIA_BARK);
                        output.accept(BIRCH_BARK);
                        output.accept(CHERRY_BARK);
                        output.accept(CRIMSON_BARK);
                        output.accept(DARK_OAK_BARK);
                        output.accept(JUNGLE_BARK);
                        output.accept(MANGROVE_BARK);
                        output.accept(OAK_BARK);
                        output.accept(PALE_OAK_BARK);
                        output.accept(SPRUCE_BARK);
                        output.accept(WARPED_BARK);

                        output.accept(BONE_BOWL);
                        output.accept(STONE_ARROW);
                        output.accept(STONE_NUGGET);
                        output.accept(WATER_BONE_BOWL);
                        }, () -> PLANT_FERTILIZER.get().getDefaultInstance()
            ));

    public static DeferredHolder<CreativeModeTab, CreativeModeTab> register(String name, Function<String, CreativeModeTab.Builder> builder) {
        return TAB_REG.register(name, builder.apply(name)::build);
    }

    public static CreativeModeTab.Builder registerCreativeModeTab(
            String name,
            CreativeModeTab.DisplayItemsGenerator displayItemsGenerator,
            Supplier<ItemStack> icon) {
        return registerCreativeModeTab(name, displayItemsGenerator).icon(icon);
    }

    public static CreativeModeTab.Builder registerCreativeModeTab(String name, CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {
        return CreativeModeTab.builder().title(getComponent(name)).displayItems(displayItemsGenerator);
    }

    private static @NotNull MutableComponent getComponent(String imagePath) {
        return Component.translatable("itemGroup." + RefactorSkyblock.MODID + "." + imagePath);
    }
}
