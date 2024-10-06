package net.anweisen.bannerseverywhere.datagen;

import net.anweisen.bannerseverywhere.BannersEverywhereMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class ModelGenerator extends FabricModelProvider {

  public ModelGenerator(FabricDataOutput output) {
    super(output);
  }

  @Override
  public void generateBlockStateModels(BlockStateModelGenerator generator) {
    BlockStateModelGenerator.BuiltinModelPool pool = generator.registerBuiltin(Identifier.ofVanilla("block/banner"), Blocks.OAK_PLANKS);

    for (DyeColor color : DyeColor.values()) {
      pool.includeWithoutItem(BannersEverywhereMod.getHangingBannerBlock(color), BannersEverywhereMod.getSidewaysBannerBlock(color));
    }
  }

  @Override
  public void generateItemModels(ItemModelGenerator generator) {

  }
}
