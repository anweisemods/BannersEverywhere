package net.anweisen.bannerseverywhere;

import net.anweisen.bannerseverywhere.datagen.ModelGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class BannersEverywhereDataGenerator implements DataGeneratorEntrypoint {

  @Override
  public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
    FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

    pack.addProvider(ModelGenerator::new);
  }
}
