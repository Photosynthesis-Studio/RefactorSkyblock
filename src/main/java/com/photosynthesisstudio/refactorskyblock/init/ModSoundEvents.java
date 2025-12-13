package com.photosynthesisstudio.refactorskyblock.init;

import com.photosynthesisstudio.refactorskyblock.RefactorSkyblock;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_REG = DeferredRegister.create(Registries.SOUND_EVENT, RefactorSkyblock.MODID);

    public static final Holder<SoundEvent> BONE_BOWL_EMPTY = register("item.bone_bowl.empty");
    public static final Holder<SoundEvent> BONE_BOWL_FILL = register("item.bone_bowl.fill");

    private static DeferredHolder<SoundEvent, @org.jetbrains.annotations.NotNull SoundEvent> register(String name) {
        return SOUND_REG.register(name, SoundEvent::createVariableRangeEvent);
    }
}
