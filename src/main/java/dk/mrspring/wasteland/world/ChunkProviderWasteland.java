package dk.mrspring.wasteland.world;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;

public class ChunkProviderWasteland implements IChunkProvider
{
    /**
     * RNG.
     */
    private Random rand;
    private NoiseGeneratorOctaves field_147431_j;
    private NoiseGeneratorOctaves field_147432_k;
    private NoiseGeneratorOctaves field_147429_l;
    private NoiseGeneratorPerlin field_147430_m;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private WorldType field_177475_o;
    private final double[] field_147434_q;
    private final float[] parabolicField;
    private ChunkProviderSettings settings;
    private Block field_177476_s;
    private double[] stoneNoise;
    private MapGenBase caveGenerator;
    private MapGenStronghold strongholdGenerator;
    private MapGenVillage villageGenerator;
    private MapGenMineshaft mineshaftGenerator;
    private MapGenScatteredFeature scatteredFeatureGenerator;
    private MapGenBase ravineGenerator;
    private StructureOceanMonument oceanMonumentGenerator;
    private BiomeGenBase[] biomesForGeneration;
    double[] field_147427_d;
    double[] field_147428_e;
    double[] field_147425_f;
    double[] field_147426_g;
    private static final String __OBFID = "CL_00000396";

    public ChunkProviderWasteland(World worldIn, long p_i45636_2_, boolean p_i45636_4_, String p_i45636_5_)
    {
        this.field_177476_s = Blocks.air;
        this.stoneNoise = new double[256];
        this.caveGenerator = new MapGenCaves();
        this.strongholdGenerator = new MapGenStronghold();
        this.villageGenerator = new MapGenVillage();
        this.mineshaftGenerator = new MapGenMineshaft();
        this.scatteredFeatureGenerator = new MapGenScatteredFeature();
        this.ravineGenerator = new MapGenRavine();
        this.oceanMonumentGenerator = new StructureOceanMonument();
        {
            caveGenerator = TerrainGen.getModdedMapGen(caveGenerator, CAVE);
            strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(strongholdGenerator, STRONGHOLD);
            villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(villageGenerator, VILLAGE);
            mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(mineshaftGenerator, MINESHAFT);
            scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(scatteredFeatureGenerator, SCATTERED_FEATURE);
            ravineGenerator = TerrainGen.getModdedMapGen(ravineGenerator, RAVINE);
            oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(oceanMonumentGenerator, OCEAN_MONUMENT);
        }
        this.worldObj = worldIn;
        this.mapFeaturesEnabled = p_i45636_4_;
        this.field_177475_o = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(p_i45636_2_);
        this.field_147431_j = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147432_k = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_147429_l = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147430_m = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_147434_q = new double[825];
        this.parabolicField = new float[25];

        for (int j = -2; j <= 2; ++j)
        {
            for (int k = -2; k <= 2; ++k)
            {
                float f = 10.0F / MathHelper.sqrt_float((float) (j * j + k * k) + 0.2F);
                this.parabolicField[j + 2 + (k + 2) * 5] = f;
            }
        }

        if (p_i45636_5_ != null)
        {
            this.settings = ChunkProviderSettings.Factory.func_177865_a(p_i45636_5_).func_177864_b();
        }

        NoiseGenerator[] noiseGens = {field_147431_j, field_147432_k, field_147429_l, field_147430_m, noiseGen5, noiseGen6, mobSpawnerNoise};
        noiseGens = TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, noiseGens);
        this.field_147431_j = (NoiseGeneratorOctaves) noiseGens[0];
        this.field_147432_k = (NoiseGeneratorOctaves) noiseGens[1];
        this.field_147429_l = (NoiseGeneratorOctaves) noiseGens[2];
        this.field_147430_m = (NoiseGeneratorPerlin) noiseGens[3];
        this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
        this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
    }

    public void setBlocksInChunk(int p_180518_1_, int p_180518_2_, ChunkPrimer p_180518_3_)
    {
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, p_180518_1_ * 4 - 2, p_180518_2_ * 4 - 2, 10, 10);
        this.func_147423_a(p_180518_1_ * 4, 0, p_180518_2_ * 4);

        for (int k = 0; k < 4; ++k)
        {
            int l = k * 5;
            int i1 = (k + 1) * 5;

            for (int j1 = 0; j1 < 4; ++j1)
            {
                int k1 = (l + j1) * 33;
                int l1 = (l + j1 + 1) * 33;
                int i2 = (i1 + j1) * 33;
                int j2 = (i1 + j1 + 1) * 33;

                for (int k2 = 0; k2 < 32; ++k2)
                {
                    double d0 = 0.125D;
                    double d1 = this.field_147434_q[k1 + k2];
                    double d2 = this.field_147434_q[l1 + k2];
                    double d3 = this.field_147434_q[i2 + k2];
                    double d4 = this.field_147434_q[j2 + k2];
                    double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
                    double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
                    double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
                    double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;

                    for (int l2 = 0; l2 < 8; ++l2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int i3 = 0; i3 < 4; ++i3)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double d15 = d10 - d16;

                            for (int j3 = 0; j3 < 4; ++j3)
                            {
                                if ((d15 += d16) > 0.0D)
                                {
                                    p_180518_3_.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, Blocks.stone.getDefaultState());
                                } else if (k2 * 8 + l2 < this.settings.seaLevel)
                                {
                                    p_180518_3_.setBlockState(k * 4 + i3, k2 * 8 + l2, j1 * 4 + j3, this.field_177476_s.getDefaultState());
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void func_180517_a(int p_180517_1_, int p_180517_2_, ChunkPrimer p_180517_3_, BiomeGenBase[] p_180517_4_)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, p_180517_1_, p_180517_2_, p_180517_3_, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        double d0 = 0.03125D;
        this.stoneNoise = this.field_147430_m.func_151599_a(this.stoneNoise, (double) (p_180517_1_ * 16), (double) (p_180517_2_ * 16), 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int k = 0; k < 16; ++k)
        {
            for (int l = 0; l < 16; ++l)
            {
                BiomeGenBase biomegenbase = p_180517_4_[l + k * 16];
                biomegenbase.genTerrainBlocks(this.worldObj, this.rand, p_180517_3_, p_180517_1_ * 16 + k, p_180517_2_ * 16 + l, this.stoneNoise[l + k * 16]);
            }
        }
    }

    /**
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int x, int z)
    {
        this.rand.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.func_180517_a(x, z, chunkprimer, this.biomesForGeneration);

        if (this.settings.useCaves)
        {
            this.caveGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useRavines)
        {
            this.ravineGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            this.villageGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.func_175792_a(this, this.worldObj, x, z, chunkprimer);
        }

        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        byte[] abyte = chunk.getBiomeArray();

        for (int k = 0; k < abyte.length; ++k)
        {
            abyte[k] = (byte) this.biomesForGeneration[k].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
    {
        this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, (double) this.settings.depthNoiseScaleX, (double) this.settings.depthNoiseScaleZ, (double) this.settings.depthNoiseScaleExponent);
        float f = this.settings.coordinateScale;
        float f1 = this.settings.heightScale;
        this.field_147427_d = this.field_147429_l.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double) (f / this.settings.mainNoiseScaleX), (double) (f1 / this.settings.mainNoiseScaleY), (double) (f / this.settings.mainNoiseScaleZ));
        this.field_147428_e = this.field_147431_j.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double) f, (double) f1, (double) f);
        this.field_147425_f = this.field_147432_k.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, (double) f, (double) f1, (double) f);
        boolean flag1 = false;
        boolean flag = false;
        int l = 0;
        int i1 = 0;

        for (int j1 = 0; j1 < 5; ++j1)
        {
            for (int k1 = 0; k1 < 5; ++k1)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                byte b0 = 2;
                BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + (k1 + 2) * 10];

                for (int l1 = -b0; l1 <= b0; ++l1)
                {
                    for (int i2 = -b0; i2 <= b0; ++i2)
                    {
                        BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + (k1 + i2 + 2) * 10];
                        float f5 = this.settings.biomeDepthOffSet + biomegenbase1.minHeight * this.settings.biomeDepthWeight;
                        float f6 = this.settings.biomeScaleOffset + biomegenbase1.maxHeight * this.settings.biomeScaleWeight;

                        if (this.field_177475_o == WorldType.AMPLIFIED && f5 > 0.0F)
                        {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.parabolicField[l1 + 2 + (i2 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.minHeight > biomegenbase.minHeight)
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 /= f4;
                f3 /= f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.field_147426_g[i1] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 /= 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 /= 1.4D;
                    d7 /= 2.0D;
                } else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 /= 8.0D;
                }

                ++i1;
                double d8 = (double) f3;
                double d9 = (double) f2;
                d8 += d7 * 0.2D;
                d8 = d8 * (double) this.settings.baseSize / 8.0D;
                double d0 = (double) this.settings.baseSize + d8 * 4.0D;

                for (int j2 = 0; j2 < 33; ++j2)
                {
                    double d1 = ((double) j2 - d0) * (double) this.settings.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.field_147428_e[l] / (double) this.settings.lowerLimitScale;
                    double d3 = this.field_147425_f[l] / (double) this.settings.upperLimitScale;
                    double d4 = (this.field_147427_d[l] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;

                    if (j2 > 29)
                    {
                        double d6 = (double) ((float) (j2 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.field_147434_q[l] = d5;
                    ++l;
                }
            }
        }
    }

    /**
     * Checks to see if a chunk exists at x, z
     */
    public boolean chunkExists(int x, int z)
    {
        return true;
    }

    /**
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
    {
        BlockFalling.fallInstantly = true;
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BlockPos blockpos = new BlockPos(k, 0, l);
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) p_73153_2_ * i1 + (long) p_73153_3_ * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(p_73153_2_, p_73153_3_);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag));

        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
        }

        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            flag = this.villageGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
        }

        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
        }

        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
        }

        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.func_175794_a(this.worldObj, this.rand, chunkcoordintpair);
        }

        int k1;
        int l1;
        int i2;

        /*if (biomegenbase != BiomeGenBase.desert && biomegenbase != BiomeGenBase.desertHills && this.settings.useWaterLakes && !flag && this.rand.nextInt(this.settings.waterLakeChance) == 0
                && TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, LAKE))
        {
            k1 = this.rand.nextInt(16) + 8;
            l1 = this.rand.nextInt(256);
            i2 = this.rand.nextInt(16) + 8;
            (new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, blockpos.add(k1, l1, i2));
        }*/

        if (TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, LAVA) && !flag && this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
        {
            k1 = this.rand.nextInt(16) + 8;
            l1 = this.rand.nextInt(this.rand.nextInt(248) + 8);
            i2 = this.rand.nextInt(16) + 8;

            if (l1 < 63 || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0)
            {
                (new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, blockpos.add(k1, l1, i2));
            }
        }

        if (this.settings.useDungeons)
        {
            boolean doGen = TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, DUNGEON);
            for (k1 = 0; doGen && k1 < this.settings.dungeonChance; ++k1)
            {
                l1 = this.rand.nextInt(16) + 8;
                i2 = this.rand.nextInt(256);
                int j2 = this.rand.nextInt(16) + 8;
                (new WorldGenDungeons()).generate(this.worldObj, this.rand, blockpos.add(l1, i2, j2));
            }
        }

        biomegenbase.decorate(this.worldObj, this.rand, new BlockPos(k, 0, l));
        if (TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, ANIMALS))
        {
            SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
        }
        blockpos = blockpos.add(8, 0, 8);

        boolean doGen = TerrainGen.populate(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag, ICE);
        for (k1 = 0; doGen && k1 < 16; ++k1)
        {
            for (l1 = 0; l1 < 16; ++l1)
            {
                BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(blockpos.add(k1, 0, l1));
                BlockPos blockpos2 = blockpos1.down();

                if (this.worldObj.func_175675_v(blockpos2))
                {
                    this.worldObj.setBlockState(blockpos2, Blocks.ice.getDefaultState(), 2);
                }

                if (this.worldObj.canSnowAt(blockpos1, true))
                {
                    this.worldObj.setBlockState(blockpos1, Blocks.snow_layer.getDefaultState(), 2);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(p_73153_1_, worldObj, rand, p_73153_2_, p_73153_3_, flag));

        BlockFalling.fallInstantly = false;
    }

    public boolean func_177460_a(IChunkProvider p_177460_1_, Chunk p_177460_2_, int p_177460_3_, int p_177460_4_)
    {
        boolean flag = false;

        if (this.settings.useMonuments && this.mapFeaturesEnabled && p_177460_2_.getInhabitedTime() < 3600L)
        {
            flag |= this.oceanMonumentGenerator.func_175794_a(this.worldObj, this.rand, new ChunkCoordIntPair(p_177460_3_, p_177460_4_));
        }

        return flag;
    }

    /**
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
    {
        return true;
    }

    /**
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.  Currently
     * unimplemented.
     */
    public void saveExtraData()
    {
    }

    /**
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    /**
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List func_177458_a(EnumCreatureType p_177458_1_, BlockPos p_177458_2_)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_177458_2_);

        if (this.mapFeaturesEnabled)
        {
            if (p_177458_1_ == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(p_177458_2_))
            {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (p_177458_1_ == EnumCreatureType.MONSTER && this.settings.useMonuments && this.oceanMonumentGenerator.func_175796_a(this.worldObj, p_177458_2_))
            {
                return this.oceanMonumentGenerator.func_175799_b();
            }
        }

        return biomegenbase.getSpawnableList(p_177458_1_);
    }

    public BlockPos getStrongholdGen(World worldIn, String p_180513_2_, BlockPos p_180513_3_)
    {
        return "Stronghold".equals(p_180513_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, p_180513_3_) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_)
    {
        if (this.settings.useMineShafts && this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer) null);
        }

        if (this.settings.useVillages && this.mapFeaturesEnabled)
        {
            this.villageGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer) null);
        }

        if (this.settings.useStrongholds && this.mapFeaturesEnabled)
        {
            this.strongholdGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer) null);
        }

        if (this.settings.useTemples && this.mapFeaturesEnabled)
        {
            this.scatteredFeatureGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer) null);
        }

        if (this.settings.useMonuments && this.mapFeaturesEnabled)
        {
            this.oceanMonumentGenerator.func_175792_a(this, this.worldObj, p_180514_2_, p_180514_3_, (ChunkPrimer) null);
        }
    }

    public Chunk provideChunk(BlockPos blockPosIn)
    {
        return this.provideChunk(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4);
    }
}

/*
public class ChunkProviderWasteland implements IChunkProvider
{
    private Random rand;
    private NoiseGeneratorOctaves noiseGen1;
    private NoiseGeneratorOctaves noiseGen2;
    private NoiseGeneratorOctaves noiseGen3;
    private NoiseGeneratorPerlin noiseGen4;
    public NoiseGeneratorOctaves noiseGen5;
    public NoiseGeneratorOctaves noiseGen6;
    public NoiseGeneratorOctaves mobSpawnerNoise;
    private World worldObj;
    private final boolean mapFeaturesEnabled;
    private WorldType currentWorldType;
    private final double[] dblArray5;
    private final float[] parabolicField;
    private double[] stoneNoise = new double[256];
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private BiomeGenBase[] biomesForGeneration;
    double[] dblArray1;
    double[] dblArray2;
    double[] dblArray3;
    double[] dblArray4;

    public ChunkProviderWasteland(World par1World, long par2, boolean par4)
    {
        this.caveGenerator = TerrainGen.getModdedMapGen(this.caveGenerator, EventType.CAVE);
        this.strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(this.strongholdGenerator, EventType.STRONGHOLD);
        this.villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(this.villageGenerator, EventType.VILLAGE);
        this.mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(this.mineshaftGenerator, EventType.MINESHAFT);
        this.scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(this.scatteredFeatureGenerator, EventType.SCATTERED_FEATURE);
        this.ravineGenerator = TerrainGen.getModdedMapGen(this.ravineGenerator, EventType.RAVINE);
        this.worldObj = par1World;
        this.mapFeaturesEnabled = par4;
        this.currentWorldType = par1World.getWorldInfo().getTerrainType();
        this.rand = new Random(par2);
        this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
        this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
        this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
        this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
        this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
        this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
        this.dblArray5 = new double[825];
        this.parabolicField = new float[25];

        for (int noiseGens = -2; noiseGens <= 2; ++noiseGens)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt_float((float) (noiseGens * noiseGens + j * j) + 0.2F);
                this.parabolicField[noiseGens + 2 + (j + 2) * 5] = f;
            }
        }

        NoiseGenerator[] var8 = new NoiseGenerator[]{this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise};
        var8 = TerrainGen.getModdedNoiseGenerators(par1World, this.rand, var8);
        this.noiseGen1 = (NoiseGeneratorOctaves) var8[0];
        this.noiseGen2 = (NoiseGeneratorOctaves) var8[1];
        this.noiseGen3 = (NoiseGeneratorOctaves) var8[2];
        this.noiseGen4 = (NoiseGeneratorPerlin) var8[3];
        this.noiseGen5 = (NoiseGeneratorOctaves) var8[4];
        this.noiseGen6 = (NoiseGeneratorOctaves) var8[5];
        this.mobSpawnerNoise = (NoiseGeneratorOctaves) var8[6];
    }

    public void func_147424_a(int xCoord, int zCoord, Block[] blocks)
    {
        byte byte1 = 63;
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, xCoord * 4 - 2, zCoord * 4 - 2, 10, 10);
        this.func_147423_a(xCoord * 4, 0, zCoord * 4);

        for (int i = 0; i < 4; ++i)
        {
            int int1 = i * 5;
            int int2 = (i + 1) * 5;

            for (int j = 0; j < 4; ++j)
            {
                int int3 = (int1 + j) * 33;
                int int4 = (int1 + j + 1) * 33;
                int int5 = (int2 + j) * 33;
                int int6 = (int2 + j + 1) * 33;

                for (int k = 0; k < 32; ++k)
                {
                    double dbl0 = 0.125D;
                    double dbl1 = this.dblArray5[int3 + k];
                    double dbl2 = this.dblArray5[int4 + k];
                    double dbl3 = this.dblArray5[int5 + k];
                    double dbl4 = this.dblArray5[int6 + k];
                    double dbl5 = (this.dblArray5[int3 + k + 1] - dbl1) * dbl0;
                    double dbl6 = (this.dblArray5[int4 + k + 1] - dbl2) * dbl0;
                    double dbl7 = (this.dblArray5[int5 + k + 1] - dbl3) * dbl0;
                    double dbl8 = (this.dblArray5[int6 + k + 1] - dbl4) * dbl0;

                    for (int l = 0; l < 8; ++l)
                    {
                        double dbl9 = 0.25D;
                        double dbl10 = dbl1;
                        double dbl11 = dbl2;
                        double dbl12 = (dbl3 - dbl1) * dbl9;
                        double dbl13 = (dbl4 - dbl2) * dbl9;

                        for (int m = 0; m < 4; ++m)
                        {
                            int j3 = m + i * 4 << 12 | 0 + j * 4 << 8 | k * 8 + l;
                            short short1 = 256;
                            j3 -= short1;
                            double dbl14 = 0.25D;
                            double dbl16 = (dbl11 - dbl10) * dbl14;
                            double dbl15 = dbl10 - dbl16;

                            for (int n = 0; n < 4; ++n)
                            {
                                if ((dbl15 += dbl16) > 0.0D)
                                {
                                    blocks[j3 += short1] = Blocks.stone;
                                } else if (k * 8 + l < byte1)
                                {
                                    blocks[j3 += short1] = Blocks.air;
                                } else
                                {
                                    blocks[j3 += short1] = null;
                                }
                            }

                            dbl10 += dbl12;
                            dbl11 += dbl13;
                        }

                        dbl1 += dbl5;
                        dbl2 += dbl6;
                        dbl3 += dbl7;
                        dbl4 += dbl8;
                    }
                }
            }
        }

    }

    public void replaceBlocksForBiome(int xChunk, int zChunk, Block[] blocksForReplacement, byte[] metadataForReplacement, BiomeGenBase[] biomesForReplacement, World world)
    {
        ReplaceBiomeBlocks blockReplacementEvent = new ReplaceBiomeBlocks(this, xChunk, zChunk, blocksForReplacement, metadataForReplacement, biomesForReplacement, world);
        MinecraftForge.EVENT_BUS.post(blockReplacementEvent);
        if (blockReplacementEvent.getResult() != Result.DENY)
        {
            this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, (double) (xChunk * 16), (double) (zChunk * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

            for (int i = 0; i < 16; ++i)
            {
                for (int j = 0; j < 16; ++j)
                {
                    BiomeGenBase biomegenbase = biomesForReplacement[j + i * 16];
                    biomegenbase.genTerrainBlocks(this.worldObj, this.rand, blocksForReplacement, metadataForReplacement, xChunk * 16 + i, zChunk * 16 + j, this.stoneNoise[j + i * 16]);
                }
            }

        }
    }

    public Chunk loadChunk(int par1, int par2)
    {
        return this.provideChunk(par1, par2);
    }

    public Chunk provideChunk(int par1, int par2)
    {
        this.rand.setSeed((long) par1 * 341873128712L + (long) par2 * 132897987541L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        this.func_147424_a(par1, par2, blocks);
        this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
        this.replaceBlocksForBiome(par1, par2, blocks, metadata, this.biomesForGeneration, this.worldObj);
        this.caveGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
        this.ravineGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
            this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, blocks);
        }

        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, par1, par2);
        byte[] byteArray = chunk.getBiomeArray();

        for (int i = 0; i < byteArray.length; ++i)
        {
            byteArray[i] = (byte) this.biomesForGeneration[i].biomeID;
        }

        chunk.generateSkylightMap();
        return chunk;
    }

    private void func_147423_a(int par1, int par2, int par3)
    {
        double dbl0 = 684.412D;
        double dbl1 = 684.412D;
        double dbl2 = 512.0D;
        double dbl3 = 512.0D;
        this.dblArray4 = this.noiseGen6.generateNoiseOctaves(this.dblArray4, par1, par3, 5, 5, 200.0D, 200.0D, 0.5D);
        this.dblArray1 = this.noiseGen3.generateNoiseOctaves(this.dblArray1, par1, par2, par3, 5, 33, 5, 8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
        this.dblArray2 = this.noiseGen1.generateNoiseOctaves(this.dblArray2, par1, par2, par3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        this.dblArray3 = this.noiseGen2.generateNoiseOctaves(this.dblArray3, par1, par2, par3, 5, 33, 5, 684.412D, 684.412D, 684.412D);
        int int0 = 0;
        int int1 = 0;
        double dbl4 = 8.5D;

        for (int i = 0; i < 5; ++i)
        {
            for (int j = 0; j < 5; ++j)
            {
                float flt0 = 0.0F;
                float flt1 = 0.0F;
                float flt2 = 0.0F;
                byte byte0 = 2;
                BiomeGenBase biome0 = this.biomesForGeneration[i + 2 + (j + 2) * 10];

                for (int dbl13 = -byte0; dbl13 <= byte0; ++dbl13)
                {
                    for (int l = -byte0; l <= byte0; ++l)
                    {
                        BiomeGenBase dbl12 = this.biomesForGeneration[i + dbl13 + 2 + (j + l + 2) * 10];
                        float flt3 = dbl12.rootHeight;
                        float dbl14 = dbl12.heightVariation;
                        if (this.currentWorldType == WorldType.AMPLIFIED && flt3 > 0.0F)
                        {
                            flt3 = 1.0F + flt3 * 2.0F;
                            dbl14 = 1.0F + dbl14 * 4.0F;
                        }

                        float flt5 = this.parabolicField[dbl13 + 2 + (l + 2) * 5] / (flt3 + 2.0F);
                        if (dbl12.rootHeight > biome0.rootHeight)
                        {
                            flt5 /= 2.0F;
                        }

                        flt0 += dbl14 * flt5;
                        flt1 += flt3 * flt5;
                        flt2 += flt5;
                    }
                }

                flt0 /= flt2;
                flt1 /= flt2;
                flt0 = flt0 * 0.9F + 0.1F;
                flt1 = (flt1 * 4.0F - 1.0F) / 8.0F;
                double var44 = this.dblArray4[int1] / 8000.0D;
                if (var44 < 0.0D)
                {
                    var44 = -var44 * 0.3D;
                }

                var44 = var44 * 3.0D - 2.0D;
                if (var44 < 0.0D)
                {
                    var44 /= 2.0D;
                    if (var44 < -1.0D)
                    {
                        var44 = -1.0D;
                    }

                    var44 /= 1.4D;
                    var44 /= 2.0D;
                } else
                {
                    if (var44 > 1.0D)
                    {
                        var44 = 1.0D;
                    }

                    var44 /= 8.0D;
                }

                ++int1;
                double var45 = (double) flt1;
                double var46 = (double) flt0;
                var45 += var44 * 0.2D;
                var45 = var45 * 8.5D / 8.0D;
                double dbl5 = 8.5D + var45 * 4.0D;

                for (int k = 0; k < 33; ++k)
                {
                    double dbl6 = ((double) k - dbl5) * 12.0D * 128.0D / 256.0D / var46;
                    if (dbl6 < 0.0D)
                    {
                        dbl6 *= 4.0D;
                    }

                    double dbl7 = this.dblArray2[int0] / 512.0D;
                    double dbl8 = this.dblArray3[int0] / 512.0D;
                    double dbl9 = (this.dblArray1[int0] / 10.0D + 1.0D) / 2.0D;
                    double dbl10 = MathHelper.denormalizeClamp(dbl7, dbl8, dbl9) - dbl6;
                    if (k > 29)
                    {
                        double d11 = (double) ((float) (k - 29) / 3.0F);
                        dbl10 = dbl10 * (1.0D - d11) + -10.0D * d11;
                    }

                    this.dblArray5[int0] = dbl10;
                    ++int0;
                }
            }
        }

    }

    public boolean chunkExists(int par1, int par2)
    {
        return true;
    }

    public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
    {
        BlockFalling.fallInstantly = true;
        int k = par2 * 16;
        int l = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(k + 16, l + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) par2 * i1 + (long) par3 * j1 ^ this.worldObj.getSeed());
        boolean bln = false;
        MinecraftForge.EVENT_BUS.post(new Pre(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln));
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            bln = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
            this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
        }

        boolean doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON);

        int var1;
        int var2;
        int var3;
        for (var1 = 0; doGen && var1 < 8; ++var1)
        {
            var2 = k + this.rand.nextInt(16) + 8;
            var3 = this.rand.nextInt(256);
            int j2 = l + this.rand.nextInt(16) + 8;
            (new WorldGenDungeons()).generate(this.worldObj, this.rand, var2, var3, j2);
        }

        biomegenbase.decorate(this.worldObj, this.rand, k, l);
        SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, k + 8, l + 8, 16, 16, this.rand);
        k += 8;
        l += 8;
        doGen = TerrainGen.populate(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE);

        for (var1 = 0; doGen && var1 < 16; ++var1)
        {
            for (var2 = 0; var2 < 16; ++var2)
            {
                var3 = this.worldObj.getPrecipitationHeight(k + var1, l + var2);
                if (this.worldObj.isBlockFreezable(var1 + k, var3 - 1, var2 + l))
                {
                    this.worldObj.setBlock(var1 + k, var3 - 1, var2 + l, Blocks.ice, 0, 2);
                }

                if (this.worldObj.func_147478_e(var1 + k, var3, var2 + l, true))
                {
                    this.worldObj.setBlock(var1 + k, var3, var2 + l, Blocks.snow_layer, 0, 2);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new Post(par1IChunkProvider, this.worldObj, this.rand, par2, par3, bln));
        BlockFalling.fallInstantly = false;
    }

    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    public void saveExtraData()
    {
    }

    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public String makeString()
    {
        return "RandomLevelSource";
    }

    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(par2, par4);
        return par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(par2, par3, par4) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
    {
        return "Stronghold".equals(p_147416_2_) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(p_147416_1_, p_147416_3_, p_147416_4_, p_147416_5_) : null;
    }

    public int getLoadedChunkCount()
    {
        return 0;
    }

    public void recreateStructures(int par1, int par2)
    {
        if (this.mapFeaturesEnabled)
        {
            this.mineshaftGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
            this.villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
            this.strongholdGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
            this.scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[]) null);
        }

    }
}
*/
