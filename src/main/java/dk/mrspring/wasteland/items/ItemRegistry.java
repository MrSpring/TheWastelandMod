package dk.mrspring.wasteland.items;

import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

   public static Fluid radiationWaste;
   public static BlockFluidClassic radiationWasteBlock;
   public static ItemBucket radiationWasteBucket;


   public ItemRegistry() {
      radiationWaste = new Fluid("toxicWasteFluid", new ResourceLocation("WLM", "blocks/toxic_still"), new ResourceLocation("WLM", "blocks/toxic_flow"))
              .setLuminosity(10).setDensity(1000).setViscosity(1500).setBlock(radiationWasteBlock);
      this.registerFluids();

      radiationWasteBlock = new BlockRadFluid(radiationWaste, Material.water);//(BlockRadFluid)(new BlockRadFluid(radiationWaste, Material.water)).setBlockName("toxicWasteBlock");

      this.registerBlocks();
      radiationWasteBucket = (ItemBucket)(new ItemBucket(radiationWasteBlock)).setRegistryName("WLM", "toxicWasteBucket").setUnlocalizedName("toxicWasteBucket");
      radiationWasteBucket.setContainerItem(Items.bucket);
      radiationWasteBucket.setCreativeTab(CreativeTabs.tabMisc);
      this.registerItems();
   }

   public void registerFluids() {
      FluidRegistry.registerFluid(radiationWaste);
   }

   public void registerBlocks() {
      GameRegistry.registerBlock(radiationWasteBlock);
   }

   public void registerItems() {
      GameRegistry.registerItem(radiationWasteBucket);
   }
}
