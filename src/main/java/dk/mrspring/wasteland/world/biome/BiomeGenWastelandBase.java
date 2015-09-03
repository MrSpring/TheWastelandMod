//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.biome;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.world.gen.BiomeDecoratorWasteland;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;

import java.util.List;

public class BiomeGenWastelandBase extends BiomeGenBase
{
    public static final Height height_Wasteland = new Height(0.1F, 0.05F);
    public static final Height height_WastelandCity = new Height(0.09F, 0.01F);
    public static final Height height_WastelandMountains = new Height(1.0F, 0.5F);
    private static int lastID = 0;

    public BiomeGenWastelandBase(int par1ID, String par2Name, Height biomeHeight)
    {
        super(par1ID);
        super.enableRain = true;
        super.biomeName = par2Name;
        this.setHeight(biomeHeight);
        super.theBiomeDecorator = new BiomeDecoratorWasteland();
        super.waterColorMultiplier = 3376435;
        ++lastID;
        this.loadBiome();
    }

    public static void load()
    {
        BiomeGenBase apocalypse = (new BiomeGenApocalypse(ModConfig.apocalypseBiomeID, "Wasteland", height_Wasteland)).setColor(14728553);
        BiomeGenBase apocMountains = (new BiomeGenMountains(ModConfig.mountainBiomeID, "Wasteland Mountains", height_WastelandMountains)).setColor(10255379);
        BiomeGenBase apocForest = (new BiomeGenForest(ModConfig.forestBiomeID, "Wasteland Forest", height_Wasteland)).setColor(10793807);
        BiomeGenBase apocCity = (new BiomeGenCity(ModConfig.cityBiomeID, "Wasteland City", height_WastelandCity)).setColor(9410739);
        BiomeGenBase radioactive = (new BiomeGenRadioactive(ModConfig.radioactiveBiomeID, "Radioactive Wasteland", height_Wasteland)).setColor(6088238);
        BiomeDictionary.registerBiomeType(apocalypse, new Type[]{Type.WASTELAND});
        BiomeDictionary.registerBiomeType(apocMountains, new Type[]{Type.WASTELAND, Type.MOUNTAIN});
        BiomeDictionary.registerBiomeType(apocForest, new Type[]{Type.WASTELAND, Type.FOREST});
        BiomeDictionary.registerBiomeType(apocCity, new Type[]{Type.WASTELAND, Type.DEAD});
        BiomeDictionary.registerBiomeType(radioactive, new Type[]{Type.WASTELAND});
        BiomeManager.addSpawnBiome(apocalypse);
        BiomeManager.addSpawnBiome(apocMountains);
        BiomeManager.addSpawnBiome(apocForest);
        BiomeManager.addSpawnBiome(apocCity);
        BiomeManager.addSpawnBiome(radioactive);
    }

    public BiomeGenWastelandBase setTopBlock(Block block, int meta)
    {
        super.topBlock = block;
        super.field_150604_aj = meta;
        return this;
    }

    public BiomeGenWastelandBase setFillerBlock(Block block)
    {
        super.fillerBlock = block;
        return this;
    }

    public void loadBiome()
    {
        super.theBiomeDecorator.deadBushPerChunk = 5;
        super.theBiomeDecorator.flowersPerChunk = -999;
        super.theBiomeDecorator.generateLakes = false;
        super.theBiomeDecorator.grassPerChunk = -999;
        super.theBiomeDecorator.treesPerChunk = -999;
        this.setTopBlock(ModConfig.getSurfaceBlock(), ModConfig.getSurfaceBlockMeta());
        this.setFillerBlock(Blocks.stone);
    }

    public void setCreatureSpawns(List<SpawnListEntry> entries, List<SpawnListEntry> biomeEntities, boolean spawn)
    {
        biomeEntities.clear();
        if (spawn)
        {
            for (int i = 0; i < entries.size(); ++i)
            {
                SpawnListEntry entry = (SpawnListEntry) entries.get(i);
                if (entry.itemWeight > 0 && entry.maxGroupCount > 0)
                {
                    biomeEntities.add(entries.get(i));
                }
            }
        }

    }
}
