package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.config.RuinConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Sphere;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class Building
{

    public int width;
    public int height;
    public int length;
    public int rotation;
    public String name;
    public boolean duplicate;
    private byte[] blocks;
    private byte[] data;
    private List entities;
    private List tileentities;
    private RuinGenHelper genHelper = new RuinGenHelper();
    public static LootStack easyLoot;
    public static LootStack midLoot;
    public static LootStack hardLoot;
    public static final int CLOCK_TOWER = 0;
    public static final int CHURCH = 1;
    public static final int DINER = 2;
    public static final int STAND = 3;
    public static final int HOSPITAL = 4;
    public static final int L_FARM = 5;
    public static final int L_HOUSE1 = 6;
    public static final int L_HOUSE2 = 7;
    public static final int LIBRARY = 8;
    public static final int M_HOUSE1 = 9;
    public static final int M_HOUSE2 = 10;
    public static final int S_FARM = 11;
    public static final int S_HOUSE1 = 12;
    public static final int S_HOUSE2 = 13;
    public static final int WELL = 14;


    public Building(String name, int w, int h, int l, byte[] b, byte[] d, boolean multiple)
    {
        this.width = w;
        this.height = h;
        this.length = l;
        this.blocks = (byte[]) b.clone();
        this.data = d;
        this.name = name;
        this.duplicate = multiple;
        easyLoot = new LootStack(RuinConfig.getLoot(RuinConfig.easyLoot), RuinConfig.easyLootMax, RuinConfig.easyLootMin, RuinConfig.easyLootRepeat);
        midLoot = new LootStack(RuinConfig.getLoot(RuinConfig.midLoot), RuinConfig.midLootMax, RuinConfig.midLootMin, RuinConfig.midLootRepeat);
        hardLoot = new LootStack(RuinConfig.getLoot(RuinConfig.hardLoot), RuinConfig.hardLootMax, RuinConfig.hardLootMin, RuinConfig.hardLootRepeat);
    }

    public static Building create(int building)
    {
        switch (building)
        {
            case 0:
                //return new Building("cTower", 13, 34, 13, BuildingCode..BLOCKS, BuildingCode.DATA, false);
            case 1:
                return new Building("church", 9, 12, 5, BuildingCode.Church.BLOCKS, BuildingCode.Church.DATA, false);
            case 2:
                return new Building("diner", 10, 11, 16, BuildingCode.Diner.BLOCKS, BuildingCode.Diner.DATA, false);
            case 3:
                return new Building("stand", 5, 5, 3, BuildingCode.GroceryStand.BLOCKS, BuildingCode.GroceryStand.DATA, true); // ?
            case 4:
                return new Building("hospital", 24, 13, 17, BuildingCode.Hospital.BLOCKS, BuildingCode.Hospital.DATA, false);
            case 5:
                return new Building("largeFarm", 14, 10, 27, BuildingCode.LargeFarm.BLOCKS, BuildingCode.LargeFarm.DATA, false);
            case 6:
                return new Building("largeHouse1", 11, 9, 9, BuildingCode.LargeHouse1.BLOCKS, BuildingCode.LargeHouse1.DATA, false);
            case 7:
                return new Building("largeHouse2", 12, 12, 11, BuildingCode.LargeHouse2.BLOCKS, BuildingCode.LargeHouse2.DATA, false);
            case 8:
                return new Building("library", 14, 10, 14, BuildingCode.Library.BLOCKS, BuildingCode.Library.DATA, false);
            case 9:
                return new Building("midHouse1", 9, 7, 12, BuildingCode.MidHouse1.BLOCKS, BuildingCode.MidHouse1.DATA, true);
            case 10:
                return new Building("midHouse2", 9, 9, 8, BuildingCode.MidHouse2.BLOCKS, BuildingCode.MidHouse2.DATA, true);
            case 11:
                return new Building("smallFarm", 12, 7, 9, BuildingCode.SmallFarm.BLOCKS, BuildingCode.SmallFarm.DATA, true);
            case 12:
                return new Building("smallHouse1", 5, 5, 4, BuildingCode.SmallHouse1.BLOCKS, BuildingCode.SmallHouse1.DATA, true);
            case 13:
                return new Building("smallHouse2", 5, 6, 5, BuildingCode.SmallHouse2.BLOCKS, BuildingCode.SmallHouse2.DATA, true);
            case 14:
                return new Building("well", 4, 5, 4, BuildingCode.Well.BLOCKS, BuildingCode.Well.DATA, false);
            default:
                return null;
        }
    }

    public boolean generate(World world, Random random, Vector pos, int rot)
    {
        Block surfaceBlock = ModConfig.getSurfaceBlock();
        int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();
        RuinGenHelper var10000 = this.genHelper;
        RuinGenHelper.setWorld(world);
        byte maxSize;
        byte minSize;
        int numHoles;
        if (this.blocks.length < 100)
        {
            numHoles = random.nextInt(2) + 2;
            maxSize = 2;
            minSize = 0;
        } else if (this.blocks.length >= 100 && this.blocks.length < 500)
        {
            numHoles = random.nextInt(2) + 2;
            maxSize = 2;
            minSize = 1;
        } else if (this.blocks.length >= 500 && this.blocks.length < 1500)
        {
            numHoles = random.nextInt(2) + 1;
            maxSize = 3;
            minSize = 2;
        } else
        {
            numHoles = random.nextInt(4) + 2;
            maxSize = 4;
            minSize = 2;
        }

        int x;
        int z;
        if (this.name.equals(create(14).name))
        {
            x = random.nextInt(7) + 10;

            for (z = 0; z < x; ++z)
            {
                world.setBlock(pos.X + 1, pos.Y - 1 - z, pos.Z + 1, Blocks.water);
                world.setBlock(pos.X + 2, pos.Y - 1 - z, pos.Z + 1, Blocks.water);
                world.setBlock(pos.X + 2, pos.Y - 1 - z, pos.Z + 2, Blocks.water);
                world.setBlock(pos.X + 1, pos.Y - 1 - z, pos.Z + 2, Blocks.water);
            }
        } else
        {
            this.ruinBuilding(numHoles, maxSize, minSize, random);
        }

        int count = 0;

        for (short j = 0; j < this.height; ++j)
        {
            for (short k = 0; k < this.length; ++k)
            {
                for (short i = 0; i < this.width; ++i)
                {
                    if (rot == 0)
                    {
                        x = i;
                        z = k;
                    } else if (rot == 1)
                    {
                        x = this.width - i - 1;
                        z = k;
                    } else if (rot == 2)
                    {
                        x = this.width - i - 1;
                        z = this.width - k - 1;
                    } else
                    {
                        x = i;
                        z = this.width - k - 1;
                    }

                    if (this.blocks[count] == 7)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, ModConfig.getSurfaceBlock(), surfaceBlockMeta);
                    } else if (this.blocks[count] != 2)
                    {
                        if (this.blocks[count] == 54)
                        {
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, Blocks.chest);
                            TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.X + x, pos.Y + j, pos.Z + z);
                            LootStack loot = this.setItems(random);
                            CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
                        } else
                        {
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(pos.X + x, pos.Y + j, pos.Z + z, Block.getBlockById(this.blocks[count]), this.data[count]);
                        }
                    }

                    ++count;
                }
            }
        }

        return true;
    }

    private LootStack setItems(Random random)
    {
        return random.nextInt(RuinConfig.hardLootChance) == 0 ? hardLoot : (random.nextInt(RuinConfig.midLootChance) == 0 ? midLoot : easyLoot);
    }

    private void ruinBuilding(int nodes, int maxRuinRad, int minRuinRad, Random rand)
    {
        int bottom = (int) ((float) this.blocks.length / 3.0F);
        int rad = rand.nextInt(maxRuinRad - minRuinRad) + minRuinRad;

        for (int i = 0; i < nodes; ++i)
        {
            int node;
            for (node = rand.nextInt(this.blocks.length - bottom) + bottom - 1; this.blocks[node] == 0 || this.blocks[node] == 2; node = rand.nextInt(this.blocks.length - bottom) + bottom - 1)
            {
                ;
            }

            int y = node / (this.width * this.length);
            int mod = node - this.width * this.length * y;
            int z = mod / this.width;
            int x = mod - z * this.width;
            Sphere hole = new Sphere(new Vector(x, y, z), rad);
            this.blocks = hole.makeSphereOf(this.blocks, this.width, this.length, this.height, (byte) 0);
        }

    }

   /*private static Tag getChildTag(Map items, String key, Class expected) {
      Tag tag = (Tag)items.get(key);
      return tag;
   }*/
}
