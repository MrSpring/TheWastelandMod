package dk.mrspring.wasteland.items;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockRadFluid extends BlockFluidClassic
{
    public BlockRadFluid(Fluid fluid, Material material)
    {
        super(fluid, material);

        this.setRegistryName("WLM", "toxicWasteBlock");
        this.setUnlocalizedName("toxicWasteBlock");
    }

   /*public BlockRadFluid(Fluid fluid, Material material) {
      super(fluid, material);
   }

   public boolean canDisplace(IBlockAccess world, BlockPos pos) {
      return world.getBlock(x, y, z).getMaterial().isLiquid()?false:super.canDisplace(world, x, y, z);
   }

   public boolean displaceIfPossible(World world, int x, int y, int z) {
      return world.getBlock(x, y, z).getMaterial().isLiquid()?false:super.displaceIfPossible(world, x, y, z);
   }

   public void onEntityCollidedWithBlock(World world, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity) {
      if(!world.isRemote && entity instanceof EntityLivingBase && (world.getWorldTime() & 10L) == 0L && !((EntityLivingBase)entity).isPotionActive(Potion.poison.id)) {
         ((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.poison.id, 205, 0, false));
      }

   }*/
}
