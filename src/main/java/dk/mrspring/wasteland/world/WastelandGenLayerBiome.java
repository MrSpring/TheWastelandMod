//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world;

import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;
import net.minecraft.world.gen.layer.GenLayerEdge.Mode;
import net.minecraftforge.common.BiomeManager.BiomeEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WastelandGenLayerBiome extends GenLayer
{
    private List<BiomeEntry> biomes = new ArrayList();
    private static final String __OBFID = "CL_00000555";

    public WastelandGenLayerBiome(long p_i2122_1_, GenLayer p_i2122_3_, WorldType p_i2122_4_)
    {
        super(p_i2122_1_);
        super.parent = p_i2122_3_;
        int i;
        if (ModConfig.spawnCities)
        {
            for (i = 0; i < 1; ++i)
            {
                this.biomes.add(new BiomeEntry(WastelandBiomes.city, 10));
            }
        }

        for (i = 0; i < 3; ++i)
        {
            this.biomes.add(new BiomeEntry(WastelandBiomes.radioactive, 10));
        }

        for (i = 0; i < 10; ++i)
        {
            this.biomes.add(new BiomeEntry(WastelandBiomes.forest, 10));
            this.biomes.add(new BiomeEntry(WastelandBiomes.mountains, 10));
        }

        for (i = 0; i < 40; ++i)
        {
            this.biomes.add(new BiomeEntry(WastelandBiomes.apocalypse, 10));
        }

    }

    public static GenLayer[] initializeAllBiomeGenerators(long p_75901_0_, WorldType p_75901_2_)
    {
        boolean flag = false;
        GenLayerIsland genlayerisland = new GenLayerIsland(1L);
        GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
        GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom);
        GenLayerZoom genlayerzoom = new GenLayerZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
        genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland);
        GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new GenLayerRemoveTooMuchOcean(2L, genlayeraddisland);
        GenLayerAddSnow genlayeraddsnow = new GenLayerAddSnow(2L, genlayerremovetoomuchocean);
        genlayeraddisland = new GenLayerAddIsland(3L, genlayeraddsnow);
        GenLayerEdge genlayeredge = new GenLayerEdge(2L, genlayeraddisland, Mode.COOL_WARM);
        genlayeredge = new GenLayerEdge(2L, genlayeredge, Mode.HEAT_ICE);
        genlayeredge = new GenLayerEdge(3L, genlayeredge, Mode.SPECIAL);
        genlayerzoom = new GenLayerZoom(2002L, genlayeredge);
        genlayerzoom = new GenLayerZoom(2003L, genlayerzoom);
        genlayeraddisland = new GenLayerAddIsland(4L, genlayerzoom);
        GenLayerAddMushroomIsland genlayeraddmushroomisland = new GenLayerAddMushroomIsland(5L, genlayeraddisland);
        GenLayerDeepOcean genlayerdeepocean = new GenLayerDeepOcean(4L, genlayeraddmushroomisland);
        GenLayer genlayer2 = GenLayerZoom.magnify(1000L, genlayerdeepocean, 0);
        byte b0 = 4;
        if (p_75901_2_ == WorldType.LARGE_BIOMES)
        {
            b0 = 6;
        }

        byte var23 = getModdedBiomeSize(p_75901_2_, b0);
        GenLayer genlayer = GenLayerZoom.magnify(1000L, genlayer2, 0);
        GenLayer object = p_75901_2_.getBiomeLayer(p_75901_0_, genlayer2);
        GenLayer genlayer1 = GenLayerZoom.magnify(1000L, genlayer, 2);
        GenLayerHills genlayerhills = new GenLayerHills(1000L, (GenLayer) object, genlayer1);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, 2);
        genlayer = GenLayerZoom.magnify(1000L, genlayer, var23);
        GenLayerSmooth genlayersmooth = new GenLayerSmooth(1000L, genlayer);
        Object var24 = new GenLayerRareBiome(1001L, genlayerhills);

        for (int genlayersmooth1 = 0; genlayersmooth1 < var23; ++genlayersmooth1)
        {
            var24 = new GenLayerZoom((long) (1000 + genlayersmooth1), (GenLayer) var24);
            if (genlayersmooth1 == 0)
            {
                var24 = new GenLayerAddIsland(3L, (GenLayer) var24);
            }

            if (genlayersmooth1 == 1)
            {
                var24 = new GenLayerShore(1000L, (GenLayer) var24);
            }
        }

        GenLayerSmooth var25 = new GenLayerSmooth(1000L, (GenLayer) var24);
        GenLayerRiverMix genlayerrivermix = new GenLayerRiverMix(100L, var25, genlayersmooth);
        GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerrivermix);
        var25.initWorldGenSeed(p_75901_0_);
        genlayerrivermix.initWorldGenSeed(p_75901_0_);
        genlayervoronoizoom.initWorldGenSeed(p_75901_0_);
        return new GenLayer[]{genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public int[] getInts(int p_75904_1_, int p_75904_2_, int p_75904_3_, int p_75904_4_)
    {
        int[] aint = super.parent.getInts(p_75904_1_, p_75904_2_, p_75904_3_, p_75904_4_);
        int[] aint1 = IntCache.getIntCache(p_75904_3_ * p_75904_4_);

        for (int i1 = 0; i1 < p_75904_4_; ++i1)
        {
            for (int j1 = 0; j1 < p_75904_3_; ++j1)
            {
                this.initChunkSeed((long) (j1 + p_75904_1_), (long) (i1 + p_75904_2_));
                int k1 = aint[j1 + i1 * p_75904_3_];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;
                aint1[j1 + i1 * p_75904_3_] = ((BiomeEntry) WeightedRandom.getItem(this.biomes, (int) (this.nextLong((long) (WeightedRandom.getTotalWeight(this.biomes) / 10)) * 10L))).biome.biomeID;
            }
        }

        return aint1;
    }

    private static int pickBiome(List<BiomeEntry> biomes)
    {
        Random rand = new Random();
        int totalWeight = 0;

        int num;
        for (num = 0; num < biomes.size(); ++num)
        {
            totalWeight += ((BiomeEntry) biomes.get(num)).itemWeight;
        }

        num = rand.nextInt(totalWeight);
        int sum = 0;

        for (int i = 0; i < biomes.size(); ++i)
        {
            sum += ((BiomeEntry) biomes.get(1)).itemWeight;
            if (sum > num)
            {
                return ((BiomeEntry) biomes.get(i)).biome.biomeID;
            }
        }

        return pickBiome(biomes);
    }
}
