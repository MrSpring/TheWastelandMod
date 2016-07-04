package dk.mrspring.wasteland.items;

import dk.mrspring.wasteland.config.CityLootConfig;
import dk.mrspring.wasteland.utils.CustomItemStack;

public class LootStack {

   public CustomItemStack[] items;
   public int maxNum;
   public int minNum;
   public boolean repeat;


   public LootStack(CustomItemStack[] items, int max, int min, boolean repeat) {
      this.items = items;
      this.maxNum = max;
      this.minNum = min;
      this.repeat = repeat;
   }

   public static LootStack[] loadCityLoot() {
      LootStack[] loot = new LootStack[]{new LootStack(CityLootConfig.getLoot(CityLootConfig.easyLoot), CityLootConfig.easyLootMax, CityLootConfig.easyLootMin, CityLootConfig.easyLootRepeat), new LootStack(CityLootConfig.getLoot(CityLootConfig.midLoot), CityLootConfig.midLootMax, CityLootConfig.midLootMin, CityLootConfig.midLootRepeat), new LootStack(CityLootConfig.getLoot(CityLootConfig.hardLoot), CityLootConfig.hardLootMax, CityLootConfig.hardLootMin, CityLootConfig.hardLootRepeat), new LootStack(CityLootConfig.getLoot(CityLootConfig.ultraLoot), CityLootConfig.ultraLootMax, CityLootConfig.ultraLootMin, CityLootConfig.ultraLootRepeat)};
      return loot;
   }
}
