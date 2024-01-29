package net.tme.saws.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.tme.saws.Saws;
import net.tme.saws.block.SawsBlocks;

public class SawsBlockEntities {
    public static void registerBlockEntities() {
        Saws.LOGGER.info("Registering block entities for " + Saws.MOD_ID);
    }

    public static final BlockEntityType<SleepingBagBlockEntity> SLEEPING_BAG_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Saws.MOD_ID, "sleeping_bag_block_entity"),
                    FabricBlockEntityTypeBuilder.create(SleepingBagBlockEntity::new,
                            SawsBlocks.RED_SLEEPING_BAG).build());
}
