//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ItemRegistry
{
    public static Fluid radiationWaste;
    public static BlockRadFluid radiationWasteBlock;
    public static ItemBucket radiationWasteBucket;

    public ItemRegistry()
    {
        radiationWaste = (new Fluid("toxicWasteFluid")).setLuminosity(10).setDensity(1000).setViscosity(1500);
        this.registerFluids();
        radiationWasteBlock = (BlockRadFluid) (new BlockRadFluid(radiationWaste, Material.water)).setBlockName("toxicWasteBlock");
        this.registerBlocks();
        radiationWasteBucket = (ItemBucket) (new ItemBucket(radiationWasteBlock)).setUnlocalizedName("toxicWasteBucket");
        radiationWasteBucket.setContainerItem(Items.bucket);
        radiationWasteBucket.setTextureName("WLM:bucket_toxic");
        radiationWasteBucket.setCreativeTab(CreativeTabs.tabMisc);
        this.registerItems();
    }

    public void registerFluids()
    {
        FluidRegistry.registerFluid(radiationWaste);
    }

    public void registerBlocks()
    {
        GameRegistry.registerBlock(radiationWasteBlock, radiationWasteBlock.getUnlocalizedName());
    }

    public void registerItems()
    {
        GameRegistry.registerItem(radiationWasteBucket, radiationWasteBucket.getUnlocalizedName());
    }
}
