package dk.mrspring.wasteland.utils;

import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.util.BlockPos;

import java.util.List;

public class Sphere {

   public BlockPos location;
   public int radius;
   public List grid;


   public Sphere(BlockPos loc, int rad) {
      this.location = loc;
      this.radius = rad;
   }

   public byte[] makeSphereOf(byte[] array, int w, int l, int h, byte replace) {
      int count = 0;

      for(int k = 0; k < h; ++k) {
         for(int j = 0; j < l; ++j) {
            for(int i = 0; i < w; ++i) {
               BlockPos pos = new BlockPos(i, j, k);
               if(Vector.VtoVlength(pos, this.location) <= (double)this.radius) {
                  array[count] = replace;
               }

               ++count;
            }
         }
      }

      return array;
   }
}
