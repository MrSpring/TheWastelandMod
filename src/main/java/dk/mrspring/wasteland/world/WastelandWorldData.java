package dk.mrspring.wasteland.world;

import dk.mrspring.wasteland.Wasteland;
import dk.mrspring.wasteland.utils.Schematic;
import dk.mrspring.wasteland.utils.Vector;
import org.jnbt.*;

import java.io.*;
import java.util.*;

public class WastelandWorldData
{

    private File file;


    public WastelandWorldData(String filename)
    {
        this.file = new File(filename);
    }

    public WastelandWorldData()
    {
        this.file = null;
    }

    public void setFile(String filename)
    {
        this.file = new File(filename);
    }

    public boolean checkIfExists()
    {
        return this.file.exists();
    }

    public void createFile()
    {
        HashMap numVillages = new HashMap();
        HashMap numCities = new HashMap();
        HashMap global = new HashMap();
        HashMap playerNames = new HashMap();
        numVillages.put("Total", new IntTag("Total", 0));
        numCities.put("Total", new IntTag("Total", 0));
        CompoundTag villagesTag = new CompoundTag("Villages", numVillages);
        CompoundTag citiesTag = new CompoundTag("Cities", numVillages);
        CompoundTag playersTag = new CompoundTag("Players", playerNames);
        global.put("Villages", villagesTag);
        global.put("Cities", citiesTag);
        global.put("Players", playersTag);
        CompoundTag globalTag = new CompoundTag("WastelandMod", global);

        try
        {
            this.file.createNewFile();
            FileOutputStream e = new FileOutputStream(this.file);
            NBTOutputStream nos = new NBTOutputStream(e);
            nos.writeTag(globalTag);
            nos.close();
            e.close();
        } catch (IOException var11)
        {
            var11.printStackTrace();
        }

    }

    public List loadVillageData()
    {
        ArrayList villagePos = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            Map villageCollection = ((CompoundTag) getChildTag(((CompoundTag) nbt.readTag()).getValue(), "Villages", CompoundTag.class)).getValue();
            nbt.close();
            e.close();
            Iterator i$ = villageCollection.keySet().iterator();

            while (i$.hasNext())
            {
                String villageID = (String) i$.next();
                if (!villageID.equals("Total"))
                {
                    Map village = ((CompoundTag) getChildTag(villageCollection, villageID, CompoundTag.class)).getValue();
                    villagePos.add(new Vector(((Integer) getChildTag(village, "X", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(village, "Y", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(village, "Z", IntTag.class).getValue()).intValue()));
                }
            }
        } catch (Exception var8)
        {
            var8.printStackTrace();
        }

        return villagePos;
    }

    public void saveVillageData(List villagePosition)
    {
        ArrayList tagList = new ArrayList();
        ArrayList parentList = new ArrayList();
        ArrayList tagName = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            CompoundTag oldGlobalTag = (CompoundTag) nbt.readTag();
            nbt.close();
            e.close();

            for (int newGlobalTag = 0; newGlobalTag < villagePosition.size(); ++newGlobalTag)
            {
                HashMap fos = new HashMap();
                fos.put("X", new IntTag("X", ((Vector) villagePosition.get(newGlobalTag)).X));
                fos.put("Y", new IntTag("Y", ((Vector) villagePosition.get(newGlobalTag)).Y));
                fos.put("Z", new IntTag("Z", ((Vector) villagePosition.get(newGlobalTag)).Z));
                CompoundTag nos = new CompoundTag(String.valueOf(newGlobalTag + 1), fos);
                tagList.add(nos);
                parentList.add("Villages");
                tagName.add(String.valueOf(newGlobalTag + 1));
            }

            tagList.add(new IntTag("Total", villagePosition.size()));
            parentList.add("Villages");
            tagName.add(String.valueOf("Total"));
            CompoundTag var13 = this.addTags(oldGlobalTag, parentList, tagList, tagName);
            FileOutputStream var14 = new FileOutputStream(this.file);
            NBTOutputStream var12 = new NBTOutputStream(var14);
            var12.writeTag(new CompoundTag("WastelandMod", var13.getValue()));
            var12.close();
            var14.close();
        } catch (Exception var11)
        {
            var11.printStackTrace();
        }

    }

    public List loadCityData()
    {
        ArrayList cityPos = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            Map cityCollection = ((CompoundTag) getChildTag(((CompoundTag) nbt.readTag()).getValue(), "Cities", CompoundTag.class)).getValue();
            nbt.close();
            e.close();
            Iterator i$ = cityCollection.keySet().iterator();

            while (i$.hasNext())
            {
                String cityID = (String) i$.next();
                if (!cityID.equals("Total"))
                {
                    Map city = ((CompoundTag) getChildTag(cityCollection, cityID, CompoundTag.class)).getValue();
                    cityPos.add(new Vector(((Integer) getChildTag(city, "X", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(city, "Y", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(city, "Z", IntTag.class).getValue()).intValue()));
                }
            }
        } catch (Exception var8)
        {
            var8.printStackTrace();
        }

        return cityPos;
    }

    public void saveCityData(List cityPosition)
    {
        ArrayList tagList = new ArrayList();
        ArrayList parentList = new ArrayList();
        ArrayList tagName = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            CompoundTag oldGlobalTag = (CompoundTag) nbt.readTag();
            nbt.close();
            e.close();

            for (int newGlobalTag = 0; newGlobalTag < cityPosition.size(); ++newGlobalTag)
            {
                HashMap fos = new HashMap();
                fos.put("X", new IntTag("X", ((Vector) cityPosition.get(newGlobalTag)).X));
                fos.put("Y", new IntTag("Y", ((Vector) cityPosition.get(newGlobalTag)).Y));
                fos.put("Z", new IntTag("Z", ((Vector) cityPosition.get(newGlobalTag)).Z));
                CompoundTag nos = new CompoundTag(String.valueOf(newGlobalTag + 1), fos);
                tagList.add(nos);
                parentList.add("Cities");
                tagName.add(String.valueOf(newGlobalTag + 1));
            }

            tagList.add(new IntTag("Total", cityPosition.size()));
            parentList.add("Cities");
            tagName.add(String.valueOf("Total"));
            CompoundTag var13 = this.addTags(oldGlobalTag, parentList, tagList, tagName);
            FileOutputStream var14 = new FileOutputStream(this.file);
            NBTOutputStream var12 = new NBTOutputStream(var14);
            var12.writeTag(new CompoundTag("WastelandMod", var13.getValue()));
            var12.close();
            var14.close();
        } catch (Exception var11)
        {
            var11.printStackTrace();
        }

    }

    public List getPlayerNames()
    {
        ArrayList names = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            Map playerCollection = ((CompoundTag) getChildTag(((CompoundTag) nbt.readTag()).getValue(), "Players", CompoundTag.class)).getValue();
            nbt.close();
            e.close();
            Iterator i$ = playerCollection.keySet().iterator();

            while (i$.hasNext())
            {
                String PlayerName = (String) i$.next();
                names.add(PlayerName);
            }
        } catch (Exception var7)
        {
            var7.printStackTrace();
        }

        return names.isEmpty() ? null : names;
    }

    public void savePlayerNames(List names)
    {
        ArrayList tagList = new ArrayList();
        ArrayList parentList = new ArrayList();
        ArrayList tagName = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            CompoundTag oldGlobalTag = (CompoundTag) nbt.readTag();
            nbt.close();
            e.close();

            for (int newGlobalTag = 0; newGlobalTag < names.size(); ++newGlobalTag)
            {
                StringTag fos = new StringTag((String) names.get(newGlobalTag), (String) names.get(newGlobalTag));
                tagList.add(fos);
                parentList.add("Players");
                tagName.add(String.valueOf(names.get(newGlobalTag)));
            }

            CompoundTag var12 = this.addTags(oldGlobalTag, parentList, tagList, tagName);
            FileOutputStream var13 = new FileOutputStream(this.file);
            NBTOutputStream nos = new NBTOutputStream(var13);
            nos.writeTag(new CompoundTag("WastelandMod", var12.getValue()));
            nos.close();
            var13.close();
        } catch (Exception var11)
        {
            var11.printStackTrace();
        }

    }

    public void savePlayerName(String name)
    {
        ArrayList names = new ArrayList();
        names.add(name);
        this.savePlayerNames(names);
    }

    private CompoundTag addTags(CompoundTag globalTag, List parentTag, List tagMap, List tagName)
    {
        Map tagCollection = globalTag.getValue();
        Set keys = tagCollection.keySet();
        HashMap newTagCollection = new HashMap();
        Iterator i = keys.iterator();

        while (i.hasNext())
        {
            String key = (String) i.next();
            Class t = ((Tag) tagCollection.get(key)).getClass();
            if (t.getName().endsWith("StringTag"))
            {
                newTagCollection.put(key, new StringTag(key, (String) ((Tag) tagCollection.get(key)).getValue()));
            } else if (t.getName().endsWith("IntTag"))
            {
                newTagCollection.put(key, new IntTag(key, ((Integer) ((Tag) tagCollection.get(key)).getValue()).intValue()));
            } else if (t.getName().endsWith("CompoundTag"))
            {
                newTagCollection.put(key, this.addTags((CompoundTag) tagCollection.get(key), parentTag, tagMap, tagName));
            } else
            {
                System.out.println("Unknown Tag Class: " + t.getName());
            }
        }

        for (int var11 = 0; var11 < tagMap.size(); ++var11)
        {
            if (globalTag.getName().equals(parentTag.get(var11)))
            {
                newTagCollection.put(tagName.get(var11), tagMap.get(var11));
            }
        }

        return new CompoundTag(globalTag.getName(), newTagCollection);
    }

    private CompoundTag copyAllTags(CompoundTag tag)
    {
        Map tagCollection = tag.getValue();
        Set keys = tagCollection.keySet();
        HashMap newTagCollection = new HashMap();
        Iterator i$ = keys.iterator();

        while (i$.hasNext())
        {
            String key = (String) i$.next();
            Class t = ((Tag) tagCollection.get(key)).getClass();
            if (t.getName().endsWith("StringTag"))
            {
                newTagCollection.put(key, new StringTag(key, (String) ((Tag) tagCollection.get(key)).getValue()));
            } else if (t.getName().endsWith("IntTag"))
            {
                newTagCollection.put(key, new IntTag(key, ((Integer) ((Tag) tagCollection.get(key)).getValue()).intValue()));
            } else if (t.getName().endsWith("CompoundTag"))
            {
                newTagCollection.put(key, this.copyAllTags((CompoundTag) tagCollection.get(key)));
            } else
            {
                System.out.println("Unknown Tag Class: " + t.getName());
            }
        }

        return new CompoundTag(tag.getName(), newTagCollection);
    }

    private static Tag getChildTag(Map items, String key, Class expected)
    {
        Tag tag = (Tag) items.get(key);
        return tag;
    }

    public void saveSpawnLoc(Vector spawn)
    {
        ArrayList tagList = new ArrayList();
        ArrayList parentList = new ArrayList();
        ArrayList tagName = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            CompoundTag oldGlobalTag = (CompoundTag) nbt.readTag();
            nbt.close();
            e.close();
            HashMap spawnTag = new HashMap();
            spawnTag.put("spawnX", new IntTag("spawnX", spawn.X));
            spawnTag.put("spawnY", new IntTag("spawnY", spawn.Y));
            spawnTag.put("spawnZ", new IntTag("spawnZ", spawn.Z));
            CompoundTag tag = new CompoundTag("Spawn", spawnTag);
            tagList.add(tag);
            parentList.add("WastelandMod");
            tagName.add(String.valueOf("Spawn"));
            CompoundTag newGlobalTag = this.addTags(oldGlobalTag, parentList, tagList, tagName);
            FileOutputStream fos = new FileOutputStream(this.file);
            NBTOutputStream nos = new NBTOutputStream(fos);
            nos.writeTag(new CompoundTag("WastelandMod", newGlobalTag.getValue()));
            nos.close();
            fos.close();
        } catch (Exception var13)
        {
            var13.printStackTrace();
        }

    }

    public Vector loadSpawnLoc()
    {
        Vector spawn = null;

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            Map spawnLoc = ((CompoundTag) getChildTag(((CompoundTag) nbt.readTag()).getValue(), "Spawn", CompoundTag.class)).getValue();
            nbt.close();
            e.close();
            spawn = new Vector(((Integer) getChildTag(spawnLoc, "spawnX", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(spawnLoc, "spawnY", IntTag.class).getValue()).intValue(), ((Integer) getChildTag(spawnLoc, "spawnZ", IntTag.class).getValue()).intValue());
        } catch (Exception var5)
        {
            var5.printStackTrace();
        }

        return spawn;
    }

    public static void loadSchematic(InputStream is, Schematic s) throws IOException
    {
        NBTInputStream nbt = new NBTInputStream(is);
        CompoundTag globalTag = (CompoundTag) nbt.readTag();
        nbt.close();
        is.close();
        Map tagCollection = globalTag.getValue();
        short width = ((Short) getChildTag(tagCollection, "Width", ShortTag.class).getValue()).shortValue();
        short length = ((Short) getChildTag(tagCollection, "Length", ShortTag.class).getValue()).shortValue();
        short height = ((Short) getChildTag(tagCollection, "Height", ShortTag.class).getValue()).shortValue();
        byte[] blocks = (byte[]) ((byte[]) getChildTag(tagCollection, "Blocks", ByteArrayTag.class).getValue());
        byte[] data = (byte[]) ((byte[]) getChildTag(tagCollection, "Data", ByteArrayTag.class).getValue());
        s.load(width, length, height, blocks, data);
    }

    public static void loadSchematic(String s, Schematic sch) throws IOException
    {
        loadSchematic(Wasteland.class.getClassLoader().getResourceAsStream("assets/wlm/schematics/" + s), sch);
    }
}
