package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.ruin.RuinRuined;
import dk.mrspring.wasteland.ruin.RuinRuinedCiv1;
import dk.mrspring.wasteland.ruin.RuinSurvivorTent;
import dk.mrspring.wasteland.ruin.RuinTreeHouse;
import dk.mrspring.wasteland.ruin.RuinVillageGenerator;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.gen.WorldGenRandomFire;
import dk.mrspring.wasteland.world.gen.WorldGenRandomRubble;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandBigTree;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandClay;
import dk.mrspring.wasteland.world.gen.WorldGenWastelandLake;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDecoratorWasteland extends BiomeDecorator {

   public int firePerChunk;
   public int rubblePerChunk;
   public int deadTreePerChunk;
   public WorldGenerator randomFireGen = new WorldGenRandomFire();
   public WorldGenerator randomRubbleGen = new WorldGenRandomRubble();
   public WorldGenWastelandBigTree deadTreeGen = new WorldGenWastelandBigTree(true);
   public WorldGenWastelandLake lakeGen = new WorldGenWastelandLake(ModConfig.getlakeLiquid());
   public WorldGenWastelandClay field_76809_f = new WorldGenWastelandClay(4);
   public RuinTreeHouse treeHouse = new RuinTreeHouse("treeHouse");
   public RuinSurvivorTent tent = new RuinSurvivorTent("tent");
   public RuinRuined temple = new RuinRuined("temple");
   public RuinRuinedCiv1 house = new RuinRuinedCiv1("house");


   public BiomeDecoratorWasteland() {
      this.firePerChunk = ModConfig.randomFirePerChunk;
      this.rubblePerChunk = 1;
      this.deadTreePerChunk = 1;
      super.flowersPerChunk = -999;
      super.grassPerChunk = -999;
      super.deadBushPerChunk = 5;
      super.generateLakes = true;
      super.treesPerChunk = -999;
   }

   public void decorateChunk(World world, Random rand, BiomeGenBase biome, int chunkX, int chunkZ) {
      if(super.currentWorld == null) {
         super.currentWorld = world;
         super.randomGenerator = rand;
         super.chunk_X = chunkX;
         super.chunk_Z = chunkZ;
         this.genDecorations(biome);
         super.currentWorld = null;
         super.randomGenerator = null;
      }

   }

   protected void genDecorations(BiomeGenBase biome) {
      super.genDecorations(biome);
      Random rand = new Random();
      if(biome.biomeID == WastelandBiomes.apocalypse.biomeID) {
         this.decorateWasteland(rand);
      } else if(biome.biomeID == WastelandBiomes.mountains.biomeID) {
         this.decorateMountains(rand);
      } else if(biome.biomeID == WastelandBiomes.forest.biomeID) {
         this.decorateForest(rand);
      } else if(biome.biomeID == WastelandBiomes.radioactive.biomeID) {
         this.decorateRadioactive(rand);
      }

   }

   private void decorateWasteland(Random rand) {
      boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 3) == 0;

      int x;
      int z;
      int pos;
      for(pos = 0; doGen && pos < 1; ++pos) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         int y = super.currentWorld.getHeightValue(x, z);
         this.lakeGen.generate(super.currentWorld, super.randomGenerator, x, y, z);
         if(rand.nextInt(6) < 5) {
            this.field_76809_f.generate(super.currentWorld, super.randomGenerator, x, y, z);
         }
      }

      doGen = true;

      for(pos = 0; doGen && pos < this.firePerChunk; ++pos) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomFireGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 3) == 0;
      if(doGen) {
         Vector var8 = new Vector(super.chunk_X + super.randomGenerator.nextInt(16), 0, super.chunk_Z + super.randomGenerator.nextInt(16));

         for(int i = 0; i < RuinVillageGenerator.villageNum; ++i) {
            doGen = doGen && Vector.VtoVlengthXZ(var8, (Vector)RuinVillageGenerator.villageLocation.get(i)) > 48.0D;
         }

         if(doGen) {
            this.house.generate(super.currentWorld, super.randomGenerator, var8.X, super.currentWorld.getHeightValue(var8.X, var8.Z) - 1, var8.Z);
         }
      }

      doGen = rand.nextInt(ModConfig.wastelandRuinRarirty) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 15) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.deadTreeGen.setTreeType(Blocks.log, 0);
         this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

   }

   private void decorateMountains(Random rand) {
      boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0;

      int x;
      int z;
      for(int i = 0; doGen && i < 1; ++i) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         int y = super.currentWorld.getHeightValue(x, z);
         this.lakeGen.generate(super.currentWorld, super.randomGenerator, x, y, z);
         if(rand.nextInt(6) < 5) {
            this.field_76809_f.generate(super.currentWorld, super.randomGenerator, x, y, z);
         }
      }

      doGen = rand.nextInt(ModConfig.mountainRuinRarity * 2) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16);
         z = super.chunk_Z + super.randomGenerator.nextInt(16);
         this.temple.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.mountainRuinRarity * 3) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 25) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16);
         z = super.chunk_Z + super.randomGenerator.nextInt(16);
         this.deadTreeGen.setTreeType(Blocks.log, 0);
         this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

   }

   private void decorateForest(Random rand) {
      boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0;

      int x;
      int z;
      for(int treesPerChunk = 0; doGen && treesPerChunk < 1; ++treesPerChunk) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         int y = super.currentWorld.getHeightValue(x, z);
         this.lakeGen.generate(super.currentWorld, super.randomGenerator, x, y, z);
         if(rand.nextInt(6) < 5) {
            this.field_76809_f.generate(super.currentWorld, super.randomGenerator, x, y, z);
         }
      }

      doGen = rand.nextInt(ModConfig.forestRuinRarity) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.forestRuinRarity * 3) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16);
         z = super.chunk_Z + super.randomGenerator.nextInt(16);
         this.tent.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z) - 1, z);
      }

      doGen = rand.nextInt(ModConfig.forestRuinRarity * 2) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16);
         z = super.chunk_Z + super.randomGenerator.nextInt(16);
         this.treeHouse.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z) - 1, z);
      }

      doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate) == 0;
      byte var8 = 1;

      for(int i = 0; doGen && i < var8; ++i) {
         x = super.chunk_X + super.randomGenerator.nextInt(16);
         z = super.chunk_Z + super.randomGenerator.nextInt(16);
         this.deadTreeGen.setTreeType(ModConfig.getWoodType(super.randomGenerator));
         this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

   }

   private void decorateRadioactive(Random rand) {
      boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate / 10) == 0;

      int x;
      int z;
      int pos;
      for(pos = 0; doGen && pos < 1; ++pos) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         int y = super.currentWorld.getHeightValue(x, z);
         this.lakeGen.generate(super.currentWorld, super.randomGenerator, x, y, z);
         this.field_76809_f.generate(super.currentWorld, super.randomGenerator, x, y, z);
      }

      doGen = rand.nextInt(4) == 0;

      for(pos = 0; doGen && pos < 1; ++pos) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomFireGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 5) == 0;
      if(doGen) {
         Vector var8 = new Vector(super.chunk_X + super.randomGenerator.nextInt(16), 0, super.chunk_Z + super.randomGenerator.nextInt(16));

         for(int i = 0; i < RuinVillageGenerator.villageNum; ++i) {
            doGen = doGen && Vector.VtoVlengthXZ(var8, (Vector)RuinVillageGenerator.villageLocation.get(i)) > 48.0D;
         }

         if(doGen) {
            this.house.generate(super.currentWorld, super.randomGenerator, var8.X, super.currentWorld.getHeightValue(var8.X, var8.Z) - 1, var8.Z);
         }
      }

      doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 2) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

      doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 75) == 0;
      if(doGen) {
         x = super.chunk_X + super.randomGenerator.nextInt(16) + 8;
         z = super.chunk_Z + super.randomGenerator.nextInt(16) + 8;
         this.deadTreeGen.setTreeType(Blocks.log, 0);
         this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, super.currentWorld.getHeightValue(x, z), z);
      }

   }
}
