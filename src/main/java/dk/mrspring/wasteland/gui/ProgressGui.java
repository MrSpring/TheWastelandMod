//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class ProgressGui extends Gui
{
    public static float progress = 0.0F;
    public static boolean visible = false;

    public ProgressGui()
    {
    }

    public static void drawProgressBar(FontRenderer font, int width, int height)
    {
        if (visible)
        {
            drawCircle(progress, (double) width / 2.0D + 0.5D, (double) height / 2.0D + 0.5D, 12, 10, -2130771794);
            font.drawString("Generating...", width / 2 - 28, height / 2 - 22, -1, true);
        }

    }

    public static void drawCircle(float progress, double x, double y, int rOuter, int rInner, int colour)
    {
        double angleInc = 0.06283185307179587D;
        float f3 = (float) (colour >> 24 & 255) / 255.0F;
        float f = (float) (colour >> 16 & 255) / 255.0F;
        float f1 = (float) (colour >> 8 & 255) / 255.0F;
        float f2 = (float) (colour & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glColor4f(f, f1, f2, f3);
        tessellator.getWorldRenderer().startDrawingQuads();
        double[] rX = new double[4];
        double[] rY = new double[4];
        int count = 0;
        double angle = 0.0D;

        for (double angleOffset = -1.5707963267948966D; angle < (double) (progress * 2.0F) * 3.141592653589793D - angleInc; ++count)
        {
            rX[0] = Math.cos(angle + angleOffset) * (double) rOuter;
            rY[0] = Math.sin(angle + angleOffset) * (double) rOuter;
            rX[1] = Math.cos(angle + angleInc + angleOffset) * (double) rOuter;
            rY[1] = Math.sin(angle + angleInc + angleOffset) * (double) rOuter;
            rX[2] = Math.cos(angle + angleInc + angleOffset) * (double) rInner;
            rY[2] = Math.sin(angle + angleInc + angleOffset) * (double) rInner;
            rX[3] = Math.cos(angle + angleOffset) * (double) rInner;
            rY[3] = Math.sin(angle + angleOffset) * (double) rInner;
            tessellator.getWorldRenderer().addVertex(rX[0] + x, rY[0] + y, 0.0D);
            tessellator.getWorldRenderer().addVertex(rX[3] + x, rY[3] + y, 0.0D);
            tessellator.getWorldRenderer().addVertex(rX[2] + x, rY[2] + y, 0.0D);
            tessellator.getWorldRenderer().addVertex(rX[1] + x, rY[1] + y, 0.0D);
            angle += angleInc;
        }

        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }
}
