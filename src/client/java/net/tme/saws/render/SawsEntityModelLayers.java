package net.tme.saws.render;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.tme.saws.Saws;

public class SawsEntityModelLayers {
    public static final EntityModelLayer SLEEPING_BAG_HEAD =
            new EntityModelLayer(new Identifier(Saws.MOD_ID, "sleeping_bag_head"), "main");
    public static final EntityModelLayer SLEEPING_BAG_FOOT =
            new EntityModelLayer(new Identifier(Saws.MOD_ID, "sleeping_bag_foot"), "main");
}
