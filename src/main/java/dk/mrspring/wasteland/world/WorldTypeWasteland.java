package dk.mrspring.wasteland.world;

import dk.mrspring.wasteland.world.gen.WastelandGeneratorInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldTypeWasteland extends WorldType
{
    public static WastelandGeneratorInfo genInfo = new WastelandGeneratorInfo();

    public WorldTypeWasteland(String name)
    {
        super(name);
    }

    public WorldChunkManager getChunkManager(World world)
    {
        return new WorldChunkManagerWasteland(world);
    }

    public IChunkProvider getChunkGenerator(World world, String generatorOptions)
    {
        return new ChunkProviderWasteland(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), "");
    }

    public GenLayer getBiomeLayer(long worldSeed, GenLayer parentLayer, String options)
    {
        WastelandGenLayerBiome ret = new WastelandGenLayerBiome(200L, parentLayer, this);
        GenLayer ret1 = GenLayerZoom.magnify(1000L, ret, 2);
        GenLayerBiomeEdge ret2 = new GenLayerBiomeEdge(1000L, ret1);
        return ret2;
    }
}
