package net.anweisen.bannerseverywhere.client;

import net.anweisen.bannerseverywhere.BannersEverywhereMod;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class BannersEverywhereClientMod implements ClientModInitializer {

  @Override
  public void onInitializeClient() {
    BlockEntityRendererFactories.register(BannersEverywhereMod.SIDEWAYS_BANNER_TYPE, BannerBlockEntityRenderer::new);
    BlockEntityRendererFactories.register(BannersEverywhereMod.SIDE_BANNER_TYPE, BannerBlockEntityRenderer::new);
    BlockEntityRendererFactories.register(BannersEverywhereMod.HANGING_BANNER_TYPE, BannerBlockEntityRenderer::new);
  }
}