//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;

public class RuinGenHelper
{
    protected static World worldObj;

    public RuinGenHelper()
    {
    }

    public static void setCube(int baseX, int baseY, int baseZ, int lengthX, int lengthY, int lengthZ, Block block)
    {
        for (int y = 0; y < lengthY; ++y)
        {
            for (int z = 0; z < lengthX; ++z)
            {
                for (int x = 0; x < lengthZ; ++x)
                {
                    setBlock(x + baseX, y + baseY, z + baseZ, block);
                }
            }
        }

    }

    public static void setBlock(int x, int y, int z, Block block)
    {
        setBlock(x, y, z, block, 0);
    }

    public static void setBlock(int x, int y, int z, Block block, int meta)
    {
        worldObj.setBlockState(asPos(x, y, z), block.getStateFromMeta(meta), 2);
    }

    public static BlockPos asPos(int x, int y, int z)
    {
        return new BlockPos(x, y, z);
    }

    public static void setWorld(World world)
    {
        worldObj = world;
    }

    public static TileEntity getTileEntity(int x, int y, int z)
    {
        return worldObj.getTileEntity(asPos(x, y, z));
    }

    public static BiomeGenBase getBiomeGenForCoords(int x, int z)
    {
        return worldObj.getBiomeGenForCoords(asPos(x, 0, z));
    }

    public static int getHeightValue(int x, int z)
    {
        Chunk chunk = worldObj.getChunkFromBlockCoords(asPos(x, 0, z));
        int xCoord = x;
        int zCoord = z;
        int k = chunk.getTopFilledSegment() + 15;
        x &= 15;

        for (z &= 15; k > 0; --k)
        {
            Block block = chunk.getBlock(x, k, z);

            if (block.getMaterial().blocksMovement() && block.getMaterial() != Material.leaves && !block.isFoliage(worldObj, asPos(xCoord, k, zCoord)))
            {
                return k + 1;
            }
        }

        return -1;
    }

    public static Block getBlock(int x, int y, int z)
    {
        return worldObj.getBlockState(asPos(x, y, z)).getBlock();
    }
}
