//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.items;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.PropertyFloat;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockRadFluid extends BlockFluidClassic
{
    public BlockRadFluid(Fluid fluid, Material material)
    {
        super(fluid, material);
    }

    //    @SideOnly(Side.CLIENT)
//    protected IIcon stillIcon;
//    @SideOnly(Side.CLIENT)
//    protected IIcon flowingIcon;
//    private long tickedWorldTime;

//    public BlockRadFluid(Fluid fluid, Material material)
//    {
//        super(fluid, material);
//    }

//    public IIcon getIcon(int side, int meta)
//    {
//        return side != 0 && side != 1 ? this.flowingIcon : this.stillIcon;
//    }

//    @SideOnly(Side.CLIENT)
//    public void registerBlockIcons(IIconRegister register)
//    {
//        this.stillIcon = register.registerIcon("WLM:toxic_still");
//        this.flowingIcon = register.registerIcon("WLM:toxic_flow");
//    }

//    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
//    {
//        return world.getBlock(x, y, z).getMaterial().isLiquid() ? false : super.canDisplace(world, x, y, z);
//    }
//
//    public boolean displaceIfPossible(World world, int x, int y, int z)
//    {
//        return world.getBlock(x, y, z).getMaterial().isLiquid() ? false : super.displaceIfPossible(world, x, y, z);
//    }
//
//    public void onEntityCollidedWithBlock(World world, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity entity)
//    {
//        if (!world.isRemote && entity instanceof EntityLivingBase && (world.getWorldTime() & 10L) == 0L && !((EntityLivingBase) entity).isPotionActive(Potion.poison.id))
//        {
//            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, 205, 0, false));
//        }
//
//    }
}
