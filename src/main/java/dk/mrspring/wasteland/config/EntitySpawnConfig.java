//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.config;

import dk.mrspring.wasteland.entity.EntityDayZombie;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.config.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntitySpawnConfig
{
    private static final String WASTELAND = "wasteland";
    private static final String MOUNTAINS = "mountains";
    private static final String FOREST = "forest";
    private static final String CITY = "city";
    public static List<List<SpawnListEntry>> wastelandCreatures;
    public static List<List<SpawnListEntry>> forestCreatures;
    public static List<List<SpawnListEntry>> mountainsCreatures;
    public static List<List<SpawnListEntry>> cityCreatures;
    public static boolean enableHostileSpawn;
    public static boolean enablePassiveSpawn;
    public static boolean enableWaterSpawn;

    public EntitySpawnConfig()
    {
    }

    public static void load(Configuration config)
    {
        List creatures = getAllBiomeEntities();
        addRemainingCreatures(creatures);
        List hostileCreatures = (List) creatures.get(0);
        List passiveCreatures = (List) creatures.get(1);
        List waterCreatures = (List) creatures.get(2);
        wastelandCreatures = copyArrayList(creatures);
        forestCreatures = copyArrayList(creatures);
        mountainsCreatures = copyArrayList(creatures);
        cityCreatures = copyArrayList(creatures);
        Map entities = EntityList.classToStringMapping;
        config.load();
        config.setCategoryComment("General", "");
        enableHostileSpawn = config.get("General", "Enable hostile creatures", true).getBoolean(true);
        enablePassiveSpawn = config.get("General", "Enable passive creatures", false).getBoolean(false);
        enableWaterSpawn = config.get("General", "Enable water creatures", false).getBoolean(false);
        String category = "Hostile Creature List";
        config.setCategoryComment(category, "Class name = wasteland biome, spawnWeight, minGroup, maxGorup ...  --- do not use spaces!");

        int i;
        SpawnListEntry entity;
        String creatureConfig;
        for (i = 0; i < hostileCreatures.size(); ++i)
        {
            entity = (SpawnListEntry) hostileCreatures.get(i);
            creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + String.valueOf(entity.itemWeight) + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
            setValues((SpawnListEntry) ((List) wastelandCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "wasteland"));
            setValues((SpawnListEntry) ((List) forestCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "forest"));
            setValues((SpawnListEntry) ((List) mountainsCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "mountains"));
            setValues((SpawnListEntry) ((List) cityCreatures.get(0)).get(i), getSpawnValues(creatureConfig, "city"));
        }

        category = "Passive Creature List";
        config.setCategoryComment(category, "Class name = spawnWeight , minGroup , maxGorup  --- do not use spaces!");

        for (i = 0; i < passiveCreatures.size(); ++i)
        {
            entity = (SpawnListEntry) passiveCreatures.get(i);
            creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + String.valueOf(entity.itemWeight) + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
            setValues((SpawnListEntry) ((List) wastelandCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "wasteland"));
            setValues((SpawnListEntry) ((List) forestCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "forest"));
            setValues((SpawnListEntry) ((List) mountainsCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "mountains"));
            setValues((SpawnListEntry) ((List) cityCreatures.get(1)).get(i), getSpawnValues(creatureConfig, "city"));
        }

        category = "Water Creature List";
        config.setCategoryComment(category, "Class name = spawnWeight , minGroup , maxGorup  --- do not use spaces!");

        for (i = 0; i < waterCreatures.size(); ++i)
        {
            entity = (SpawnListEntry) waterCreatures.get(i);
            creatureConfig = config.get(category, entity.entityClass.getName(), "wasteland," + String.valueOf(entity.itemWeight) + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "forest" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "mountains" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + "," + "city" + "," + entity.itemWeight + "," + entity.minGroupCount + "," + entity.maxGroupCount + ",", entities.get(entity.entityClass).toString()).getString();
            setValues((SpawnListEntry) ((List) wastelandCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "wasteland"));
            setValues((SpawnListEntry) ((List) forestCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "forest"));
            setValues((SpawnListEntry) ((List) mountainsCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "mountains"));
            setValues((SpawnListEntry) ((List) cityCreatures.get(2)).get(i), getSpawnValues(creatureConfig, "city"));
        }

        config.save();
    }

    private static void setValues(SpawnListEntry entry, int[] values)
    {
        entry.itemWeight = values[0];
        entry.minGroupCount = values[1];
        entry.maxGroupCount = values[2];
    }

    private static List<List<SpawnListEntry>> copyArrayList(List<List<SpawnListEntry>> list)
    {
        ArrayList outList = new ArrayList(list.size());

        for (int i = 0; i < list.size(); ++i)
        {
            List current = (List) list.get(i);
            ArrayList newList = new ArrayList();

            for (int j = 0; j < current.size(); ++j)
            {
                newList.add(copy((SpawnListEntry) current.get(j)));
            }

            outList.add(newList);
        }

        return outList;
    }

    private static int[] getSpawnValues(String configString, String biome)
    {
        int index = configString.indexOf(biome) + biome.length() + 1;
        String s = configString.substring(index);
        index = s.indexOf(",");
        String s1 = s.substring(0, index);
        s = s.substring(index + 1);
        index = s.indexOf(",");
        String s2 = s.substring(0, index);
        s = s.substring(index + 1);
        index = s.indexOf(",");
        String s3 = s.substring(0, index);
        return new int[]{Integer.parseInt(s1), Integer.parseInt(s2), Integer.parseInt(s3)};
    }

    private static List<List<SpawnListEntry>> getAllBiomeEntities()
    {
        ArrayList allMonsterCreatures = new ArrayList();
        ArrayList allPassiveCreatures = new ArrayList();
        ArrayList allWaterCreatures = new ArrayList();
        Type[] type = new Type[]{Type.DEAD, Type.FOREST, Type.MESA, Type.MOUNTAIN, Type.SANDY, Type.SAVANNA, Type.WASTELAND, Type.SPOOKY, Type.PLAINS, Type.SPARSE};

        for (int creatures = 0; creatures < type.length; ++creatures)
        {
            BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType(type[creatures]);

            for (int j = 0; j < biomes.length; ++j)
            {
                List monsterCreatures = biomes[j].getSpawnableList(EnumCreatureType.MONSTER);
                List passiveCreatures = biomes[j].getSpawnableList(EnumCreatureType.CREATURE);
                List waterCreatures = biomes[j].getSpawnableList(EnumCreatureType.WATER_CREATURE);

                int k;
                for (k = 0; k < monsterCreatures.size(); ++k)
                {
                    if (creatureIndex((SpawnListEntry) ((SpawnListEntry) monsterCreatures.get(k)), allMonsterCreatures) == -1)
                    {
                        allMonsterCreatures.add(copy((SpawnListEntry) monsterCreatures.get(k)));
                    }
                }

                for (k = 0; k < passiveCreatures.size(); ++k)
                {
                    if (creatureIndex((SpawnListEntry) ((SpawnListEntry) passiveCreatures.get(k)), allPassiveCreatures) == -1)
                    {
                        allPassiveCreatures.add(copy((SpawnListEntry) passiveCreatures.get(k)));
                    }
                }

                for (k = 0; k < waterCreatures.size(); ++k)
                {
                    if (creatureIndex((SpawnListEntry) ((SpawnListEntry) waterCreatures.get(k)), allWaterCreatures) == -1)
                    {
                        allWaterCreatures.add(copy((SpawnListEntry) waterCreatures.get(k)));
                    }
                }
            }
        }

        ArrayList var11 = new ArrayList(3);
        var11.add(allMonsterCreatures);
        var11.add(allPassiveCreatures);
        var11.add(allWaterCreatures);
        return var11;
    }

    private static void addRemainingCreatures(List<List<SpawnListEntry>> creatures)
    {
        Map entities = EntityList.stringToClassMapping;
        Iterator i$ = entities.keySet().iterator();

        while (true)
        {
            while (true)
            {
                Class entity;
                do
                {
                    boolean containsCreature;
                    do
                    {
                        do
                        {
                            do
                            {
                                if (!i$.hasNext())
                                {
                                    return;
                                }

                                String entityName = (String) i$.next();
                                entity = (Class) entities.get(entityName);
                                containsCreature = false;
                            } while (!EntityLiving.class.isAssignableFrom(entity));

                            for (int i = 0; i < creatures.size(); ++i)
                            {
                                if (creatureIndex(entity, (List) creatures.get(i)) >= 0)
                                {
                                    containsCreature = true;
                                }
                            }
                        } while (containsCreature);
                    } while (entity.equals(EntityMob.class));
                } while (entity.equals(EntityLiving.class));

                if (entity.equals(EntityDayZombie.class))
                {
                    ((List) creatures.get(0)).add(new SpawnListEntry(entity, 100, 0, 2));
                } else if (!IMob.class.isAssignableFrom(entity) && !EntityMob.class.isAssignableFrom(entity))
                {
                    if (EntityWaterMob.class.isAssignableFrom(entity))
                    {
                        ((List) creatures.get(2)).add(new SpawnListEntry(entity, 0, 0, 0));
                    } else
                    {
                        ((List) creatures.get(1)).add(new SpawnListEntry(entity, 0, 0, 0));
                    }
                } else
                {
                    ((List) creatures.get(0)).add(new SpawnListEntry(entity, 0, 0, 0));
                }
            }
        }
    }

    private static int creatureIndex(SpawnListEntry entry, List<SpawnListEntry> list)
    {
        return creatureIndex(entry.entityClass, list);
    }

    private static int creatureIndex(Class<?> entry, List<SpawnListEntry> list)
    {
        for (int i = 0; i < list.size(); ++i)
        {
            SpawnListEntry listEntry = (SpawnListEntry) list.get(i);
            if (entry.equals(listEntry.entityClass))
            {
                return i;
            }
        }

        return -1;
    }

    private static SpawnListEntry copy(SpawnListEntry entry)
    {
        return new SpawnListEntry(entry.entityClass, entry.itemWeight, entry.minGroupCount, entry.maxGroupCount);
    }

    public static void printString(List<SpawnListEntry> list)
    {
        Map entities = EntityList.classToStringMapping;

        for (int i = 0; i < list.size(); ++i)
        {
            SpawnListEntry entry = (SpawnListEntry) list.get(i);
            System.out.println(entities.get(entry.entityClass).toString() + ": " + entry.itemWeight + " " + entry.minGroupCount + " " + entry.maxGroupCount);
        }

    }
}
