package net.tme.saws.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.tme.saws.Saws;

public class SawsBlocks {
    public static void registerSawsBlocks() {
        Saws.LOGGER.info("Registering blocks for " + Saws.MOD_ID);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Saws.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(Saws.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static Block createSleepingBagBlock(DyeColor color) {
        return new SleepingBagBlock(color, AbstractBlock.Settings.create().mapColor(state -> state.get(BedBlock.PART) == BedPart.FOOT ? color.getMapColor() : MapColor.WHITE_GRAY).sounds(BlockSoundGroup.WOOD).strength(0.2f).nonOpaque().burnable().pistonBehavior(PistonBehavior.DESTROY));
    }

    public static final Block RED_SLEEPING_BAG = registerBlock("red_sleeping_bag", SawsBlocks.createSleepingBagBlock(DyeColor.RED));
}
