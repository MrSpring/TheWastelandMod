//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import dk.mrspring.wasteland.config.ModConfig;
import net.minecraft.world.biome.BiomeGenBase;

public class WastelandBiomes
{
    public static BiomeGenBase apocalypse;
    public static BiomeGenBase mountains;
    public static BiomeGenBase forest;
    public static BiomeGenBase city;
    public static BiomeGenBase radioactive;

    public WastelandBiomes()
    {
    }

    static
    {
        apocalypse = BiomeGenBase.getBiome(ModConfig.apocalypseBiomeID);
        mountains = BiomeGenBase.getBiome(ModConfig.mountainBiomeID);
        forest = BiomeGenBase.getBiome(ModConfig.forestBiomeID);
        city = BiomeGenBase.getBiome(ModConfig.cityBiomeID);
        radioactive = BiomeGenBase.getBiome(ModConfig.radioactiveBiomeID);
    }
}
