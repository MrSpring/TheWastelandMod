package dk.mrspring.wasteland;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.world.WorldChunkManagerWasteland;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PostModWorldGenerator implements IWorldGenerator
{

   public static int surfaceBlockID = Block.getIdFromBlock(ModConfig.getSurfaceBlock());
   public static int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();
   public static int grassID = Block.getIdFromBlock(Blocks.grass);
   public static int tallGrassID = Block.getIdFromBlock(Blocks.tallgrass);
   public static int deadBushID = Block.getIdFromBlock(Blocks.deadbush);
   public static int radius = ModConfig.forceDisableGrassRadius;
   private static boolean checkingNearChunks = false;


   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      if(world.provider.getDimensionId() == 0 && world.getWorldChunkManager() instanceof WorldChunkManagerWasteland && ModConfig.forceDisableGrass) {
         if(!checkingNearChunks) {
            if(checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ)) {
               checkingNearChunks = true;

               for(int x = 0 - radius; x <= radius; ++x) {
                  for(int z = 0 - radius; z <= radius; ++z) {
                     if(x != 0 || z != 0) {
                        Chunk current = world.getChunkFromChunkCoords(chunkX + x, chunkZ + z);
                        checkReplaceBlocks(current, world, chunkX + x, chunkZ + z);
                     }
                  }
               }

               checkingNearChunks = false;
            }
         } else {
            checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ);
         }
      }

   }

   private static boolean checkReplaceBlocks(Chunk chunk, World world, int chunkX, int chunkZ) {
      boolean blocksExist = false;
      ExtendedBlockStorage[] extStore = chunk.getBlockStorageArray();
      Block surfaceBlock = Block.getBlockById(surfaceBlockID);

      for(int i = 3; extStore != null && i < extStore.length; ++i) {
         ExtendedBlockStorage extB = extStore[i];
         if(extB != null) {
//            byte[] blocks = extB.;
            char[] blocks = extB.getData();

            for(int j = 0; j < blocks.length; ++j) {
               IBlockState state = Block.BLOCK_STATE_IDS.getByValue(blocks[j]);
               if(state.getBlock() == Blocks.grass) {
                  blocks[j] = (char) Block.BLOCK_STATE_IDS.get(surfaceBlock.getStateFromMeta(surfaceBlockMeta));
//                  extB.set(chunkX * 16 + (j & 15), i * 16 + ((j & 3840) >> 8), chunkZ * 16 + ((j & 240) >> 4), surfaceBlock.getStateFromMeta(surfaceBlockMeta));
//                  extB.setExtBlockMetadata(chunkX * 16 + (j & 15), i * 16 + ((j & 3840) >> 8), chunkZ * 16 + ((j & 240) >> 4), surfaceBlockMeta);
                  blocksExist = true;
               } else if(blocks[j] == tallGrassID) {
                  blocks[j] = (char)Block.BLOCK_STATE_IDS.get(Blocks.deadbush.getDefaultState());
               }
            }
         }
      }

      return blocksExist;
   }

}
