//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.client;

import cpw.mods.fml.client.FMLClientHandler;
import dk.mrspring.wasteland.gui.BiomesGui;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.List;

public class ClientProxy
{
    public ClientProxy()
    {
    }

    public static void displayBiomeMap(int[][] biomeMap, int i, List<BiomeGenBase> biomes)
    {
        FMLClientHandler.instance().getClient().displayGuiScreen(new BiomesGui(biomeMap, 200, biomes));
    }
}
