package com.photosynthesisstudio.refactorskyblock;

import com.mojang.serialization.Codec;
import com.photosynthesisstudio.refactorskyblock.event.InitializeSkyblock;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

import java.util.function.Supplier;

import static com.photosynthesisstudio.refactorskyblock.init.ModCreativeModeTabs.TAB_REG;
import static com.photosynthesisstudio.refactorskyblock.init.ModItems.ITEM_REG;

@Mod(RefactorSkyblock.MODID)
public class RefactorSkyblock {
    public static final String MODID = "refactorskyblock";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_REG =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Boolean>> PLAYER_INITIALIZE = ATTACHMENT_REG.register(
            "initialize", () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL.fieldOf("initialize")).build()
    );

    public RefactorSkyblock(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(new InitializeSkyblock());

        modEventBus.register(new RefactorSkyblockDatagen());

        ATTACHMENT_REG.register(modEventBus);
        ITEM_REG.register(modEventBus);
        TAB_REG.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.CLIENT, RefactorSkyblockConfig.CLIENT.build());
        modContainer.registerConfig(ModConfig.Type.COMMON, RefactorSkyblockConfig.COMMON.build());
        modContainer.registerConfig(ModConfig.Type.SERVER, RefactorSkyblockConfig.SERVER.build());
    }
}
