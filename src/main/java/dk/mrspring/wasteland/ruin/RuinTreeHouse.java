package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RuinTreeHouse extends Ruin implements IWorldGenerator
{

   public RuinTreeHouse(String par1Name) {
      super(par1Name);
   }

   public boolean generate(World world, Random random, int x, int y, int z) {
      RuinGenHelper.setWorld(world);
      if(RuinGenHelper.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock())) {
         int yCoord = RuinGenHelper.getHeightValue(x, z) - 1;
         int var1 = RuinGenHelper.getHeightValue(x - 1, z - 1);
         RuinGenHelper.setBlock(x - 1, var1, z - 1, Blocks.planks);
         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         if(!RuinGenHelper.getBlock(x, yCoord, z - 1).getMaterial().isSolid()) {
            RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
         }

         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         RuinGenHelper.setBlock(x - 1, yCoord, z, Blocks.log);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.log);
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
         ++yCoord;
         RuinGenHelper.setBlock(x + 0, yCoord, z + 0, Blocks.log);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.log);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.log);
         RuinGenHelper.setBlock(x + 0, yCoord, z - 1, Blocks.ladder, 2);
         RuinGenHelper.setBlock(x + 0, yCoord, z + 1, Blocks.planks);
         RuinGenHelper.setBlock(x + 0, yCoord, z + 2, Blocks.log);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.log);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.planks);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.planks);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.planks);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.log);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.chest);
         TileEntityChest chest = (TileEntityChest)world.getTileEntity(new BlockPos(x - 1, yCoord, z + 1));
         LootStack loot = this.setItems(random);
         CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.log);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.log);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.log);
         ++yCoord;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.log, 8);
         return true;
      } else {
         return false;
      }
   }
}
