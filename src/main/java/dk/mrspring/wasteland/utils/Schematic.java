package dk.mrspring.wasteland.utils;

import dk.mrspring.wasteland.world.WastelandWorldData;
import java.io.IOException;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Schematic {

   public int width;
   public int length;
   public int height;
   public byte[] blocks;
   public byte[] data;
   public int chestNum;


   public Schematic(String fileName) {
      try {
         WastelandWorldData.loadSchematic(fileName + ".schematic", this);
      } catch (IOException var3) {
         var3.printStackTrace();
      }

   }

   public void load(int w, int l, int h, byte[] b, byte[] d) {
      this.width = w;
      this.length = l;
      this.height = h;
      this.blocks = b;
      this.data = d;
      this.chestNum = 0;
      int chestID = Block.getIdFromBlock(Blocks.chest);

      for(int i = 0; i < this.blocks.length; ++i) {
         if(this.blocks[i] == chestID) {
            ++this.chestNum;
         }
      }

   }
}
