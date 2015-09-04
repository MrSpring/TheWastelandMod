//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.world.WorldChunkManagerWasteland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class PostModWorldGenerator implements IWorldGenerator
{
    public static int surfaceBlockID = Block.getIdFromBlock(ModConfig.getSurfaceBlock());
    public static int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();
    public static int grassID;
    public static int tallGrassID;
    public static int deadBushID;
    public static int radius;
    private static boolean checkingNearChunks;
    private static int checkingChunkX;
    private static int checkingChunkZ;

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        // Removed for now. TODO: Re-implement
        /*if (world.provider.getDimensionId() == 0 &&
                world.getWorldChunkManager() instanceof WorldChunkManagerWasteland && ModConfig.forceDisableGrass)
        {
            if (!checkingNearChunks)
            {
                if (checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ))
                {
                    checkingNearChunks = true;

                    for (int x = 0 - radius; x <= radius; ++x)
                    {
                        for (int z = 0 - radius; z <= radius; ++z)
                        {
                            if (x != 0 || z != 0)
                            {
                                Chunk current = world.getChunkFromChunkCoords(chunkX + x, chunkZ + z);
                                checkReplaceBlocks(current, world, chunkX + x, chunkZ + z);
                            }
                        }
                    }

                    checkingNearChunks = false;
                }
            } else
            {
                checkReplaceBlocks(world.getChunkFromChunkCoords(chunkX, chunkZ), world, chunkX, chunkZ);
            }
        }*/

    }

    private static boolean checkReplaceBlocks(Chunk chunk, World world, int chunkX, int chunkZ)
    {
        boolean blocksExist = false;
        /*ExtendedBlockStorage[] extStore = chunk.getBlockStorageArray();

        for (int i = 3; extStore != null && i < extStore.length; ++i)
        {
            ExtendedBlockStorage extB = extStore[i];
            if (extB != null)
            {
                byte[] blocks = extB.get//getBlockLSBArray();

                for (int j = 0; j < blocks.length; ++j)
                {
                    if (blocks[j] == grassID)
                    {
                        blocks[j] = (byte) surfaceBlockID;
                        extB.setExtBlockMetadata(chunkX * 16 + (j & 15), i * 16 + ((j & 3840) >> 8), chunkZ * 16 + ((j & 240) >> 4), surfaceBlockMeta);
                        blocksExist = true;
                    } else if (blocks[j] == tallGrassID)
                    {
                        blocks[j] = (byte) deadBushID;
                    }
                }
            }
        }
*/
        return blocksExist;
    }

    static
    {
        grassID = Block.getIdFromBlock(Blocks.grass);
        tallGrassID = Block.getIdFromBlock(Blocks.tallgrass);
        deadBushID = Block.getIdFromBlock(Blocks.deadbush);
        radius = ModConfig.forceDisableGrassRadius;
        checkingNearChunks = false;
    }
}
