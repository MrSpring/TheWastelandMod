package dk.mrspring.wasteland.world.biome;

import dk.mrspring.wasteland.config.EntitySpawnConfig;
import dk.mrspring.wasteland.world.biome.BiomeGenWastelandBase;
import java.util.List;
import net.minecraft.world.biome.BiomeGenBase.Height;

public class BiomeGenRadioactive extends BiomeGenWastelandBase {

   public BiomeGenRadioactive(int par1ID, String par2Name, Height par3BiomeHeight) {
      super(par1ID, par2Name, par3BiomeHeight);
      this.setCreatureSpawns((List)EntitySpawnConfig.cityCreatures.get(0), super.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
      this.setCreatureSpawns((List)EntitySpawnConfig.cityCreatures.get(1), super.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
      this.setCreatureSpawns((List)EntitySpawnConfig.cityCreatures.get(2), super.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
   }
}
