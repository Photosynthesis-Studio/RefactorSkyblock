package com.photosynthesisstudio.refactorskyblock.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class UnfinishedCampfireBlock extends Block implements SimpleWaterloggedBlock{
    public UnfinishedCampfireBlock(BlockBehaviour.Properties properties) {
        super(properties
                .mapColor(MapColor.WOOD)
                .strength(2.0F)
                .sound(SoundType.WOOD)
                .noOcclusion()
                .ignitedByLava());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.column(16.0f, 0.0f, 7.0f);

    protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (itemStack.isEmpty()
                || itemStack.getItem() instanceof BlockItem) {
            return InteractionResult.PASS;
        }

        int isFuel = itemStack.getBurnTime(RecipeType.CAMPFIRE_COOKING, level.fuelValues());

        if (isFuel > 0 && isFuel <= 1600) {
            // 不是创造 就减少物品
            if (!player.isCreative()) {
                if (itemStack.isEmpty()) {
                    return InteractionResult.PASS;
                }
                itemStack.shrink(1);
                // 如果减少后堆叠为空，则设置手为空
                if (itemStack.isEmpty()) {
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }
            }

            if (!level.isClientSide()) {
                level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

                for (int i = 0; i < 5; i++) {
                    ServerLevel serverLevel = (ServerLevel) level;
                    serverLevel.sendParticles(
                            ParticleTypes.FLAME,
                            pos.getX() + level.random.nextDouble(),
                            pos.getY() + 1,
                            pos.getZ() + level.random.nextDouble(),
                            1,
                            0.0,
                            0.0,
                            0.0,
                            1.0
                    );
                }
            }

            if (level.random.nextBoolean() && level.random.nextBoolean()) {
                level.setBlockAndUpdate(pos, Blocks.CAMPFIRE.withPropertiesOf(level.getBlockState(pos)));

                if (!level.isClientSide()) {
                    level.gameEvent(null, GameEvent.BLOCK_CHANGE, pos);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        LevelAccessor levelaccessor = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        boolean flag = levelaccessor.getFluidState(blockpos).getType() == Fluids.WATER;
        return this.defaultBlockState()
                .setValue(WATERLOGGED, flag)
                .setValue(FACING, context.getHorizontalDirection());
    }

    @Override
    public boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        if (!(Boolean)state.getValue(BlockStateProperties.WATERLOGGED) && fluidState.getType() == Fluids.WATER) {
            level.setBlock(pos, state
                    .setValue(WATERLOGGED, true), 3);
            level.scheduleTick(pos, fluidState.getType(), fluidState.getType().getTickDelay(level));
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected @NotNull BlockState updateShape(BlockState state1, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos1, Direction direction, BlockPos pos2, BlockState state2, RandomSource source) {
        if (state1.getValue(WATERLOGGED)) {
            tickAccess.scheduleTick(pos1, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return direction == Direction.DOWN ? state1 :
                super.updateShape(state1, level, tickAccess, pos1, direction, pos2, state2, source);
    }

    @Override
    protected @NotNull FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected @NotNull BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
