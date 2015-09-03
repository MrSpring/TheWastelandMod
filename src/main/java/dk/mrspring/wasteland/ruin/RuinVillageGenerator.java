//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.config.RuinConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.code.BuildingCode.Bunker;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.WastelandWorldData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RuinVillageGenerator implements IWorldGenerator
{
    public static List<Vector> villageLocation;
    public static int villageNum;
    private boolean generating;
    private boolean loadedWorld;

    public RuinVillageGenerator()
    {
        GameRegistry.registerWorldGenerator(this.toIWorldGenerator(), 12);
        villageLocation = new ArrayList();
        villageNum = 0;
        this.generating = false;
        this.loadedWorld = false;
    }

    public IWorldGenerator toIWorldGenerator()
    {
        return this;
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.dimensionId == 0 && world.getBiomeGenForCoords(chunkX * 16, chunkZ * 16) == WastelandBiomes.apocalypse && this.loadedWorld)
        {
            this.generateVillage(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }

    }

    public void generateVillage(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        Vector currentLoc = new Vector(chunkX * 16, world.getHeightValue(chunkX * 16, chunkZ * 16), chunkZ * 16);
        if (this.checkDist(currentLoc, (double) (ModConfig.minVillageDistance * 16)) && !this.generating && !world.isRemote)
        {
            this.generating = true;
            int villageSize = random.nextInt(3);
            int villageDim = (villageSize + 3) * 16;
            int[][] heightMap = this.getTerrainLevelMap(world, currentLoc, villageDim);
            if (this.checkLevelVariation(heightMap[0], 6) && this.checkCenter(currentLoc, 3, 1, world))
            {
                System.out.println("Generating Village at X:" + String.valueOf(chunkX * 16) + " Z:" + chunkZ * 16);
                villageLocation.add(currentLoc);
                ++villageNum;
                RuinedVillage village = new RuinedVillage(world, chunkX * 16, chunkZ * 16, villageDim, villageSize, random);
                village.generate(world, random);
            }

            this.generating = false;
        }

    }

    public static void spawnBunker(Vector pos, World world)
    {
        byte[] blocks = Bunker.BLOCKS;
        byte[] data = Bunker.DATA;
        int count = 0;
        int worldHeight = getWorldHeight(world, pos.X - 1, pos.Z - 5);
        Random random = new Random();
        byte cX = 3;
        byte cZ = 3;
        System.out.println(pos.toCustomString());

        int i;
        for (i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 7; ++j)
            {
                for (int i1 = 0; i1 < 7; ++i1)
                {
                    if (blocks[count] == 54)
                    {
                        world.setBlock(pos.X - cX + i1, pos.Y + i, pos.Z - cZ + j, Block.getBlockById(blocks[count]), data[count], 0);
                        TileEntityChest meta = (TileEntityChest) world.getTileEntity(pos.X - cX + i1, pos.Y + i, pos.Z - cZ + j);
                        LootStack loot = new LootStack(RuinConfig.getLoot(RuinConfig.startLoot), RuinConfig.startLootMax, RuinConfig.startLootMin, RuinConfig.startLootRepeat);
                        CustomItemStack.placeLoot(random, meta, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
                    } else if (blocks[count] == 98)
                    {
                        int var14 = random.nextInt(10);
                        var14 = var14 > 6 ? random.nextInt(5) : 0;
                        var14 = var14 > 2 ? random.nextInt(2) + 1 : var14;
                        world.setBlock(pos.X - cX + i1, pos.Y + i, pos.Z - cZ + j, Block.getBlockById(blocks[count]), var14, 0);
                    } else
                    {
                        world.setBlock(pos.X - cX + i1, pos.Y + i, pos.Z - cZ + j, Block.getBlockById(blocks[count]), data[count], 0);
                    }

                    ++count;
                }
            }
        }

        world.setBlock(pos.X - 1, pos.Y + 1, pos.Z - 4, Blocks.air, 0, 2);
        world.setBlock(pos.X - 1, pos.Y + 2, pos.Z - 4, Blocks.air, 0, 2);

        for (i = pos.Y + 1; i < worldHeight; ++i)
        {
            world.setBlock(pos.X - 1, i, pos.Z - 5, Blocks.ladder, 3, 2);
        }

        world.setBlock(pos.X - 1, worldHeight, pos.Z - 5, Blocks.air, 3, 2);
        world.setBlock(pos.X - 1, worldHeight + 1, pos.Z - 5, Blocks.air, 3, 2);
        world.setBlock(pos.X - 1, worldHeight + 2, pos.Z - 5, Blocks.air, 3, 2);
    }

    private boolean checkCenter(Vector currentLoc, int rad, int maxVar, World world)
    {
        int max = getWorldHeight(world, currentLoc.X, currentLoc.Z);
        int min = max;

        for (int i = 0; i < rad * 2 + 1; ++i)
        {
            for (int j = 0; j < rad * 2 + 1; ++j)
            {
                int h = getWorldHeight(world, currentLoc.X - rad + i, currentLoc.Z - rad + j);
                max = h > max ? h : max;
                min = h < min ? h : min;
            }
        }

        return max - min <= maxVar;
    }

    private boolean checkDist(Vector current, double distance)
    {
        for (int i = 0; i < villageLocation.size(); ++i)
        {
            if (Vector.VtoVlength(current, (Vector) villageLocation.get(i)) < distance)
            {
                return false;
            }
        }

        return true;
    }

    public void resetData()
    {
        this.generating = false;
        villageNum = 0;
        villageLocation.clear();
        this.loadedWorld = true;
    }

    private boolean checkLevelVariation(int[] heightMap, int maxBlockVariation)
    {
        int maxHeight = heightMap[0];
        int minHeight = maxHeight;

        for (int i = 1; i < heightMap.length; ++i)
        {
            if (heightMap[i] > maxHeight)
            {
                maxHeight = heightMap[i];
            }

            if (heightMap[i] < minHeight)
            {
                minHeight = heightMap[i];
            }
        }

        return maxHeight - minHeight <= maxBlockVariation;
    }

    private int[][] getTerrainLevelMap(World world, Vector position, int dim)
    {
        int villageDim = dim / 2;
        int[][] heightMap = new int[2][villageDim * villageDim];

        for (int i = 0; i < villageDim; ++i)
        {
            for (int j = 0; j < villageDim; ++j)
            {
                boolean flag = true;

                for (byte tries = 0; tries < 3 && flag; ++tries)
                {
                    int height = getWorldHeight(world, position.X + j * 2 - villageDim, position.Z + i * 2 - villageDim);
                    if (height != 0)
                    {
                        heightMap[0][i * villageDim + j] = height;
                        heightMap[1][i * villageDim + j] = Block.getIdFromBlock(world.getBlock(position.X + j * 2 - villageDim, height, position.Z + i * 2 - villageDim));
                        flag = false;
                    }
                }
            }
        }

        return heightMap;
    }

    public static int getWorldHeight(World world, int x, int z)
    {
        int worldHeight = world.getHeightValue(x, z);
        if (worldHeight == 0)
        {
            world.getChunkProvider().loadChunk(x >> 4, z >> 4);
            worldHeight = world.getHeightValue(x, z);
        }

        if (worldHeight == 0)
        {
            System.out.println("World height still 0");
        }

        return worldHeight;
    }

    public void loadData(List<Vector> villageLoc, int size)
    {
        villageLocation = villageLoc;
        villageNum = size;
        this.loadedWorld = true;
    }

    public void saveData(WastelandWorldData data)
    {
        data.saveVillageData(villageLocation);
    }
}
