//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.client;

import dk.mrspring.wasteland.gui.BiomesGui;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.util.List;

public class ClientProxy
{
    public static void displayBiomeMap(int[][] biomeMap, int i, List<BiomeGenBase> biomes)
    {
        FMLClientHandler.instance().getClient().displayGuiScreen(new BiomesGui(biomeMap, 200, biomes));
    }
}
