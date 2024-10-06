package net.anweisen.bannerseverywhere;

import net.anweisen.bannerseverywhere.hanging.HangingBannerBlock;
import net.anweisen.bannerseverywhere.hanging.HangingBannerBlockEntity;
import net.anweisen.bannerseverywhere.sideways.SidewaysBannerBlock;
import net.anweisen.bannerseverywhere.sideways.SidewaysBannerBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

public class BannersEverywhereMod implements ModInitializer {

  public static final String MOD_ID = "bannerseverywhere";

  public static final Block WHITE_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.WHITE, "white_sideways_banner", Blocks.WHITE_WALL_BANNER);
  public static final Block ORANGE_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.ORANGE, "orange_sideways_banner", Blocks.ORANGE_WALL_BANNER);
  public static final Block MAGENTA_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.MAGENTA, "magenta_sideways_banner", Blocks.MAGENTA_WALL_BANNER);
  public static final Block LIGHT_BLUE_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.LIGHT_BLUE, "light_blue_sideways_banner", Blocks.LIGHT_BLUE_WALL_BANNER);
  public static final Block YELLOW_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.YELLOW, "yellow_sideways_banner", Blocks.YELLOW_WALL_BANNER);
  public static final Block LIME_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.LIME, "lime_sideways_banner", Blocks.LIME_WALL_BANNER);
  public static final Block PINK_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.PINK, "pink_sideways_banner", Blocks.PINK_WALL_BANNER);
  public static final Block GRAY_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.GRAY, "gray_sideways_banner", Blocks.GRAY_WALL_BANNER);
  public static final Block LIGHT_GRAY_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.LIGHT_GRAY, "light_gray_sideways_banner", Blocks.LIGHT_GRAY_WALL_BANNER);
  public static final Block CYAN_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.CYAN, "cyan_sideways_banner", Blocks.CYAN_WALL_BANNER);
  public static final Block PURPLE_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.PURPLE, "purple_sideways_banner", Blocks.PURPLE_WALL_BANNER);
  public static final Block BLUE_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.BLUE, "blue_sideways_banner", Blocks.BLUE_WALL_BANNER);
  public static final Block BROWN_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.BROWN, "brown_sideways_banner", Blocks.BROWN_WALL_BANNER);
  public static final Block GREEN_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.GREEN, "green_sideways_banner", Blocks.GREEN_WALL_BANNER);
  public static final Block RED_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.RED, "red_sideways_banner", Blocks.RED_WALL_BANNER);
  public static final Block BLACK_SIDEWAYS_BANNER = registerSidewaysBanner(DyeColor.BLACK, "black_sideways_banner", Blocks.BLACK_WALL_BANNER);

  public static final BlockEntityType<? extends BannerBlockEntity> SIDEWAYS_BANNER_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
    id("sideways_banner"), BlockEntityType.Builder.create(SidewaysBannerBlockEntity::new,
      WHITE_SIDEWAYS_BANNER, ORANGE_SIDEWAYS_BANNER, MAGENTA_SIDEWAYS_BANNER, LIGHT_BLUE_SIDEWAYS_BANNER, YELLOW_SIDEWAYS_BANNER,
      LIME_SIDEWAYS_BANNER, PINK_SIDEWAYS_BANNER, GRAY_SIDEWAYS_BANNER, LIGHT_GRAY_SIDEWAYS_BANNER, CYAN_SIDEWAYS_BANNER, PURPLE_SIDEWAYS_BANNER,
      BLUE_SIDEWAYS_BANNER, BROWN_SIDEWAYS_BANNER, GREEN_SIDEWAYS_BANNER, RED_SIDEWAYS_BANNER, BLACK_SIDEWAYS_BANNER
    ).build()
  );

  public static final Block WHITE_HANGING_BANNER = registerHangingBanner(DyeColor.WHITE, "white_hanging_banner", Blocks.WHITE_BANNER);
  public static final Block ORANGE_HANGING_BANNER = registerHangingBanner(DyeColor.ORANGE, "orange_hanging_banner", Blocks.ORANGE_BANNER);
  public static final Block MAGENTA_HANGING_BANNER = registerHangingBanner(DyeColor.MAGENTA, "magenta_hanging_banner", Blocks.MAGENTA_BANNER);
  public static final Block LIGHT_BLUE_HANGING_BANNER = registerHangingBanner(DyeColor.LIGHT_BLUE, "light_blue_hanging_banner", Blocks.LIGHT_BLUE_BANNER);
  public static final Block YELLOW_HANGING_BANNER = registerHangingBanner(DyeColor.YELLOW, "yellow_hanging_banner", Blocks.YELLOW_BANNER);
  public static final Block LIME_HANGING_BANNER = registerHangingBanner(DyeColor.LIME, "lime_hanging_banner", Blocks.LIME_BANNER);
  public static final Block PINK_HANGING_BANNER = registerHangingBanner(DyeColor.PINK, "pink_hanging_banner", Blocks.PINK_BANNER);
  public static final Block GRAY_HANGING_BANNER = registerHangingBanner(DyeColor.GRAY, "gray_hanging_banner", Blocks.GRAY_BANNER);
  public static final Block LIGHT_GRAY_HANGING_BANNER = registerHangingBanner(DyeColor.LIGHT_GRAY, "light_gray_hanging_banner", Blocks.LIGHT_GRAY_BANNER);
  public static final Block CYAN_HANGING_BANNER = registerHangingBanner(DyeColor.CYAN, "cyan_hanging_banner", Blocks.CYAN_BANNER);
  public static final Block PURPLE_HANGING_BANNER = registerHangingBanner(DyeColor.PURPLE, "purple_hanging_banner", Blocks.PURPLE_BANNER);
  public static final Block BLUE_HANGING_BANNER = registerHangingBanner(DyeColor.BLUE, "blue_hanging_banner", Blocks.BLUE_BANNER);
  public static final Block BROWN_HANGING_BANNER = registerHangingBanner(DyeColor.BROWN, "brown_hanging_banner", Blocks.BROWN_BANNER);
  public static final Block GREEN_HANGING_BANNER = registerHangingBanner(DyeColor.GREEN, "green_hanging_banner", Blocks.GREEN_BANNER);
  public static final Block RED_HANGING_BANNER = registerHangingBanner(DyeColor.RED, "red_hanging_banner", Blocks.RED_BANNER);
  public static final Block BLACK_HANGING_BANNER = registerHangingBanner(DyeColor.BLACK, "black_hanging_banner", Blocks.BLACK_BANNER);

  public static final BlockEntityType<? extends BannerBlockEntity> HANGING_BANNER_TYPE = Registry.register(Registries.BLOCK_ENTITY_TYPE,
    id("hanging_banner"), BlockEntityType.Builder.create(HangingBannerBlockEntity::new,
      WHITE_HANGING_BANNER, ORANGE_HANGING_BANNER, MAGENTA_HANGING_BANNER, LIGHT_BLUE_HANGING_BANNER, YELLOW_HANGING_BANNER,
      LIME_HANGING_BANNER, PINK_HANGING_BANNER, GRAY_HANGING_BANNER, LIGHT_GRAY_HANGING_BANNER, CYAN_HANGING_BANNER, PURPLE_HANGING_BANNER,
      BLUE_HANGING_BANNER, BROWN_HANGING_BANNER, GREEN_HANGING_BANNER, RED_HANGING_BANNER, BLACK_HANGING_BANNER
    ).build()
  );

  public static Block getSidewaysBannerBlock(DyeColor color) {
    return switch (color) {
      case WHITE -> WHITE_SIDEWAYS_BANNER;
      case ORANGE -> ORANGE_SIDEWAYS_BANNER;
      case MAGENTA -> MAGENTA_SIDEWAYS_BANNER;
      case LIGHT_BLUE -> LIGHT_BLUE_SIDEWAYS_BANNER;
      case YELLOW -> YELLOW_SIDEWAYS_BANNER;
      case LIME -> LIME_SIDEWAYS_BANNER;
      case PINK -> PINK_SIDEWAYS_BANNER;
      case GRAY -> GRAY_SIDEWAYS_BANNER;
      case LIGHT_GRAY -> LIGHT_GRAY_SIDEWAYS_BANNER;
      case CYAN -> CYAN_SIDEWAYS_BANNER;
      case PURPLE -> PURPLE_SIDEWAYS_BANNER;
      case BLUE -> BLUE_SIDEWAYS_BANNER;
      case BROWN -> BROWN_SIDEWAYS_BANNER;
      case GREEN -> GREEN_SIDEWAYS_BANNER;
      case RED -> RED_SIDEWAYS_BANNER;
      case BLACK -> BLACK_SIDEWAYS_BANNER;
    };
  }

  public static Block getHangingBannerBlock(DyeColor color) {
    return switch (color) {
      case WHITE -> WHITE_HANGING_BANNER;
      case ORANGE -> ORANGE_HANGING_BANNER;
      case MAGENTA -> MAGENTA_HANGING_BANNER;
      case LIGHT_BLUE -> LIGHT_BLUE_HANGING_BANNER;
      case YELLOW -> YELLOW_HANGING_BANNER;
      case LIME -> LIME_HANGING_BANNER;
      case PINK -> PINK_HANGING_BANNER;
      case GRAY -> GRAY_HANGING_BANNER;
      case LIGHT_GRAY -> LIGHT_GRAY_HANGING_BANNER;
      case CYAN -> CYAN_HANGING_BANNER;
      case PURPLE -> PURPLE_HANGING_BANNER;
      case BLUE -> BLUE_HANGING_BANNER;
      case BROWN -> BROWN_HANGING_BANNER;
      case GREEN -> GREEN_HANGING_BANNER;
      case RED -> RED_HANGING_BANNER;
      case BLACK -> BLACK_HANGING_BANNER;
    };
  }

  private static Identifier id(String path) {
    return Identifier.of(MOD_ID, path);
  }

  private static SidewaysBannerBlock registerSidewaysBanner(DyeColor color, String name, Block wallBanner) {
    return Registry.register(Registries.BLOCK, id(name), new SidewaysBannerBlock(color, AbstractBlock.Settings.copy(wallBanner)));
  }

  private static HangingBannerBlock registerHangingBanner(DyeColor color, String name, Block wallBanner) {
    return Registry.register(Registries.BLOCK, id(name), new HangingBannerBlock(color, AbstractBlock.Settings.copy(wallBanner)));
  }

  @Override
  public void onInitialize() {
  }


}