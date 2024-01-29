package net.tme.saws.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.tme.saws.entity.SleepingBagBlockEntity;
import org.jetbrains.annotations.Nullable;

public class SleepingBagBlock extends BedBlock implements BlockEntityProvider {
    private final DyeColor COLOR;

    public SleepingBagBlock(DyeColor color, Settings settings) {
        super(color, settings);
        this.COLOR = color;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SleepingBagBlockEntity(pos, state, this.COLOR);
    }

    private static final VoxelShape SLEEPING_BAG_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0);

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SLEEPING_BAG_SHAPE;
    }
}
