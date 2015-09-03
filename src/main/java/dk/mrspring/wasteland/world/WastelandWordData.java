//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world;

import dk.mrspring.wasteland.utils.Vector;
import org.jnbt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class WastelandWordData
{
    private File file;

    public WastelandWordData(String filename)
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
        HashMap villages = new HashMap();
        numVillages.put("Total", new IntTag("Total", 0));
        CompoundTag villagesTag = new CompoundTag("Villages", numVillages);
        villages.put("Villages", villagesTag);
        CompoundTag globalTag = new CompoundTag("WastelandMod", villages);

        try
        {
            this.file.createNewFile();
            FileOutputStream e = new FileOutputStream(this.file);
            NBTOutputStream nos = new NBTOutputStream(e);
            nos.writeTag(globalTag);
            nos.close();
            e.close();
        } catch (IOException var7)
        {
            var7.printStackTrace();
        }

    }

    public List<Vector> loadVillageData()
    {
        ArrayList villagePos = new ArrayList();

        try
        {
            FileInputStream e = new FileInputStream(this.file);
            NBTInputStream nbt = new NBTInputStream(e);
            Map villageCollection = ((CompoundTag) this.getChildTag(((CompoundTag) nbt.readTag()).getValue(), "Villages", CompoundTag.class)).getValue();
            nbt.close();
            e.close();
            Iterator i$ = villageCollection.keySet().iterator();

            while (i$.hasNext())
            {
                String villageID = (String) i$.next();
                if (!villageID.equals("Total"))
                {
                    Map village = ((CompoundTag) this.getChildTag(villageCollection, villageID, CompoundTag.class)).getValue();
                    villagePos.add(new Vector(((Integer) this.getChildTag(village, "X", IntTag.class).getValue()).intValue(), ((Integer) this.getChildTag(village, "Y", IntTag.class).getValue()).intValue(), ((Integer) this.getChildTag(village, "Z", IntTag.class).getValue()).intValue()));
                }
            }
        } catch (Exception var8)
        {
            var8.printStackTrace();
        }

        return villagePos;
    }

    public void saveVillageData(Vector villagePosition, int villageID)
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
            HashMap location = new HashMap();
            location.put("X", new IntTag("X", villagePosition.X));
            location.put("Y", new IntTag("Y", villagePosition.Y));
            location.put("Z", new IntTag("Z", villagePosition.Z));
            CompoundTag tag = new CompoundTag(String.valueOf(villageID), location);
            tagList.add(tag);
            parentList.add("Villages");
            tagName.add(String.valueOf(villageID));
            tagList.add(new IntTag("Total", villageID));
            parentList.add("Villages");
            tagName.add(String.valueOf("Total"));
            CompoundTag newGlobalTag = this.addTags(oldGlobalTag, parentList, tagList, tagName);
            FileOutputStream fos = new FileOutputStream(this.file);
            NBTOutputStream nos = new NBTOutputStream(fos);
            nos.writeTag(new CompoundTag("WastelandMod", newGlobalTag.getValue()));
            nos.close();
            fos.close();
        } catch (Exception var14)
        {
            var14.printStackTrace();
        }

    }

    private CompoundTag addTags(CompoundTag globalTag, List<String> parentTag, List<Tag> tagMap, List<String> tagName)
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

    private Tag getChildTag(Map<String, Tag> items, String key, Class<? extends Tag> expected)
    {
        Tag tag = (Tag) items.get(key);
        return tag;
    }
}
