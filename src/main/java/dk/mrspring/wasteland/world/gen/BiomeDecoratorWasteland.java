//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.WastelandBiomes;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.ruin.*;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class BiomeDecoratorWasteland extends BiomeDecorator
{
    public int firePerChunk;
    public int rubblePerChunk;
    public int deadTreePerChunk;
    public WorldGenerator randomFireGen = new WorldGenRandomFire();
    public WorldGenerator randomRubbleGen = new WorldGenRandomRubble();
    public WorldGenWastelandBigTree deadTreeGen = new WorldGenWastelandBigTree(true);
    public WorldGenWastelandLake lakeGen = new WorldGenWastelandLake(ModConfig.getlakeLiquid());
    public WorldGenWastelandClay field_76809_f = new WorldGenWastelandClay(4);
    public RuinTreeHouse treeHouse = new RuinTreeHouse("treeHouse");
    public RuinSurvivorTent tent = new RuinSurvivorTent("tent");
    public RuinRuined temple = new RuinRuined("temple");
    public RuinRuinedCiv1 house = new RuinRuinedCiv1("house");

    public BiomeDecoratorWasteland()
    {
        this.firePerChunk = ModConfig.randomFirePerChunk;
        this.rubblePerChunk = 1;
        this.deadTreePerChunk = 1;
        super.flowersPerChunk = -999;
        super.grassPerChunk = -999;
        super.deadBushPerChunk = 5;
        super.generateLakes = true;
        super.treesPerChunk = -999;
    }

    @Override
    public void decorate(World world, Random rand, BiomeGenBase biome, BlockPos pos)
    {
        if (super.currentWorld == null)
        {
            super.currentWorld = world;
            super.randomGenerator = rand;
            super.field_180294_c = pos;
            super.chunkProviderSettings = ChunkProviderSettings.Factory.func_177865_a("").func_177864_b();
            this.dirtGen = new WorldGenMinable(Blocks.dirt.getDefaultState(), this.chunkProviderSettings.dirtSize);
            this.gravelGen = new WorldGenMinable(Blocks.gravel.getDefaultState(), this.chunkProviderSettings.gravelSize);
            this.graniteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.GRANITE), this.chunkProviderSettings.graniteSize);
            this.dioriteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.DIORITE), this.chunkProviderSettings.dioriteSize);
            this.andesiteGen = new WorldGenMinable(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.ANDESITE), this.chunkProviderSettings.andesiteSize);
            this.coalGen = new WorldGenMinable(Blocks.coal_ore.getDefaultState(), this.chunkProviderSettings.coalSize);
            this.ironGen = new WorldGenMinable(Blocks.iron_ore.getDefaultState(), this.chunkProviderSettings.ironSize);
            this.goldGen = new WorldGenMinable(Blocks.gold_ore.getDefaultState(), this.chunkProviderSettings.goldSize);
            this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore.getDefaultState(), this.chunkProviderSettings.redstoneSize);
            this.diamondGen = new WorldGenMinable(Blocks.diamond_ore.getDefaultState(), this.chunkProviderSettings.diamondSize);
            this.lapisGen = new WorldGenMinable(Blocks.lapis_ore.getDefaultState(), this.chunkProviderSettings.lapisSize);
            System.out.println(randomGenerator == null);
            this.genDecorations(biome);
            super.currentWorld = null;
            super.randomGenerator = null;
        }
    }

    @Override
    protected void genDecorations(BiomeGenBase biome)
    {
        super.genDecorations(biome);
        Random rand = new Random();
        if (biome.biomeID == WastelandBiomes.apocalypse.biomeID)
        {
            this.decorateWasteland(rand);
        } else if (biome.biomeID == WastelandBiomes.mountains.biomeID)
        {
            this.decorateMountains(rand);
        } else if (biome.biomeID == WastelandBiomes.forest.biomeID)
        {
            this.decorateForest(rand);
        } else if (biome.biomeID == WastelandBiomes.radioactive.biomeID)
        {
            this.decorateRadioactive(rand);
        }
    }

    private void decorateWasteland(Random rand)
    {
        boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 3) == 0;

        int x;
        int z;
        int pos;
        RuinGenHelper.setWorld(super.currentWorld);
        for (pos = 0; doGen && pos < 1; ++pos)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            int y = RuinGenHelper.getHeightValue(x, z);
            this.lakeGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            if (rand.nextInt(6) < 5)
            {
                this.field_76809_f.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            }
        }

        doGen = true;

        for (pos = 0; doGen && pos < this.firePerChunk; ++pos)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomFireGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 3) == 0;
        if (doGen)
        {
            Vector var8 = new Vector(
                    super.field_180294_c.getX() + super.randomGenerator.nextInt(16),
                    0,
                    super.field_180294_c.getZ() + super.randomGenerator.nextInt(16));

            for (int i = 0; i < RuinVillageGenerator.villageNum; ++i)
            {
                doGen = doGen && Vector.VtoVlengthXZ(var8, RuinVillageGenerator.villageLocation.get(i)) > 48.0D;
            }

            if (doGen)
            {
                this.house.generate(super.currentWorld, super.randomGenerator, var8.X, RuinGenHelper.getHeightValue(var8.X, var8.Z) - 1, var8.Z);
            }
        }

        doGen = rand.nextInt(ModConfig.wastelandRuinRarirty) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 15) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.deadTreeGen.setTreeType(Blocks.log, 0);
            this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z), z);
        }

    }

    private void decorateMountains(Random rand)
    {
        boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0;

        int x;
        int z;
        for (int i = 0; doGen && i < 1; ++i)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            int y = RuinGenHelper.getHeightValue(x, z);
            this.lakeGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            if (rand.nextInt(6) < 5)
            {
                this.field_76809_f.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            }
        }

        doGen = rand.nextInt(ModConfig.mountainRuinRarity * 2) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16);
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16);
            this.temple.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z), z);
        }

        doGen = rand.nextInt(ModConfig.mountainRuinRarity * 3) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 25) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16);
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16);
            this.deadTreeGen.setTreeType(Blocks.log, 0);
            this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z), z);
        }

    }

    private void decorateForest(Random rand)
    {
        boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate * 5) == 0;

        int x;
        int z;
        for (int treesPerChunk = 0; doGen && treesPerChunk < 1; ++treesPerChunk)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            int y = RuinGenHelper.getHeightValue(x, z);
            this.lakeGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            if (rand.nextInt(6) < 5)
            {
                this.field_76809_f.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            }
        }

        doGen = rand.nextInt(ModConfig.forestRuinRarity) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.forestRuinRarity * 3) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16);
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16);
            this.tent.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z) - 1, z);
        }

        doGen = rand.nextInt(ModConfig.forestRuinRarity * 2) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16);
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16);
            this.treeHouse.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z) - 1, z);
        }

        doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate) == 0;
        byte var8 = 1;

        for (int i = 0; doGen && i < var8; ++i)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16);
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16);
            this.deadTreeGen.setTreeType(ModConfig.getWoodType(super.randomGenerator));
            this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z), z);
        }

    }

    private void decorateRadioactive(Random rand)
    {
        boolean doGen = rand.nextInt(ModConfig.lakeSpawnRate / 10) == 0;

        int x;
        int z;
        int pos;
        for (pos = 0; doGen && pos < 1; ++pos)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            int y = RuinGenHelper.getHeightValue(x, z);
            this.lakeGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
            this.field_76809_f.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, y, z));
        }

        doGen = rand.nextInt(4) == 0;

        for (pos = 0; doGen && pos < 1; ++pos)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomFireGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 5) == 0;
        if (doGen)
        {
            Vector var8 = new Vector(super.field_180294_c.getX() + super.randomGenerator.nextInt(16), 0, super.field_180294_c.getZ() + super.randomGenerator.nextInt(16));

            for (int i = 0; i < RuinVillageGenerator.villageNum; ++i)
            {
                doGen = doGen && Vector.VtoVlengthXZ(var8, (Vector) RuinVillageGenerator.villageLocation.get(i)) > 48.0D;
            }

            if (doGen)
            {
                this.house.generate(super.currentWorld, super.randomGenerator, var8.X, RuinGenHelper.getHeightValue(var8.X, var8.Z) - 1, var8.Z);
            }
        }

        doGen = rand.nextInt(ModConfig.wastelandRuinRarirty * 2) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.randomRubbleGen.generate(super.currentWorld, super.randomGenerator, new BlockPos(x, RuinGenHelper.getHeightValue(x, z), z));
        }

        doGen = rand.nextInt(ModConfig.wastelandTreeSpawnRate * 75) == 0;
        if (doGen)
        {
            x = super.field_180294_c.getX() + super.randomGenerator.nextInt(16) + 8;
            z = super.field_180294_c.getZ() + super.randomGenerator.nextInt(16) + 8;
            this.deadTreeGen.setTreeType(Blocks.log, 0);
            this.deadTreeGen.generate(super.currentWorld, super.randomGenerator, x, RuinGenHelper.getHeightValue(x, z), z);
        }

    }
}
