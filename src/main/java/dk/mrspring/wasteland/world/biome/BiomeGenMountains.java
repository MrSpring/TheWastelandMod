//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.biome;

import dk.mrspring.wasteland.config.EntitySpawnConfig;

import java.util.List;

public class BiomeGenMountains extends BiomeGenWastelandBase
{
    public BiomeGenMountains(int par1ID, String par2Name, Height par3BiomeHeight)
    {
        super(par1ID, par2Name, par3BiomeHeight);
        this.setCreatureSpawns((List) EntitySpawnConfig.mountainsCreatures.get(0), super.spawnableMonsterList, EntitySpawnConfig.enableHostileSpawn);
        this.setCreatureSpawns((List) EntitySpawnConfig.mountainsCreatures.get(1), super.spawnableCreatureList, EntitySpawnConfig.enablePassiveSpawn);
        this.setCreatureSpawns((List) EntitySpawnConfig.mountainsCreatures.get(2), super.spawnableWaterCreatureList, EntitySpawnConfig.enableWaterSpawn);
    }
}
