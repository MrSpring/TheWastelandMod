package dk.mrspring.wasteland.client;

import net.minecraft.item.Item;

public class ItemRuinIcon extends Item {

   public ItemRuinIcon(String textureName) {
      this.setTextureName("WLM:" + textureName);
   }
}
