package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.ruin.Building;
import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class Layout {

   public Rectangle cPos;
   public Rectangle[] bPos;
   public int[] bRot;
   public BlockPos centerPos;
   public int villageDim;
   public int villageSize;


   public Layout(World world, Random rand, Building center, Building[] structures, BlockPos vPos, int dim, int size) {
      this.centerPos = vPos;
      this.villageDim = dim;
      this.villageSize = size;
      int i;
      if(center != null) {
         i = (int)((float)center.width / 2.0F);
         int offsetZ = (int)((float)center.length / 2.0F);
         this.cPos = new Rectangle(
                 new BlockPos(vPos.getX() - i, world.getHeight(vPos.add(-i, 0, -offsetZ)).getY() - 1, vPos.getZ() - offsetZ),
                 center.width, center.length
         );
      } else {
         this.cPos = null;
      }

      this.bPos = new Rectangle[structures.length];

      for(i = 0; i < this.bPos.length; ++i) {
         this.bPos[i] = this.pickBestPos(world, rand, structures[i]);
      }

   }

   private Rectangle pickBestPos(World world, Random rand, Building b) {
      BlockPos cent = getWorldHeight(world, centerPos);
      short maxTries = 1000;

      for(int i = 0; i < maxTries; ++i) {
         boolean flag = true;
         byte rot = 0;
         Rectangle pos = (new Rectangle(Vector.randomVector2D(rand, this.villageDim / 2, this.villageSize < 2?4:6).add(cent), b.width, b.length)).rotate(rot);
         if(this.cPos != null && Rectangle.checkConflict(pos, this.cPos, 2)) {
            flag = false;
         }

         for(int j = 0; j < this.bPos.length; ++j) {
            if(this.bPos[j] != null && Rectangle.checkConflict(pos, this.bPos[j], 2)) {
               flag = false;
            }
         }

         int[] levels = getLevels(world, pos);
         if(flag && checkLevel(levels, b.width < 8 && b.length < 8?0:1)) {
            pos.position = new BlockPos(pos.position.getX(), getAverageLevel(levels) - 1, pos.position.getZ());
            return pos;
         }
      }

      System.out.println("Skipping building");
      return null;
   }

   public static int[] getLevels(World world, Rectangle rect) {
      int[] height = new int[rect.length * rect.width];

      for(int j = 0; j < rect.length; ++j) {
         for(int i = 0; i < rect.width; ++i) {
            height[j * rect.width + i] = getWorldHeight(world, rect.position.add(i, 0, j)).getY();
         }
      }

      return height;
   }

   public static boolean checkLevel(int[] levels, int maxVar) {
      int maxHeight = levels[0];
      int minHeight = maxHeight;

      for(int j = 0; j < levels.length; ++j) {
         maxHeight = levels[j] > maxHeight?levels[j]:maxHeight;
         minHeight = levels[j] < minHeight?levels[j]:minHeight;
      }

      return maxHeight - minHeight <= maxVar;
   }

   public static int getAverageLevel(int[] levels) {
      float level = 0.0F;

      for(int j = 0; j < levels.length; ++j) {
         level += (float)levels[j];
      }

      return Math.round(level / (float)levels.length);
   }

   public static int getMinLevel(int[] levels) {
      if(levels != null && levels.length > 0) {
         int min = levels[0];

         for(int i = 0; i < levels.length; ++i) {
            min = levels[i] < min?levels[i]:min;
         }

         return min;
      } else {
         return -1;
      }
   }

   public static BlockPos getWorldHeight(World world, BlockPos pos) {
      BlockPos worldHeight = world.getHeight(pos);
      if(worldHeight.getY() == 0) {
         Chunk block = world.getChunkFromBlockCoords(pos);
         world.getChunkProvider().provideChunk(block.xPosition, block.zPosition);
         worldHeight = world.getHeight(pos);
      }

      Block var5 = world.getBlockState(worldHeight).getBlock();
      if(worldHeight.getY() == 0) {
         System.out.println("World height still 0");
      } else if(var5.equals(Blocks.deadbush)) {
         worldHeight.add(0, -1, 0);
      }

      return worldHeight;
   }
}
