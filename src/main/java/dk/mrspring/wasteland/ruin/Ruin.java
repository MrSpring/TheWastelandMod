package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.config.RuinConfig;
import dk.mrspring.wasteland.items.LootStack;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

public class Ruin {

   protected String name;
   protected int id;
   protected int rarity = 1;
   protected int weight = 10;
   protected ItemStack[] loot;
   public static LootStack normalLoot;
   public static LootStack rareLoot;
   public static LootStack seedLoot;


   public Ruin(String par1Name) {
      this.name = par1Name;
      normalLoot = new LootStack(RuinConfig.getLoot(RuinConfig.ruinEasyLoot), RuinConfig.ruinEasyLootMax, RuinConfig.ruinEasyLootMin, RuinConfig.ruinEasyLootRepeat);
      rareLoot = new LootStack(RuinConfig.getLoot(RuinConfig.ruinRareLoot), RuinConfig.ruinRareLootMax, RuinConfig.ruinRareLootMin, RuinConfig.ruinRareLootRepeat);
      seedLoot = new LootStack(RuinConfig.getLoot(RuinConfig.seedLoot), RuinConfig.seedLootMax, RuinConfig.seedLootMin, RuinConfig.seedLootRepeat);
   }

   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + ".name");
   }

   public String getUnlocalizedName() {
      return "ruin." + this.name;
   }

   protected LootStack setItems(Random random) {
      return random.nextInt(RuinConfig.rareRuinLootChance) == 0?rareLoot:normalLoot;
   }

   public Ruin setWeight(int par1Weight) {
      this.weight = par1Weight;
      return this;
   }

   protected boolean generate(World world, Random random, int x, int y, int z) {
      return false;
   }

   public LootStack setSeedItems() {
      return seedLoot;
   }

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      if(world.provider.getDimensionId() == 0) {
         this.generateSurface(world, random, chunkX * 16, chunkZ * 16);
      }

   }

   protected void generateSurface(World world, Random random, int i, int j) {
      BlockPos pos = world.getHeight(new BlockPos(i, 0, j)).add(random.nextInt(16), 0, random.nextInt(16));
      if(!world.isRemote) {
         this.generate(world, random, pos.getX(), pos.getY(), pos.getZ());
      }

   }
}
