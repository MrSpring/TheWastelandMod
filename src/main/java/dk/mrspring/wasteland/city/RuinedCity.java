package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.Wasteland;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.Layout;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.utils.Message;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandBigTree;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandClay;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandLake;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RuinedCity {

   CityBlockLayout layout;


   public RuinedCity(World world, MultiVector center, List chunks, Random random) {
      this.layout = new CityBlockLayout(chunks, center, random, world);
   }

   public void generate(World world, Random random, List buildingSchematics, LootStack[] loot) {
      this.generateCityRoads(world, random);
      int cityColour = random.nextInt(16);
      int citySize = this.layout.block.size();

      for(int i = 0; i < citySize; ++i) {
         CityBlock generatingBlock = (CityBlock)this.layout.block.get(i);
         if(generatingBlock.doGenerate) {
            generatingBlock.generate(world, random, buildingSchematics, loot, cityColour);
         } else {
            boolean large = generatingBlock.area.length == 32 && (large = generatingBlock.area.width == 32);
            boolean genLake = large?random.nextInt(4) > 0:random.nextInt(4) == 0;
            int trees = large?random.nextInt(2) + 1:1;
            byte w = 10;

            for(int totalTrees = 0; totalTrees < 50 && genLake; ++totalTrees) {
               BlockPos pos = Layout.getWorldHeight(world, new BlockPos(
                       random.nextInt(w + 1) - w / 2 + (random.nextInt(2) == 0?generatingBlock.area.width - 8:8) + generatingBlock.area.position.getX(),
                       0,
                       random.nextInt(w + 1) - w / 2 + (random.nextInt(2) == 0?generatingBlock.area.length - 8:8) + generatingBlock.area.position.getZ())
               );
               WorldGenWastelandLake y = new WorldGenWastelandLake(random.nextInt(5) == 0?Blocks.water:ModConfig.getlakeLiquid());
               WorldGenWastelandClay tree = new WorldGenWastelandClay(3);
               genLake = !y.generate(world, random, pos);
               if(!genLake && random.nextInt(6) < 5) {
                  tree.generate(world, random, pos);
               }
            }

            int totalTrees = 0;
            for(int t = 0; t < 50 && totalTrees < trees; ++t) {
               BlockPos pos = Layout.getWorldHeight(world, new BlockPos(
                       random.nextInt(w + 1) - w / 2 + (random.nextInt(2) == 0?generatingBlock.area.width - 8:8) + generatingBlock.area.position.getX(),
                       0,
                       random.nextInt(w + 1) - w / 2 + (random.nextInt(2) == 0?generatingBlock.area.length - 8:8) + generatingBlock.area.position.getZ()
               ));
               WorldGenWastelandBigTree var20 = new WorldGenWastelandBigTree(true);
               totalTrees = var20.generate(world, random, pos)?totalTrees + 1:totalTrees;
            }
         }

         if(i != citySize) {
            Wasteland.NETWORK.sendToAll(Message.createProgressMessage(i + 1, citySize + 1));
         }
      }

   }

   private void generateCityRoads(World world, Random r) {
      byte roadWidth = 2;
      byte hOffset = 0;

      RuinGenHelper.setWorld(world);

      for(int b = 0; b < this.layout.block.size(); ++b) {
         CityBlock block = (CityBlock)this.layout.block.get(b);
         int w = block.area.width / 16;
         int l = block.area.length / 16;
         int x = block.area.position.getX();
         int z = block.area.position.getZ();

         int y1;
         int y2;
         int i;
         int j;
         for(i = 0; i < w; ++i) {
            if(block.connectedFaces[i]) {
               for(j = 0; j < roadWidth; ++j) {
                  y1 = block.cornerHeight[i];
                  y2 = block.cornerHeight[i + 1];
                  this.generateRoad(world, x + i * 16 - roadWidth, z + block.area.length - 1 - j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
               }
            }

            if(block.connectedFaces[2 * w + l - i - 1]) {
               for(j = 0; j < roadWidth; ++j) {
                  y1 = block.cornerHeight[2 * w + l - i];
                  y2 = block.cornerHeight[2 * w + l - i - 1];
                  this.generateRoad(world, x + i * 16 - roadWidth, z + j, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, true, r);
               }
            }
         }

         for(i = 0; i < l; ++i) {
            if(block.connectedFaces[w + l - i - 1]) {
               for(j = 0; j < roadWidth; ++j) {
                  y1 = block.cornerHeight[w + l - i];
                  y2 = block.cornerHeight[w + l - i - 1];
                  this.generateRoad(world, x + block.area.width - 1 - j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
               }
            }

            if(block.connectedFaces[2 * w + l + i]) {
               for(j = 0; j < roadWidth; ++j) {
                  y1 = block.cornerHeight[2 * w + l + i];
                  if(2 * w + l + i + 1 >= block.cornerHeight.length) {
                     y2 = block.cornerHeight[0];
                  } else {
                     y2 = block.cornerHeight[2 * w + l + i + 1];
                  }

                  this.generateRoad(world, x + j, z + i * 16 - roadWidth, y1 + hOffset, y2 + hOffset, 16 + roadWidth * 2, false, r);
               }
            }
         }
      }

   }

   private void generateRoad(World world, int x, int z, int y1, int y2, int length, boolean dir, Random r) {
      byte odds = 10;
      Block roadBlock = Blocks.stained_hardened_clay;
      Block surfaceBlock = ModConfig.getSurfaceBlock();
      int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();
      int i;
      boolean f;
      if(dir) {
         for(i = 0; i < length; ++i) {
            f = r.nextInt(odds) == 0;
            if(i < length / 2) {
               RuinGenHelper.setBlock(x + i, y1, z, f?surfaceBlock:roadBlock, f?surfaceBlockMeta:15);
               clearAbove(x + i, y1 + 1, z, 5, world);
               fillBelow(x + i, y1 - 1, z, 3, world);
            } else {
               RuinGenHelper.setBlock(x + i, y2, z, f?surfaceBlock:roadBlock, f?surfaceBlockMeta:15);
               clearAbove(x + i, y2 + 1, z, 5, world);
               fillBelow(x + i, y2 - 1, z, 3, world);
            }
         }
      } else {
         for(i = 0; i < length; ++i) {
            f = r.nextInt(odds) == 0;
            if(i < length / 2) {
               RuinGenHelper.setBlock(x, y1, z + i, f?surfaceBlock:roadBlock, f?surfaceBlockMeta:15);
               clearAbove(x, y1 + 1, z + i, 5, world);
               fillBelow(x, y1 - 1, z + i, 3, world);
            } else {
               RuinGenHelper.setBlock(x, y2, z + i, f?surfaceBlock:roadBlock, f?surfaceBlockMeta:15);
               clearAbove(x, y2 + 1, z + i, 5, world);
               fillBelow(x, y2 - 1, z + i, 3, world);
            }
         }
      }

   }

   public static void clearAbove(int x, int y, int z, int d, World world) {
      for(int i = 0; i < d; ++i) {
         if(RuinGenHelper.getBlock(x, y + i, z) != Blocks.air) {
            RuinGenHelper.setBlock(x, y + i, z, Blocks.air, 0);
         }
      }

   }

   public static void fillBelow(int x, int y, int z, int d, World world) {
      for(int i = 0; i < d; ++i) {
         Block b = RuinGenHelper.getBlock(x, y - i, z);
         if(b == Blocks.air || b == Blocks.deadbush) {
            RuinGenHelper.setBlock(x, y - i, z, Blocks.stone, 0);
         }
      }

   }
}
