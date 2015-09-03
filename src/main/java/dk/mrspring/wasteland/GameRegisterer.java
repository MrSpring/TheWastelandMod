//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class GameRegisterer
{
    public GameRegisterer()
    {
    }

    public static void registerBiome(BiomeGenBase biome, Type... types)
    {
        BiomeDictionary.registerBiomeType(biome, types);
    }
}
