package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RuinBarnHouse extends Ruin implements IWorldGenerator
{

   private RuinGenHelper genHelper = new RuinGenHelper();


   public RuinBarnHouse(String name) {
      super(name);
   }

   public boolean generate(World world, Random random, int x, int y, int z) {
      RuinGenHelper.setWorld(world);
      Material material1 = RuinGenHelper.getBlock(x, y, z).getMaterial();
      int[] var14 = new int[]{x, y, z};
      BlockPos pos = new BlockPos(x, y, z);
      if(material1.isSolid() &&
              world.getHeight(pos.add(1, 0, 1)).getY() == y &&
              world.getHeight(pos.add(0, 0, 1)).getY() == y &&
              world.getHeight(pos.add(-1, 0, 1)).getY() == y &&
              world.getHeight(pos.add(-1, 0, 0)).getY() == y &&
              world.getHeight(pos.add(-1, 0, -1)).getY() == y &&
              world.getHeight(pos.add(0, 0, -1)).getY() == y &&
              world.getHeight(pos.add(1, 0, -1)).getY() == y) {
         RuinGenHelper.setWorld(world);
         int xCoord = x - 6;
         int zCoord = z - 4;
         RuinGenHelper.setCube(xCoord, y + 1, zCoord - 1, 11, 9, 14, Blocks.air);
         int yCoord = y - 1;

         int chest;
         for(chest = 0; chest < 9; ++chest) {
            RuinGenHelper.setBlock(xCoord, yCoord, zCoord + chest, Blocks.cobblestone);
         }

         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 2, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 4, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 7, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 2, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 2, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 4, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 1, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 2, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 3, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 4, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 5, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 6, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 7, Blocks.dirt);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 8, Blocks.cobblestone);

         for(chest = 0; chest < 9; ++chest) {
            RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord, Blocks.cobblestone);
         }

         ++yCoord;
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 6, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 0, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 0, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 0, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 3, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 6, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 0, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 4, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 0, Blocks.oak_fence);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 3, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 6, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 0, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 1, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 2, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 4, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 5, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 7, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 8, Blocks.air);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 8, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 3, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 8, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 3, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 8, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.oak_stairs, 5);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 6, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 6, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.oak_stairs, 4);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 7, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.oak_stairs, 5);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 10, yCoord, zCoord + 5, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 5, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 11, yCoord, zCoord + 6, Blocks.planks);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 7, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 8, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 7, Blocks.chest);
         TileEntityChest var13 = (TileEntityChest)world.getTileEntity(new BlockPos(xCoord + 1, yCoord, zCoord + 7));
         LootStack loot = this.setItems(random);
         CustomItemStack.placeLoot(random, var13, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 8, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 9, yCoord, zCoord + 9, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 4, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 7, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 7, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 6, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 7, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 8, yCoord, zCoord + 8, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 6, Blocks.cobblestone);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.log);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 7, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         ++yCoord;
         RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.cobblestone);
         RuinGenHelper.setBlock(xCoord + 12, yCoord, zCoord + 5, Blocks.cobblestone);
         return true;
      } else {
         return false;
      }
   }

   private ItemStack getChestLoot(Random rand, ItemStack[] items) {
      int i = rand.nextInt(items.length);
      ItemStack item = new ItemStack(items[i].getItem(), 1);
      return item.getItem() == Items.wheat?new ItemStack(item.getItem(), rand.nextInt(8) + 4):(item.getItem() == Items.apple?new ItemStack(item.getItem(), rand.nextInt(2) + 1):(item.getItem() == Items.bread?new ItemStack(item.getItem(), rand.nextInt(2) + 2):new ItemStack(item.getItem(), 1)));
   }
}
