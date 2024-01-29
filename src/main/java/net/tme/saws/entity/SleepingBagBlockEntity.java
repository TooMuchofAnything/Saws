package net.tme.saws.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class SleepingBagBlockEntity extends BedBlockEntity {
    public SleepingBagBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public SleepingBagBlockEntity(BlockPos pos, BlockState state, DyeColor color) {
        super(pos, state, color);
    }
}
