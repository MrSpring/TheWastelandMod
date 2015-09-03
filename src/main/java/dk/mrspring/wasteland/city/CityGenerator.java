//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.Layout;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.WastelandWorldData;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CityGenerator implements IWorldGenerator
{
    public static List<Vector> cityLocation;
    public static int cityNum;
    private static boolean generating = false;
    private boolean loadedWorld;

    public CityGenerator()
    {
        GameRegistry.registerWorldGenerator(this.toIWorldGenerator(), 12);
        cityLocation = new ArrayList();
        cityNum = 0;
        this.loadedWorld = false;
    }

    public IWorldGenerator toIWorldGenerator()
    {
        return this;
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.getDimensionId() == 0 &&
                RuinGenHelper.getBiomeGenForCoords(chunkX * 16, chunkZ * 16) == WastelandBiomes.city && this.loadedWorld && ModConfig.spawnCities)
        {
            this.generateCity(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }

    }

    public void generateCity(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        MultiVector currentLoc = new MultiVector(chunkX * 16, Layout.getWorldHeight(world, chunkX * 16, chunkZ * 16), chunkZ * 16);
        if (this.checkDist(currentLoc, (double) (ModConfig.minCityDistance * 16)) && !generating && !world.isRemote)
        {
//            Wasteland.NETWORK.sendToAll(Message.createChatMessage("Generating world structures (please wait)..."));
//            Wasteland.NETWORK.sendToAll(Message.createProgressMessage(0, 1));
            generating = true;
            ArrayList<MultiVector> chunks = new ArrayList<MultiVector>();
            chunks.add(currentLoc);
            this.addConnectedBiomeChunks(chunks, currentLoc, world);
            MultiVector center = this.getCenterChunk(chunks, world);
            this.limitCitySize(chunks, center, ModConfig.maxCitySize * 16);
            if (chunks.size() > 0)
            {
                System.out.println("Generating City at X:" + String.valueOf(center.X) + " Z:" + center.Z + " Size: " + chunks.size());
                List<SchematicBuilding> buildingSchematics = SchematicBuilding.loadAllBuildings();
                LootStack[] loot = LootStack.loadCityLoot();
                RuinedCity city = new RuinedCity(world, center, chunks, random);
                city.generate(world, random, buildingSchematics, loot);
                buildingSchematics.clear();
                cityLocation.add(center);
                ++cityNum;
            }

//            Wasteland.NETWORK.sendToAll(Message.createProgressMessage(1, 1));
//            Wasteland.NETWORK.sendToAll(Message.createChatMessage("...done"));
            generating = false;
        }

    }

    private void limitCitySize(List<MultiVector> allChunks, MultiVector centerChunk, int maxChunkDist)
    {
        for (int i = allChunks.size() - 1; i >= 0; --i)
        {
            if (Vector.VtoVlengthXZ(centerChunk, (Vector) allChunks.get(i)) > (double) maxChunkDist)
            {
                allChunks.remove(i);
            }
        }

    }

    private MultiVector getCenterChunk(List<MultiVector> chunks, World world)
    {
        int maxX = chunks.get(0).X;
        int minX = maxX;
        int maxZ = chunks.get(0).Z;
        int minZ = maxZ;

        int cX;
        for (cX = 1; cX < chunks.size(); ++cX)
        {
            maxX = chunks.get(cX).X > maxX ? chunks.get(cX).X : maxX;
            minX = chunks.get(cX).X < minX ? chunks.get(cX).X : minX;
            maxZ = chunks.get(cX).Z > maxZ ? chunks.get(cX).Z : maxZ;
            minZ = chunks.get(cX).Z < minZ ? chunks.get(cX).Z : minZ;
        }

        cX = (maxX - minX) / 2 + minX & -16;
        int cZ = (maxZ - minZ) / 2 + minZ & -16;

        for (int i = 0; i < chunks.size(); ++i)
        {
            MultiVector v = chunks.get(i);
            if (v.X == cX && v.Z == cZ)
            {
                return v;
            }
        }

        System.out.println("Center not found");
        return null;
    }

    private void addConnectedBiomeChunks(List<MultiVector> chunks, MultiVector position, World world)
    {
        RuinGenHelper.setWorld(world);
        int biomeID = RuinGenHelper.getBiomeGenForCoords(position.X, position.Z).biomeID;
        MultiVector[] newChunks = new MultiVector[]{null, null, null, null};
        boolean containsChunk = false;

        int i;
        for (i = 0; i < 4; ++i)
        {
            MultiVector current = this.chooseChunk(i, position);
            if (RuinGenHelper.getBiomeGenForCoords(current.X, current.Z).biomeID == biomeID)
            {
                for (int j = 0; j < chunks.size() && !containsChunk; ++j)
                {
                    if (chunks.get(j).equalsXZ(current))
                    {
                        containsChunk = true;
                    }
                }

                if (!containsChunk)
                {
                    current.Y = Layout.getWorldHeight(world, current.X, current.Z);
                    newChunks[i] = current.copy();
                    chunks.add(newChunks[i]);
                } else
                {
                    newChunks[i] = null;
                }

                containsChunk = false;
            }
        }

        for (i = 0; i < 4; ++i)
        {
            if (newChunks[i] != null)
            {
                this.addConnectedBiomeChunks(chunks, newChunks[i], world);
            }
        }

    }

    private MultiVector chooseChunk(int i, MultiVector position)
    {
        MultiVector pos;
        if (i == 0)
        {
            pos = new MultiVector(position.X + 16, position.Y, position.Z);
        } else if (i == 1)
        {
            pos = new MultiVector(position.X - 16, position.Y, position.Z);
        } else if (i == 2)
        {
            pos = new MultiVector(position.X, position.Y, position.Z + 16);
        } else
        {
            pos = new MultiVector(position.X, position.Y, position.Z - 16);
        }

        return pos;
    }

    private boolean checkDist(Vector current, double distance)
    {
        for (Vector aCityLocation : cityLocation)
        {
            if (Vector.VtoVlength(current, aCityLocation) < distance)
            {
                return false;
            }
        }

        return true;
    }

    public void resetData()
    {
        generating = false;
        cityNum = 0;
        cityLocation.clear();
        this.loadedWorld = true;
    }

    public static int getWorldHeight(World world, int x, int z)
    {
        RuinGenHelper.setWorld(world);
        int worldHeight = RuinGenHelper.getHeightValue(x, z);
        if (worldHeight == 0)
        {
            world.getChunkProvider().provideChunk(x >> 4, z >> 4);
            worldHeight = RuinGenHelper.getHeightValue(x, z);
        }

        if (worldHeight == 0)
        {
            System.out.println("World height still 0");
        }

        return worldHeight;
    }

    public void loadData(List<Vector> villageLoc, int size)
    {
        cityLocation = villageLoc;
        cityNum = size;
        this.loadedWorld = true;
    }

    public void saveData(WastelandWorldData data)
    {
        data.saveCityData(cityLocation);
    }
}
