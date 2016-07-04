package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

public class RuinSurvivorTent extends Ruin implements IWorldGenerator
{

   private RuinGenHelper genHelper = new RuinGenHelper();


   public RuinSurvivorTent(String par1Name) {
      super(par1Name);
   }

   public boolean generate(World world, Random random, int x, int y, int z) {
      RuinGenHelper.setWorld(world);
      Rectangle pos = new Rectangle(new BlockPos(x - 2, y, z - 3), 5, 6);
      Block biomeBlock = ModConfig.getSurfaceBlock();
      int biomeBlockMeta = ModConfig.getSurfaceBlockMeta();
      int[] levels = Layout.getLevels(world, pos);
      if(Layout.checkLevel(levels, 0)) {
         int yCoord = Layout.getAverageLevel(levels) - 1;
         RuinGenHelper.setBlock(x - 2, yCoord, z - 3, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 0, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 3, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.chest);
         TileEntityChest chest = (TileEntityChest)world.getTileEntity(new BlockPos(x - 1, yCoord, z));
         LootStack loot = this.setItems(random);
         CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x, yCoord, z - 3, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x, yCoord, z - 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.planks);
         RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.planks);
         RuinGenHelper.setBlock(x, yCoord - 1, z + 0, Blocks.tnt);
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.planks);
         RuinGenHelper.setBlock(x, yCoord, z + 2, biomeBlock);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 3, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 3, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 2, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 0, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 1, biomeBlock, biomeBlockMeta);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 2, biomeBlock, biomeBlockMeta);
         ++yCoord;
         RuinGenHelper.setBlock(x - 2, yCoord, z - 3, Blocks.wool);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.wool);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 1, Blocks.wool);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.wool);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.wool);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 2, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.wooden_pressure_plate);
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 3, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 1, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 0, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 1, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 2, Blocks.wool);
         ++yCoord;
         RuinGenHelper.setBlock(x - 2, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x - 2, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x - 2, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.wool);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.wool);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x + 2, yCoord, z + 2, Blocks.air);
         ++yCoord;
         RuinGenHelper.setBlock(x - 1, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x - 1, yCoord, z + 2, Blocks.air);
         RuinGenHelper.setBlock(x, yCoord, z - 3, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z - 2, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z - 1, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z + 0, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z + 1, Blocks.wool);
         RuinGenHelper.setBlock(x, yCoord, z + 2, Blocks.wool);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 3, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 2, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z - 1, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 0, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 1, Blocks.air);
         RuinGenHelper.setBlock(x + 1, yCoord, z + 2, Blocks.air);
         return true;
      } else {
         return false;
      }
   }
}
