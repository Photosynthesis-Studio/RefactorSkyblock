package com.photosynthesisstudio.refactorskyblock;

//import com.photosynthesisstudio.refactorskyblock.data.ModAdvancementGenerator;

import com.photosynthesisstudio.refactorskyblock.data.ModLangEnUsProvider;
import com.photosynthesisstudio.refactorskyblock.data.ModLangZhCnProvider;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public class RefactorSkyblockDatagen {
    @SubscribeEvent
    public void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ModLangEnUsProvider::new);
        event.createProvider(ModLangZhCnProvider::new);
//        event.createProvider((output, lookupProvider)
//                -> new AdvancementProvider(output, lookupProvider,
//                List.of(
//                        new ModAdvancementGenerator()
//                )
//        ));

        DataGenerator.PackGenerator coverVanilla = event.getGenerator().getBuiltinDatapack(
                true,
                RefactorSkyblock.MODID,
                "cover_vanilla"
        );
    }
}
