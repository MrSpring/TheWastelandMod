package dk.mrspring.wasteland;

import dk.mrspring.wasteland.gui.ProgressGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Text;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiEventHandler {

   public static boolean visible = true;
   @SideOnly(Side.CLIENT)
   public static Minecraft client = FMLClientHandler.instance().getClient();


   @SubscribeEvent(
      priority = EventPriority.NORMAL
   )
   public void onRenderProgressBar(Text event) {
      if(client.inGameHasFocus || client.currentScreen instanceof GuiChat) {
         ScaledResolution scaledresolution = event.resolution;
         ProgressGui.drawProgressBar(client.fontRendererObj, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
      }

   }

}
