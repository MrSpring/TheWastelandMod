package dk.mrspring.wasteland.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.List;

@SideOnly(Side.CLIENT)
public class BiomesGui extends GuiScreen
{
    public static final int GUI_ID = 0;
    private int[][] map;
    private int mapSize = 0;
    private List<BiomeGenBase> biomesList;
    private int shiftLeftPercent;

    public BiomesGui(int[][] map, int size, List<BiomeGenBase> biomes)
    {
        this.map = map;
        this.mapSize = size;
        this.biomesList = biomes;
        this.shiftLeftPercent = 10;
    }

    public void initGui()
    {
    }

    public void drawScreen(int i, int j, float f)
    {
        if (this.map != null || this.mapSize > 0)
        {
            int pixelSize = (int) (0.85D * (double) super.height / (double) this.mapSize);
            this.drawDefaultBackground();
            this.drawBiomes(pixelSize);
            this.drawBiomeNames(this.biomesList, pixelSize);
        }

        super.drawScreen(i, j, f);
    }

    private void drawBiomeNames(List<BiomeGenBase> biomes, int pixelWidth)
    {
        int shiftX = (int) ((double) this.shiftLeftPercent / 100.0D * (double) super.width);
        FontRenderer font = FMLClientHandler.instance().getClient().fontRendererObj;

        int x = (int) (0.77D * (double) super.width);
        int y = (int) (0.1D * (double) super.height);

        for (int i = 0; i < biomes.size(); ++i)
        {
            this.drawString(font, biomes.get(i).biomeName, x - shiftX, y + i * (font.FONT_HEIGHT + 2), (biomes.get(i).color & 16777215) + -16777216);
        }

    }

    private void drawBiomes(int pixelWidth)
    {
        int shiftX = (int) ((double) this.shiftLeftPercent / 100.0D * (double) super.width);
        int centerX;
        int centerY;
        if ((this.mapSize & 1) == 0)
        {
            centerX = super.width / 2;
            centerY = super.height / 2;
        } else
        {
            centerX = (super.width - pixelWidth) / 2;
            centerY = (super.height - pixelWidth) / 2;
        }

        for (int j = 0; j < this.mapSize; ++j)
        {
            for (int i = 0; i < this.mapSize; ++i)
            {
                drawRect(centerX - this.mapSize / 2 * pixelWidth + i * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + j * pixelWidth, centerX - this.mapSize / 2 * pixelWidth + (i + 1) * pixelWidth - shiftX, centerY - this.mapSize / 2 * pixelWidth + (j + 1) * pixelWidth, this.map[j][i]);
            }
        }

    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
