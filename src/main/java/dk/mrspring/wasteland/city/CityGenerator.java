package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.Wasteland;
import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.Layout;
import dk.mrspring.wasteland.utils.Message;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.WastelandWorldData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CityGenerator implements IWorldGenerator
{

   public static List<BlockPos> cityLocation;
   public static int cityNum;
   private static boolean generating = false;
   private boolean loadedWorld;


   public CityGenerator() {
      GameRegistry.registerWorldGenerator(this, 12);
      cityLocation = new ArrayList<BlockPos>();
      cityNum = 0;
      this.loadedWorld = false;
   }

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
      if(world.provider.getDimensionId() == 0 && world.getBiomeGenForCoords(pos) == WastelandBiomes.city && this.loadedWorld && ModConfig.spawnCities) {
         this.generateCity(random, pos, world, chunkGenerator, chunkProvider);
      }

   }

   public void generateCity(Random random, BlockPos pos, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      MultiVector currentLoc = new MultiVector(Layout.getWorldHeight(world, pos));
      if(this.checkDist(currentLoc.pos, (double)(ModConfig.minCityDistance * 16)) && !generating && !world.isRemote) {
         Wasteland.NETWORK.sendToAll(Message.createChatMessage("Generating world structures (please wait)..."));
         Wasteland.NETWORK.sendToAll(Message.createProgressMessage(0, 1));
         generating = true;
         List<MultiVector> chunks = new ArrayList<MultiVector>();
         chunks.add(currentLoc);
         this.addConnectedBiomeChunks(chunks, currentLoc, world);
         MultiVector center = this.getCenterChunk(chunks, world);
         this.limitCitySize(chunks, center, ModConfig.maxCitySize * 16);
         if(chunks.size() > 0) {
            System.out.println("Generating City at X:" + center.toString() + " Size: " + chunks.size());
            List buildingSchematics = SchematicBuilding.loadAllBuildings();
            LootStack[] loot = LootStack.loadCityLoot();
            RuinedCity city = new RuinedCity(world, center, chunks, random);
            city.generate(world, random, buildingSchematics, loot);
            buildingSchematics.clear();
            cityLocation.add(center.pos);
            ++cityNum;
         }

         Wasteland.NETWORK.sendToAll(Message.createProgressMessage(1, 1));
         Wasteland.NETWORK.sendToAll(Message.createChatMessage("...done"));
         generating = false;
      }

   }

   private void limitCitySize(List<MultiVector> allChunks, MultiVector centerChunk, int maxChunkDist) {
      for(int i = allChunks.size() - 1; i >= 0; --i) {
         if(Vector.VtoVlengthXZ(centerChunk.pos, allChunks.get(i).pos) > (double)maxChunkDist) {
            allChunks.remove(i);
         }
      }

   }

   private MultiVector getCenterChunk(List<MultiVector> chunks, World world) {
      int maxX = chunks.get(0).pos.getX();
      int minX = maxX;
      int maxZ = chunks.get(0).pos.getZ();
      int minZ = maxZ;

      int cX;
      for(cX = 1; cX < chunks.size(); ++cX) {
         maxX = chunks.get(cX).pos.getX() > maxX?chunks.get(cX).pos.getX():maxX;
         minX = chunks.get(cX).pos.getX() < minX?chunks.get(cX).pos.getX():minX;
         maxZ = chunks.get(cX).pos.getZ() > maxZ?chunks.get(cX).pos.getZ():maxZ;
         minZ = chunks.get(cX).pos.getZ() < minZ?chunks.get(cX).pos.getZ():minZ;
      }

      cX = (maxX - minX) / 2 + minX & -16;
      int cZ = (maxZ - minZ) / 2 + minZ & -16;

      for(int i = 0; i < chunks.size(); ++i) {
         MultiVector v = chunks.get(i);
         if(v.pos.getX() == cX && v.pos.getZ() == cZ) {
            return v;
         }
      }

      System.out.println("Center not found");
      return null;
   }

   private void addConnectedBiomeChunks(List<MultiVector> chunks, MultiVector position, World world) {
      int biomeID = world.getBiomeGenForCoords(position.pos).biomeID;
      MultiVector[] newChunks = new MultiVector[]{null, null, null, null};
      boolean containsChunk = false;

      int i;
      for(i = 0; i < 4; ++i) {
         MultiVector current = this.chooseChunk(i, position);
         if(world.getBiomeGenForCoords(current.pos).biomeID == biomeID) {
            for(int j = 0; j < chunks.size() && !containsChunk; ++j) {
               if(chunks.get(j).equalsXZ(current)) {
                  containsChunk = true;
               }
            }

            if(!containsChunk) {
               current.pos = Layout.getWorldHeight(world, current.pos);
               newChunks[i] = current.copy();
               chunks.add(newChunks[i]);
            } else {
               newChunks[i] = null;
            }

            containsChunk = false;
         }
      }

      for(i = 0; i < 4; ++i) {
         if(newChunks[i] != null) {
            this.addConnectedBiomeChunks(chunks, newChunks[i], world);
         }
      }

   }

   private MultiVector chooseChunk(int i, MultiVector position) {
      MultiVector pos;
      if(i == 0) {
         pos = new MultiVector(position.pos.add(16, 0, 0));
      } else if(i == 1) {
         pos = new MultiVector(position.pos.add(-16, 0, 0));
      } else if(i == 2) {
         pos = new MultiVector(position.pos.add(0, 0, 16));
      } else {
         pos = new MultiVector(position.pos.add(0, 0, -16));
      }

      return pos;
   }

   private boolean checkDist(BlockPos current, double distance) {
      for(int i = 0; i < cityLocation.size(); ++i) {
         if(Vector.VtoVlength(current, (BlockPos) cityLocation.get(i)) < distance) {
            return false;
         }
      }

      return true;
   }

   public void resetData() {
      generating = false;
      cityNum = 0;
      cityLocation.clear();
      this.loadedWorld = true;
   }

   public void loadData(List<BlockPos> villageLoc, int size) {
      cityLocation = villageLoc;
      cityNum = size;
      this.loadedWorld = true;
   }

   public void saveData(WastelandWorldData data) {
      data.saveCityData(cityLocation);
   }

}
