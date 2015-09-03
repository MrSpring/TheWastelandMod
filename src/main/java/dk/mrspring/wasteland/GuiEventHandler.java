//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.gui.ProgressGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;

@SideOnly(Side.CLIENT)
public class GuiEventHandler
{
    public static boolean visible = true;
    @SideOnly(Side.CLIENT)
    public static Minecraft client = FMLClientHandler.instance().getClient();

    public GuiEventHandler()
    {
    }

    @SubscribeEvent(
            priority = EventPriority.NORMAL
    )
    public void onRenderProgressBar(Text event)
    {
        if (client.inGameHasFocus || client.currentScreen instanceof GuiChat)
        {
            ScaledResolution scaledresolution = event.resolution;
            ProgressGui.drawProgressBar(client.fontRenderer, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
        }

    }
}
