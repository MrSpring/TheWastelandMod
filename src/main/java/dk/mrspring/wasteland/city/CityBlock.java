package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.city.CityBuilding;
import dk.mrspring.wasteland.city.MultiVector;
import dk.mrspring.wasteland.city.RuinedCity;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class CityBlock {

   Rectangle area;
   int connections;
   boolean[] connectedFaces;
   boolean doGenerate;
   public String buildingName;
   public int[] cornerHeight;
   public int buildingHeight;
   CityBlock[] connectedBlocks;
   private RuinGenHelper genHelper = new RuinGenHelper();


   public CityBlock(Rectangle area) {
      this.area = area;
      int perimiter = (area.width / 16 + area.length / 16) * 2;
      this.connectedBlocks = new CityBlock[perimiter];
      this.cornerHeight = new int[perimiter];

      for(int i = 0; i < perimiter; ++i) {
         this.connectedBlocks[i] = null;
         this.cornerHeight[i] = 0;
      }

      this.doGenerate = true;
   }

   public void setConnectedCityBlock(CityBlock block, int index) {
      if(index < this.connectedBlocks.length) {
         this.connectedBlocks[index] = block;
      } else {
         System.out.println("City block does not have side: " + String.valueOf(index));
      }

   }

   public void setCorners(MultiVector position) {}

   public int[] heightVariation() {
      boolean diff = false;
      int[] out = new int[]{0, 0, 0};
      int max = this.cornerHeight[0];
      int min = max;

      for(int i = 0; i < this.cornerHeight.length; ++i) {
         max = this.cornerHeight[i] > max?this.cornerHeight[i]:max;
         min = this.cornerHeight[i] < min?this.cornerHeight[i]:min;
         int var6;
         if(i == this.cornerHeight.length - 1) {
            var6 = this.cornerHeight[0] - this.cornerHeight[i];
         } else {
            var6 = this.cornerHeight[i + 1] - this.cornerHeight[i];
         }

         out[0] = var6 > out[0]?var6:out[0];
      }

      out[1] = max - min;
      out[2] = (int)((float)(max + min) / 2.0F);
      return out;
   }

   public void reduceVariation(int average, int p, boolean[] forcedHeight) {
      for(int i = 0; i < this.cornerHeight.length; ++i) {
         if(!forcedHeight[i]) {
            if(this.cornerHeight[i] > average) {
               this.cornerHeight[i] -= p;
            } else if(this.cornerHeight[i] < average) {
               this.cornerHeight[i] += p;
            }
         }
      }

   }

   public Vector getPositionFromCorner(int index) {
      int w = this.area.width / 16;
      int l = this.area.length / 16;
      if(index >= 2 * (w + l)) {
         System.out.println("Index excedes number of corners");
         return null;
      } else {
         int x;
         int z;
         if(index <= w) {
            x = index * 16;
            z = this.area.length;
         } else if(index < w + l && index > w) {
            x = this.area.width;
            z = this.area.length - (index - w) * 16;
         } else if(index >= w + l && index <= 2 * w + l) {
            x = this.area.width - (index - w - l) * 16;
            z = 0;
         } else {
            x = 0;
            z = (index - w - w - l) * 16;
         }

         return new Vector(this.area.position.X + x, this.area.position.Y, this.area.position.Z + z);
      }
   }

   public int getCornerFromPosition(Vector pos) {
      int dX = pos.X - this.area.position.X;
      int dZ = pos.Z - this.area.position.Z;
      if(dX >= 0 && dZ >= 0 && dX <= this.area.width && dZ <= this.area.length) {
         int w = this.area.width / 16;
         int l = this.area.length / 16;
         dX /= 16;
         dZ /= 16;
         return w == 2 && l == 2?(dZ == 2?dX:(dZ == 1?(dX == 2?3:7):6 - dX)):(w == 2 && l == 1?(dZ == 1?dX:5 - dX):(w == 1 && l == 2?(dZ == 2?dX:(dZ == 1?(dX == 1?2:5):4 - dX)):(dZ == 1?dX:3 - dX)));
      } else {
         System.out.println("Corner is outside the block");
         return -1;
      }
   }

   public void generate(World world, Random random, List buildingSchematics, LootStack[] loot, int cityColour) {
      RuinGenHelper var10000 = this.genHelper;
      RuinGenHelper.setWorld(world);
      this.generateBase(world, random);
      CityBuilding building = CityBuilding.create(this.buildingName, random.nextInt(4) + 2, random, buildingSchematics, loot);
      if(building != null) {
         int damageNodes = building.height / 15;
         damageNodes = damageNodes > 0?random.nextInt(damageNodes) + 1:1;
         int damageMaxRad = (building.width + building.length) / 6;
         int damageMinRad = (building.width + building.length) / 8;
         building.damageBuilding(damageNodes, damageMaxRad, damageMinRad, random);
         boolean rot = false;
         int rot1;
         if(this.area.width == 32) {
            if(this.area.length == 32) {
               rot1 = random.nextInt(4);
            } else {
               rot1 = random.nextInt(2) * 2;
               rot1 = building.width > building.length?rot1:rot1 + 1;
            }
         } else if(this.area.length == 32) {
            rot1 = random.nextInt(2) * 2;
            rot1 = building.length > building.width?rot1:rot1 + 1;
         } else {
            rot1 = random.nextInt(4);
         }

         int offsetX;
         int offsetZ;
         if(rot1 != 0 && rot1 != 2) {
            offsetX = (this.area.width - building.length) / 2;
            offsetZ = (this.area.length - building.width) / 2;
         } else {
            offsetX = (this.area.width - building.width) / 2;
            offsetZ = (this.area.length - building.length) / 2;
         }

         building.generate(world, random, this.area.position.copy().add(new Vector(offsetX, 0, offsetZ)), rot1, cityColour);
      }

   }

   private void generateBase(World world, Random random) {
      byte roadWidth = 2;
      Block surfaceBlock = ModConfig.getSurfaceBlock();
      int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();

      int rubble;
      int i;
      for(rubble = roadWidth; rubble < this.area.length - roadWidth; ++rubble) {
         for(i = roadWidth; i < this.area.width - roadWidth; ++i) {
            RuinGenHelper var10000 = this.genHelper;
            RuinGenHelper.setBlock(this.area.position.X + i, this.area.position.Y, this.area.position.Z + rubble, surfaceBlock, surfaceBlockMeta);
            RuinedCity.clearAbove(this.area.position.X + i, this.area.position.Y + 1, this.area.position.Z + rubble, 5, world);
            RuinedCity.fillBelow(this.area.position.X + i, this.area.position.Y - 1, this.area.position.Z + rubble, 3, world);
         }
      }

      rubble = this.area.length * this.area.width / 10;

      for(i = 0; i < rubble; ++i) {
         int x = random.nextInt(this.area.width - 2 * roadWidth) + roadWidth;
         int z = random.nextInt(this.area.length - 2 * roadWidth) + roadWidth;
         if(random.nextInt(10) > 0) {
            int type = random.nextInt(10);
            Block b = type == 0?Blocks.cobblestone:(type == 1?Blocks.stonebrick:(type == 2?Blocks.mossy_cobblestone:(type == 3?Blocks.glass:(type == 4?Blocks.gravel:surfaceBlock))));
            int m = type == 1?random.nextInt(3):(type > 4?surfaceBlockMeta:0);
            world.setBlock(this.area.position.X + x, this.area.position.Y + 1, this.area.position.Z + z, b, m, 2);
         } else {
            world.setBlock(this.area.position.X + x, this.area.position.Y, this.area.position.Z + z, Blocks.air, 0, 2);
         }
      }

   }
}
