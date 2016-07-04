package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.config.ModConfig;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWastelandLake extends WorldGenerator {

   private Block field_150556_a;
   private static final String __OBFID = "CL_00000418";


   public WorldGenWastelandLake(Block p_i45455_1_) {
      this.field_150556_a = p_i45455_1_;
   }

   public boolean generate(World world, Random random, BlockPos pos) {

      for(pos.add(-8, 0, -8); pos.getY() > 5 && world.isAirBlock(pos); pos.add(0, -1, 0)) {
         ;
      }

      if(pos.getY() <= 4) {
         return false;
      } else {
         pos.add(0, -4, 0);
         boolean[] aboolean = new boolean[2048];
         int l = random.nextInt(4) + 4;

         int i1;
         for(i1 = 0; i1 < l; ++i1) {
            double j1 = random.nextDouble() * 6.0D + 3.0D;
            double flag = random.nextDouble() * 4.0D + 2.0D;
            double d2 = random.nextDouble() * 6.0D + 3.0D;
            double d3 = random.nextDouble() * (16.0D - j1 - 2.0D) + 1.0D + j1 / 2.0D;
            double d4 = random.nextDouble() * (8.0D - flag - 4.0D) + 2.0D + flag / 2.0D;
            double d5 = random.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

            for(int k1 = 1; k1 < 15; ++k1) {
               for(int l1 = 1; l1 < 15; ++l1) {
                  for(int i2 = 1; i2 < 7; ++i2) {
                     double d6 = ((double)k1 - d3) / (j1 / 2.0D);
                     double d7 = ((double)i2 - d4) / (flag / 2.0D);
                     double d8 = ((double)l1 - d5) / (d2 / 2.0D);
                     double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                     if(d9 < 1.0D) {
                        aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                     }
                  }
               }
            }
         }

         int j2;
         int var32;
         boolean var33;
         for(i1 = 0; i1 < 16; ++i1) {
            for(j2 = 0; j2 < 16; ++j2) {
               for(var32 = 0; var32 < 8; ++var32) {
                  var33 = !aboolean[(i1 * 16 + j2) * 8 + var32] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + var32] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + var32] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + var32] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + var32] || var32 < 7 && aboolean[(i1 * 16 + j2) * 8 + var32 + 1] || var32 > 0 && aboolean[(i1 * 16 + j2) * 8 + (var32 - 1)]);
                  if(var33) {
                     Material b0 = world.getBlockState(pos.add(i1, var32, j2)).getBlock().getMaterial();
                     if(var32 >= 4 && b0.isLiquid()) {
                        return false;
                     }

                     if(var32 < 4 && !b0.isSolid() && world.getBlockState(pos.add(i1, var32, j2)).getBlock() != this.field_150556_a) {
                        return false;
                     }
                  }
               }
            }
         }

         for(i1 = 0; i1 < 16; ++i1) {
            for(j2 = 0; j2 < 16; ++j2) {
               for(var32 = 0; var32 < 8; ++var32) {
                  if(aboolean[(i1 * 16 + j2) * 8 + var32]) {
                     world.setBlockState(pos.add(i1, var32, j2), var32 >= 4?Blocks.air.getDefaultState():this.field_150556_a.getDefaultState(), 2);
                  }
               }
            }
         }

         for(i1 = 0; i1 < 16; ++i1) {
            for(j2 = 0; j2 < 16; ++j2) {
               for(var32 = 4; var32 < 8; ++var32) {
                  if(aboolean[(i1 * 16 + j2) * 8 + var32] && world.getBlockState(pos.add(i1, var32 - 1, j2)).getBlock() == ModConfig.getSurfaceBlock() && world.getLightFor(EnumSkyBlock.SKY, pos.add(i1, var32, j2)) > 0) {
                     world.setBlockState(pos.add(i1, var32 - 1, j2), ModConfig.getSurfaceBlock().getDefaultState(), 2);
                  }
               }
            }
         }

         if(this.field_150556_a.getMaterial() == Material.lava) {
            for(i1 = 0; i1 < 16; ++i1) {
               for(j2 = 0; j2 < 16; ++j2) {
                  for(var32 = 0; var32 < 8; ++var32) {
                     var33 = !aboolean[(i1 * 16 + j2) * 8 + var32] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + var32] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + var32] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + var32] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + var32] || var32 < 7 && aboolean[(i1 * 16 + j2) * 8 + var32 + 1] || var32 > 0 && aboolean[(i1 * 16 + j2) * 8 + (var32 - 1)]);
                     if(var33 && (var32 < 4 || random.nextInt(2) != 0) && world.getBlockState(pos.add(i1, var32, j2)).getBlock().getMaterial().isSolid()) {
                        world.setBlockState(pos.add(i1, var32, j2), Blocks.stone.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         if(this.field_150556_a.getMaterial() == Material.water) {
            for(i1 = 0; i1 < 16; ++i1) {
               for(j2 = 0; j2 < 16; ++j2) {
                  byte var34 = 4;
                  if(world.canBlockFreezeWater(pos.add(i1, var34, j2))) {
                     world.setBlockState(pos.add(i1, var34, j2), Blocks.ice.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }
}
