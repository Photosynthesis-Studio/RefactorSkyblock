package com.photosynthesisstudio.refactorskyblock;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static com.photosynthesisstudio.refactorskyblock.init.ModCreativeModeTabs.TAB_REG;
import static com.photosynthesisstudio.refactorskyblock.init.ModItems.ITEM_REG;

@Mod(RefactorSkyblock.MODID)
public class RefactorSkyblock {
    public static final String MODID = "refactorskyblock";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_REG =
            DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    private static final Supplier<AttachmentType<Boolean>> PLAYER_INITIALIZE = ATTACHMENT_REG.register(
            "initialize", () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL.fieldOf("initialize")).build()
    );

    public RefactorSkyblock(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

        ATTACHMENT_REG.register(modEventBus);
        ITEM_REG.register(modEventBus);
        TAB_REG.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, RefactorSkyblockConfig.SPEC);
    }

    @SubscribeEvent
    public void onCreateSpawnPosition(LevelEvent.CreateSpawnPosition event) {
        Level level = (Level) event.getLevel();

        // 服务端 主世界 0 -64 0 基岩?
        if (level.isClientSide() || level.dimension() != Level.OVERWORLD
                || !level.isEmptyBlock(new BlockPos(0, -64, 0))) {
            return;
        }

        // 放方块
        level.setBlock(new BlockPos(0, 64, 0),
                Blocks.CRYING_OBSIDIAN.defaultBlockState(), 3);
        level.setBlock(new BlockPos(0, 65, 0),
                Blocks.TORCH.defaultBlockState(), 3);

        if (level.getLevelData() instanceof WritableLevelData levelData) {
            levelData.setSpawn(LevelData.RespawnData.of(
                    Level.OVERWORLD,
                    new BlockPos(0, 65, 0),
                    0, 0));
        }

        LOGGER.info("Initialize skyblock complete");
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();

        // 服务器端
        if (player.level().isClientSide()) {
            return;
        }

        // 设置玩家属性
        AttributeMap attributes = player.getAttributes();

        // 创造
        if (player.gameMode() == GameType.CREATIVE) {
            attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE).setBaseValue(10);
            attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(10);
        }
        // 生存 和 冒险
        else {
            attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE).setBaseValue(1.5);
            attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(2.5);
        }

        if (!player.getData(PLAYER_INITIALIZE)) {
            // 给物品
            player.addItem(new ItemStack(Items.BREAD, 64));
            player.addItem(new ItemStack(Items.RED_BUNDLE));
            player.addItem(new ItemStack(Items.OAK_PLANKS));
            player.addItem(new ItemStack(Items.STICK, 2));

            // 初始化
            player.setData(PLAYER_INITIALIZE, true);

            LOGGER.info("Initialize " + player.getName() + " items complete");
        }

        LOGGER.info("Initialize player attributes complete");
    }
}
