package net.tme.saws;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.tme.saws.render.SawsEntityModelLayers;
import net.tme.saws.render.block.entity.SleepingBagBlockEntityRenderer;

public class SawsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityModelLayerRegistry.registerModelLayer(SawsEntityModelLayers.SLEEPING_BAG_HEAD, SleepingBagBlockEntityRenderer::createHeadLayer);
		EntityModelLayerRegistry.registerModelLayer(SawsEntityModelLayers.SLEEPING_BAG_FOOT, SleepingBagBlockEntityRenderer::createFootLayer);
	}
}