package dk.mrspring.wasteland.utils;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;

public class CustomItemStack {

   public ItemStack items;
   public int maxItems;
   public int minItems;


   public CustomItemStack(ItemStack items, int max, int min) {
      this.items = items;
      this.maxItems = max;
      this.minItems = min;
   }

   public static ItemStack[] getLootItems(Random rand, CustomItemStack[] items, int minItems, int maxItems, boolean repeat) {
      int numofItems = minItems + rand.nextInt(maxItems - minItems + 1);
      numofItems = numofItems < 0?0:(numofItems > items.length?items.length:numofItems);
      ItemStack[] returned_itemStack = new ItemStack[numofItems];
      ArrayList temp_items = new ArrayList();

      int i;
      for(i = 0; i < items.length; ++i) {
         CustomItemStack itemIndex = new CustomItemStack(ItemStack.copyItemStack(items[i].items), items[i].maxItems, items[i].minItems);
         copyDamage(itemIndex.items, items[i].items);
         temp_items.add(itemIndex);
      }

      for(i = 0; i < numofItems && !temp_items.isEmpty(); ++i) {
         int var11 = rand.nextInt(temp_items.size());
         CustomItemStack item = (CustomItemStack)temp_items.get(var11);
         item.items.stackSize = Math.min(item.items.getItem().getItemStackLimit(item.items), rand.nextInt(item.maxItems - item.minItems + 1) + item.minItems);
         returned_itemStack[i] = item.items;
         if(!repeat) {
            temp_items.remove(var11);
         }
      }

      return returned_itemStack;
   }

   public static void placeLoot(Random rand, TileEntityChest chest, ItemStack[] loot) {
      for(int i = 0; i < loot.length; ++i) {
         int slot;
         for(slot = rand.nextInt(chest.getSizeInventory()); chest.getStackInSlot(slot) != null; slot = slot >= chest.getSizeInventory() - 1?0:slot + 1) {
            ;
         }

         if(loot[i] != null) {
            chest.setInventorySlotContents(slot, loot[i]);
         }
      }

   }

   private static void copyDamage(ItemStack newItem, ItemStack oldItem) {
      newItem.setItemDamage(oldItem.getItemDamage());
   }
}
