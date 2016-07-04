package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.config.RuinConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.world.WastelandWorldData;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static dk.mrspring.wasteland.ruin.code.BuildingCode.Bunker;

public class RuinVillageGenerator implements IWorldGenerator
{

    public static List<BlockPos> villageLocation;
    public static int villageNum;
    private boolean generating;
    private boolean loadedWorld;


    public RuinVillageGenerator()
    {
        GameRegistry.registerWorldGenerator(this, 12);
        villageLocation = new ArrayList<BlockPos>();
        villageNum = 0;
        this.generating = false;
        this.loadedWorld = false;
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        if (world.provider.getDimensionId() == 0 && world.getBiomeGenForCoords(pos) == WastelandBiomes.apocalypse && this.loadedWorld)
            this.generateVillage(random, pos, world, chunkGenerator, chunkProvider);
    }

    public void generateVillage(Random random, BlockPos pos, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        //Vector currentLoc = new Vector(chunkX * 16, world.getHeight(pos), chunkZ * 16);
        BlockPos currentLoc = world.getHeight(pos);
        if (this.checkDist(currentLoc, (double) (ModConfig.minVillageDistance * 16)) && !this.generating && !world.isRemote)
        {
            this.generating = true;
            int villageSize = random.nextInt(3);
            int villageDim = (villageSize + 3) * 16;
            int[][] heightMap = this.getTerrainLevelMap(world, currentLoc, villageDim);
            if (this.checkLevelVariation(heightMap[0], 6) && this.checkCenter(currentLoc, 3, 1, world))
            {
                System.out.println("Generating Village at: " + pos.toString());
                villageLocation.add(currentLoc);
                ++villageNum;
                RuinedVillage village = new RuinedVillage(world, pos, villageDim, villageSize, random);
                village.generate(world, random);
            }

            this.generating = false;
        }

    }

    public static void spawnBunker(BlockPos pos, World world)
    {
        byte[] blocks = Bunker.BLOCKS;
        byte[] data = Bunker.DATA;
        int count = 0;
        int worldHeight = getWorldHeight(world, pos.subtract(new BlockPos(1, 0, 5)));
        Random random = new Random();
//        byte cX = 3;
//        byte cZ = 3;
        BlockPos c = pos.add(-3, 0, -3);
        System.out.println(pos.toString());

        int i;
        for (i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 7; ++j)
            {
                for (int i1 = 0; i1 < 7; ++i1)
                {
                    BlockPos cPos = c.add(i1, i, j);
                    if (blocks[count] == 54)
                    {
                        IBlockState state = Block.getBlockById(blocks[54]).getDefaultState();
//                        world.setBlock(pos.X - cX + i1, pos.Y + i, pos.Z - cZ + j, Block.getBlockById(blocks[count]), data[count], 0);
                        world.setBlockState(cPos, state);
                        TileEntityChest meta = (TileEntityChest) world.getTileEntity(cPos);
                        LootStack loot = new LootStack(RuinConfig.getLoot(RuinConfig.startLoot), RuinConfig.startLootMax, RuinConfig.startLootMin, RuinConfig.startLootRepeat);
                        CustomItemStack.placeLoot(random, meta, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
                    } else if (blocks[count] == 98)
                    {
                        int meta = random.nextInt(10);
                        meta = meta > 6 ? random.nextInt(5) : 0;
                        meta = meta > 2 ? random.nextInt(2) + 1 : meta;
                        IBlockState state = Block.getBlockById(98).getStateFromMeta(meta);
                        world.setBlockState(cPos, state);
                    } else
                    {
                        IBlockState state = Block.getBlockById(blocks[count]).getStateFromMeta(data[count]);
                        world.setBlockState(cPos, state);
                    }

                    ++count;
                }
            }
        }

        world.setBlockToAir(pos.add(-1, 1, -4)/*pos.X - 1, pos.Y + 1, pos.Z - 4, Blocks.air, 0, 2*/);
        world.setBlockToAir(pos.add(-1, 2, -4)/*pos.X - 1, pos.Y + 2, pos.Z - 4, Blocks.air, 0, 2*/);

        for (i = pos.getY() + 1; i < worldHeight; ++i)
        {
            IBlockState state = Blocks.ladder.getStateFromMeta(3);
            world.setBlockState(new BlockPos(pos.getX() - 1, i, pos.getZ() - 5), state);
        }

        world.setBlockToAir(new BlockPos(pos.getX() - 1, worldHeight, pos.getZ() - 5)/*pos.X - 1, worldHeight, pos.Z - 5*/);
        world.setBlockToAir(new BlockPos(pos.getX() - 1, worldHeight + 1, pos.getZ() - 5)/*pos.X - 1, worldHeight + 1, pos.Z - 5*/);
        world.setBlockToAir(new BlockPos(pos.getX() - 1, worldHeight + 2, pos.getZ() - 5)/*pos.X - 1, worldHeight + 2, pos.Z - 5*/);
    }

    private boolean checkCenter(BlockPos currentLoc, int rad, int maxVar, World world)
    {
        int max = getWorldHeight(world, currentLoc);
        int min = max;

        BlockPos pos = currentLoc.add(-rad, 0, -rad);
        for (int i = 0; i < rad * 2 + 1; ++i)
        {
            for (int j = 0; j < rad * 2 + 1; ++j)
            {
                int h = getWorldHeight(world, pos.add(i, 0, j)/*currentLoc.X - rad + i, currentLoc.Z - rad + j*/);
                max = h > max ? h : max;
                min = h < min ? h : min;
            }
        }

        return max - min <= maxVar;
    }

    private boolean checkDist(BlockPos current, double distance)
    {
        for (int i = 0; i < villageLocation.size(); ++i)
        {
            if (Math.sqrt(current.distanceSq(villageLocation.get(i))) < distance)//(Vector.VtoVlength(current, (Vector) villageLocation.get(i)) < distance)
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

    private int[][] getTerrainLevelMap(World world, BlockPos position, int dim)
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
                    BlockPos pos = new BlockPos(position.getX() + j * 2 - villageDim, 0, position.getZ() + i * 2 - villageDim);
                    pos.add(0, getWorldHeight(world, pos), 0);
                    if (pos.getY() != 0)
                    {
                        heightMap[0][i * villageDim + j] = pos.getY();
                        heightMap[1][i * villageDim + j] = Block.getIdFromBlock(world.getBlockState(new BlockPos(pos)).getBlock());
                        flag = false;
                    }
                }
            }
        }

        return heightMap;
    }

    public static int getWorldHeight(World world, BlockPos pos)
    {
        int worldHeight = world.getHeight(pos).getY();
        if (worldHeight == 0)
        {
            world.getChunkProvider().provideChunk(pos.getX() >> 4, pos.getZ() >> 4);
            worldHeight = world.getHeight(pos).getY();
        }

        if (worldHeight == 0)
        {
            System.out.println("World height still 0");
        }

        return worldHeight;
    }

    public void loadData(List villageLoc, int size)
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
