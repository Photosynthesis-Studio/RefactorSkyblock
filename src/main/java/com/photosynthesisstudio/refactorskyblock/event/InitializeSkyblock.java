package com.photosynthesisstudio.refactorskyblock.event;

import com.mojang.serialization.Codec;
import com.photosynthesisstudio.refactorskyblock.RefactorSkyblockConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.WritableLevelData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.LevelEvent;

import java.util.function.Supplier;

import static com.photosynthesisstudio.refactorskyblock.RefactorSkyblock.*;

public class InitializeSkyblock {
    public static final Supplier<AttachmentType<Boolean>> PLAYER_INITIALIZE = ATTACHMENT_REG.register(
            "initialize", () -> AttachmentType.builder(() -> false)
                    .serialize(Codec.BOOL.fieldOf("initialize")).build()
    );

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

        // 设出生点
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

        attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE).setBaseValue(3.5);
        attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(2.5);

        if (!player.getData(PLAYER_INITIALIZE)) {
            // 给物品
            player.addItem(new ItemStack(Items.BREAD, RefactorSkyblockConfig.INITIAL_FOOD_NUMBER.get()));
            player.addItem(new ItemStack(Items.RED_BUNDLE));

            // 初始化
            player.setData(PLAYER_INITIALIZE, true);

            LOGGER.info("Initialize %s items complete".formatted(player.getName()));
        }

        LOGGER.info("Initialize player attributes complete");
    }

    @SubscribeEvent
    public void onChangePlayerGameMode(PlayerEvent.PlayerChangeGameModeEvent event) {
        Player player = event.getEntity();
        AttributeMap attributes = player.getAttributes();
        GameType newGameMode = event.getNewGameMode();

        Identifier id = Identifier.fromNamespaceAndPath(MODID, "creative_plus");
        AttributeModifier modifier = new AttributeModifier(id, 10, AttributeModifier.Operation.ADD_VALUE);

        if (newGameMode == GameType.CREATIVE) {
            attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE).addTransientModifier(modifier);
            attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE).addTransientModifier(modifier);

            LOGGER.info("%s switched game mode to creative, add modifier complete.".formatted(player.getName()));
        } else {
            attributes.getInstance(Attributes.BLOCK_INTERACTION_RANGE).removeModifier(modifier);
            attributes.getInstance(Attributes.ENTITY_INTERACTION_RANGE).removeModifier(modifier);

            LOGGER.info("%s switched other game mode, remove modifier removed.".formatted(player.getName()));
        }
    }
}
