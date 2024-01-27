package net.tme.saws;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class SawsDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(SawsRecipeGenerator::new);
	}

	private static class SawsRecipeGenerator extends FabricRecipeProvider {
		private SawsRecipeGenerator(FabricDataOutput generator) {
			super(generator);
		}

		@Override
		public void generate(RecipeExporter exporter) {
			// saws:crafting_table
			ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, Blocks.CRAFTING_TABLE)
					.input('#', ItemTags.LOGS)
					.pattern("#")
					.criterion("unlock_right_away", TickCriterion.Conditions.createTick())
					.showNotification(false) // Thus, unfortunately, only crafting_shaped can be used.
					.offerTo(exporter, new Identifier("saws:crafting_table"));
		}
	}
}