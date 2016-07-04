package dk.mrspring.wasteland.gui;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.biome.BiomeGenBase;

@SideOnly(Side.CLIENT)
public class BiomesGui extends GuiScreen {

   public static final int GUI_ID = 0;
   private int[][] map;
   private int mapSize = 0;
   private List biomesList;
   private int shiftLeftPercent;


   public BiomesGui(int[][] map, int size, List biomes) {
      this.map = map;
      this.mapSize = size;
      this.biomesList = biomes;
      this.shiftLeftPercent = 10;
   }

   public void initGui() {}

   public void drawScreen(int i, int j, float f) {
      if(this.map != null || this.mapSize > 0) {
         int pixelSize = (int)(0.85D * (double)super.height / (double)this.mapSize);
         this.drawDefaultBackground();
         this.drawBiomes(pixelSize);
         this.drawBiomeNames(this.biomesList, pixelSize);
      }

      super.drawScreen(i, j, f);
   }

   private void drawBiomeNames(List biomes, int pixelWidth) {
      int shiftX = (int)((double)this.shiftLeftPercent / 100.0D * (double)super.width);
      FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
      if((pixelWidth & 1) != 0) {
         int var10000 = (int)((double)pixelWidth / 2.0D);
      }

      int x = (int)(0.77D * (double)super.width);
      int y = (int)(0.1D * (double)super.height);

      for(int i = 0; i < biomes.size(); ++i) {
         this.drawString(font, ((BiomeGenBase)biomes.get(i)).biomeName, x - shiftX, y + i * (font.FONT_HEIGHT + 2), (((BiomeGenBase)biomes.get(i)).color & 16777215) + -16777216);
      }

   }

   private void drawBiomes(int pixelWidth) {
      int shiftX = (int)((double)this.shiftLeftPercent / 100.0D * (double)super.width);
      int centerX;
      int centerY;
      if((this.mapSize & 1) == 0) {
         centerX = super.width / 2;
         centerY = super.height / 2;
      } else {
         centerX = (super.width - pixelWidth) / 2;
         centerY = (super.height - pixelWidth) / 2;
      }

      for(int j = 0; j < this.mapSize; ++j) {
         for(int i = 0; i < this.mapSize; ++i) {
            drawRect(centerX - this.mapSize / 2 * pixelWidth + i * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + j * pixelWidth, centerX - this.mapSize / 2 * pixelWidth + (i + 1) * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + (j + 1) * pixelWidth, this.map[j][i]);
         }
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void keyTyped(char c, int i) {
      super.keyTyped(c, i);
   }

   public void mouseClicked(int i, int j, int k) {
      super.mouseClicked(i, j, k);
   }

   public void updateScreen() {
      super.updateScreen();
   }

   public void onGuiClosed() {
      super.onGuiClosed();
   }

   public void actionPerformed(GuiButton b) {
      super.actionPerformed(b);
   }
}
