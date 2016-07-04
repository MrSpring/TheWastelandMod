package dk.mrspring.wasteland.ruin;

import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.Ruin;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.utils.CustomItemStack;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class RuinTreeHouse extends Ruin implements IWorldGenerator {

   private RuinGenHelper genHelper = new RuinGenHelper();


   public RuinTreeHouse(String par1Name) {
      super(par1Name);
   }

   public boolean generate(World world, Random random, int x, int y, int z) {
      RuinGenHelper var10000 = this.genHelper;
      RuinGenHelper.setWorld(world);
      if(world.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock())) {
         int yCoord = world.getHeightValue(x, z) - 1;
         int var1 = world.getHeightValue(x - 1, z - 1);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, var1, z - 1, Blocks.planks);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         if(!world.getBlock(x, yCoord, z - 1).getMaterial().isSolid()) {
            var10000 = this.genHelper;
            RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
         }

         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.ladder, 2);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 0, yCoord, z + 0, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 0, yCoord, z - 1, Blocks.ladder, 2);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 0, yCoord, z + 1, Blocks.planks);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 0, yCoord, z + 2, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.planks);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.planks);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.planks);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.log);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.chest);
         TileEntityChest chest = (TileEntityChest)world.getTileEntity(x - 1, yCoord, z + 1);
         LootStack loot = this.setItems(random);
         CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.log);
         ++yCoord;
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z, Blocks.log);
         var10000 = this.genHelper;
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.log, 8);
         return true;
      } else {
         return false;
      }
   }
}
