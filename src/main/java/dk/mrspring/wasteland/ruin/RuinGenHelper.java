package dk.mrspring.wasteland.ruin;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RuinGenHelper {

   protected static World worldObj;


   public static void setCube(int baseX, int baseY, int baseZ, int lengthX, int lengthY, int lengthZ, Block block) {
      for(int y = 0; y < lengthY; ++y) {
         for(int z = 0; z < lengthX; ++z) {
            for(int x = 0; x < lengthZ; ++x) {
               setBlock(x + baseX, y + baseY, z + baseZ, block);
            }
         }
      }

   }

   public static void setBlock(int x, int z, Block block) {
      setBlock(x, z, block, 0);
   }

   public static void setBlock(int x, int z, Block block, int meta) {
      setBlock(worldObj.getHeight(new BlockPos(x, 0, z)), block, meta);
   }

   public static void setBlock(int x, int y, int z, Block block) {
      setBlock(x, y, z, block, 0);
   }

   public static void setBlock(int x, int y, int z, Block block, int meta) {
      setBlock(new BlockPos(x, y, z), block, meta);
   }

   public static void setBlock(BlockPos pos, Block block) {
      setBlock(pos, block, 0);
   }

   public static void setBlock(BlockPos pos, Block block, int meta) {
      worldObj.setBlockState(pos, block.getStateFromMeta(meta), 2);
   }

   public static Block getBlock(int x, int y, int z) {
      return getBlock(new BlockPos(x, y, z));
   }

   public static Block getBlock(BlockPos pos) {
      return worldObj.getBlockState(pos).getBlock();
   }

   public static int getHeightValue(int x, int z) {
      return worldObj.getHeight(new BlockPos(x, 0, z)).getY();
   }

   public static void setWorld(World world) {
      worldObj = world;
   }
}
