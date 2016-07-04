package dk.mrspring.wasteland.client;

import cpw.mods.fml.client.FMLClientHandler;
import dk.mrspring.wasteland.gui.BiomesGui;
import java.util.List;

public class ClientProxy {

   public static void displayBiomeMap(int[][] biomeMap, int i, List biomes) {
      FMLClientHandler.instance().getClient().displayGuiScreen(new BiomesGui(biomeMap, 200, biomes));
   }
}
