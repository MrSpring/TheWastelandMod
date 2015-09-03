//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import cpw.mods.fml.common.IWorldGenerator;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import java.util.Random;

public class RuinRuinedCiv1 extends Ruin implements IWorldGenerator
{
    private RuinGenHelper genHelper = new RuinGenHelper();

    public RuinRuinedCiv1(String par1Name)
    {
        super(par1Name);
    }

    public boolean generate(World world, Random random, int x, int y, int z)
    {
        RuinGenHelper var10000 = this.genHelper;
        RuinGenHelper.setWorld(world);
        boolean byte0 = true;
        int l = random.nextInt(2) + 2;
        int i1 = random.nextInt(2) + 2;
        boolean flag = false;
        int j1 = 0;
        int k1 = 0;
        Rectangle pos = new Rectangle(new Vector(x, y, z), 6, 6);
        int[] lay = Layout.getLevels(world, pos);
        if (Layout.checkLevel(lay, 0))
        {
            boolean flag1 = true;
            if (flag1)
            {
                int i2 = random.nextInt(3);
                Block wallBlock;
                if (i2 == 0)
                {
                    wallBlock = Blocks.cobblestone;
                } else if (i2 == 1)
                {
                    wallBlock = Blocks.mossy_cobblestone;
                } else if (i2 == 2)
                {
                    wallBlock = Blocks.planks;
                } else
                {
                    wallBlock = Blocks.mossy_cobblestone;
                }

                int j2 = random.nextInt(2);
                Block floorBlock;
                if (j2 == 0)
                {
                    floorBlock = Blocks.cobblestone;
                } else if (j2 == 1)
                {
                    floorBlock = Blocks.mossy_cobblestone;
                } else if (j2 == 2)
                {
                    floorBlock = Blocks.planks;
                } else
                {
                    floorBlock = Blocks.planks;
                }

                boolean flag2 = false;
                if (!flag2)
                {
                    int i3;
                    int k3;
                    for (i3 = 1; i3 <= 3; ++i3)
                    {
                        for (k3 = 0; k3 < 49; ++k3)
                        {
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(x + k1, y + i3, z + j1, Blocks.air);
                            ++k1;
                            if (k1 == 7)
                            {
                                ++j1;
                                k1 = 0;
                            }
                        }
                    }

                    i3 = 0;
                    k3 = 0;

                    int i4;
                    for (i4 = 0; i4 < 49; ++i4)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + k3, y, z + i3, floorBlock);
                        ++k3;
                        if (k3 == 7)
                        {
                            ++i3;
                            k3 = 0;
                        }
                    }

                    i4 = 0;
                    int j4 = 0;
                    int k4 = random.nextInt(2);
                    int k5;
                    int k6;
                    int j7;
                    int j8;
                    if (k4 == 0)
                    {
                        for (k5 = 1; k5 < 4; ++k5)
                        {
                            for (k6 = 0; k6 < 25; ++k6)
                            {
                                var10000 = this.genHelper;
                                RuinGenHelper.setBlock(x + i4 + 1, y - k5, z + j4 + 1, Blocks.air);
                                ++i4;
                                if (i4 == 5)
                                {
                                    ++j4;
                                    i4 = 0;
                                }
                            }

                            j4 = 0;
                            i4 = 0;
                        }

                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 3, y - 3, z + 3, Blocks.torch);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2, y - 3, z + 3, Blocks.stone_pressure_plate);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2, y - 4, z + 3, Blocks.stone);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2, y - 5, z + 3, Blocks.tnt);
                        k5 = random.nextInt(2);
                        if (k5 == 0)
                        {
                            for (k6 = 0; k6 < 25; ++k6)
                            {
                                var10000 = this.genHelper;
                                RuinGenHelper.setBlock(x + i4 + 1, y - 3, z + j4 + 1, Blocks.water);
                                ++i4;
                                if (i4 == 5)
                                {
                                    ++j4;
                                    i4 = 0;
                                }
                            }
                        }

                        k6 = random.nextInt(26);
                        j7 = k6 / 5;
                        j8 = k6 % 5;
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + j7, y - 3, z + j8, Blocks.chest);
                        TileEntityChest i9 = (TileEntityChest) world.getTileEntity(x + j7, y - 3, z + j8);
                        LootStack k9 = this.setItems(random);
                        CustomItemStack.placeLoot(random, i9, CustomItemStack.getLootItems(random, k9.items, k9.minNum, k9.maxNum, k9.repeat));
                    }

                    boolean var41 = false;
                    boolean var40 = false;

                    int var42;
                    for (k5 = 0; k5 < 3; ++k5)
                    {
                        k6 = 0;
                        j7 = 0;

                        for (j8 = 0; j8 < 28; ++j8)
                        {
                            var42 = random.nextInt(k5 + 1);
                            Material var43;
                            if (k6 == 0)
                            {
                                var43 = world.getBlock(x, y + k5, z + j7).getMaterial();
                                if (var43.isSolid() && var42 == 0)
                                {
                                    if (k5 == 1)
                                    {
                                        if (random.nextInt(2) == 0)
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x, y + 1 + k5, z + j7, Blocks.glass);
                                        } else
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x, y + 1 + k5, z + j7, wallBlock);
                                        }
                                    } else
                                    {
                                        var10000 = this.genHelper;
                                        RuinGenHelper.setBlock(x, y + 1 + k5, z + j7, wallBlock);
                                    }
                                }

                                ++j7;
                            }

                            if (k6 == 1)
                            {
                                var43 = world.getBlock(x + 6, y + k5, z + j7).getMaterial();
                                if (var43.isSolid() && var42 == 0)
                                {
                                    if (k5 == 1)
                                    {
                                        if (random.nextInt(2) == 0)
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + 6, y + 1 + k5, z + j7, Blocks.glass);
                                        } else
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + 6, y + 1 + k5, z + j7, wallBlock);
                                        }
                                    } else
                                    {
                                        var10000 = this.genHelper;
                                        RuinGenHelper.setBlock(x + 6, y + 1 + k5, z + j7, wallBlock);
                                    }
                                }

                                ++j7;
                            }

                            if (k6 == 2)
                            {
                                var43 = world.getBlock(x + j7, y + k5, z).getMaterial();
                                if (var43.isSolid() && var42 == 0)
                                {
                                    if (k5 == 1)
                                    {
                                        if (random.nextInt(2) == 0)
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + j7, y + 1 + k5, z, Blocks.glass);
                                        } else
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + j7, y + 1 + k5, z, wallBlock);
                                        }
                                    } else
                                    {
                                        var10000 = this.genHelper;
                                        RuinGenHelper.setBlock(x + j7, y + 1 + k5, z, wallBlock);
                                    }
                                }

                                ++j7;
                            }

                            if (k6 == 3)
                            {
                                var43 = world.getBlock(x + j7, y + k5, z + 6).getMaterial();
                                if (var43.isSolid() && var42 == 0)
                                {
                                    if (k5 == 1)
                                    {
                                        if (random.nextInt(2) == 0)
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + j7, y + 1 + k5, z + 6, Blocks.glass);
                                        } else
                                        {
                                            var10000 = this.genHelper;
                                            RuinGenHelper.setBlock(x + j7, y + 1 + k5, z + 6, wallBlock);
                                        }
                                    } else
                                    {
                                        var10000 = this.genHelper;
                                        RuinGenHelper.setBlock(x + j7, y + 1 + k5, z + 6, wallBlock);
                                    }
                                }

                                ++j7;
                            }

                            if (j7 == 7)
                            {
                                ++k6;
                                j7 = 0;
                            }
                        }
                    }

                    k5 = random.nextInt(3);
                    k6 = random.nextInt(3);
                    j7 = random.nextInt(2);
                    if (k5 == 0)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x, y + 1, z + 2 + k6, Blocks.air);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x, y + 2, z + 2 + k6, Blocks.air);
                    }

                    if (k5 == 1)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 6, y + 1, z + 2 + k6, Blocks.air);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 6, y + 2, z + 2 + k6, Blocks.air);
                    }

                    if (k5 == 2)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2 + k6, y + 1, z, Blocks.air);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2 + k6, y + 2, z, Blocks.air);
                    }

                    if (k5 == 3)
                    {
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2 + k6, y + 1, z + 6, Blocks.air);
                        var10000 = this.genHelper;
                        RuinGenHelper.setBlock(x + 2 + k6, y + 2, z + 6, Blocks.air);
                    }

                    j8 = random.nextInt(2);
                    var42 = random.nextInt(5) + 1;
                    int var44 = 0;
                    int l9 = 0;
                    if (j8 == 0)
                    {
                        var44 = x + var42;
                        l9 = z + 1;
                    }

                    if (j8 == 1)
                    {
                        var44 = x + 1;
                        l9 = z + var42;
                    }

                    if (j8 == 2)
                    {
                        var44 = x + 1;
                        l9 = z + var42;
                    }

                    var10000 = this.genHelper;
                    RuinGenHelper.setBlock(var44, y + 1, l9, Blocks.chest);
                    TileEntityChest chest1 = (TileEntityChest) world.getTileEntity(var44, y + 1, l9);
                    LootStack loot = this.setItems(random);
                    CustomItemStack.placeLoot(random, chest1, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
                    int i10 = random.nextInt(2);
                    if (i10 == 0)
                    {
                        int j10 = 0;
                        int k10 = 0;

                        int k11;
                        for (k11 = 0; k11 < 12; ++k11)
                        {
                            if (k10 == 0)
                            {
                                var10000 = this.genHelper;
                                RuinGenHelper.setBlock(x + 2, y + 1, z - 1 - j10, Blocks.fence);
                            }

                            if (k10 == 1)
                            {
                                var10000 = this.genHelper;
                                RuinGenHelper.setBlock(x + 3 + j10, y + 1, z - 4, Blocks.fence);
                            }

                            if (k10 == 2)
                            {
                                var10000 = this.genHelper;
                                RuinGenHelper.setBlock(x + 6, y + 1, z - 1 - j10, Blocks.fence);
                            }

                            ++j10;
                            if (j10 == 4)
                            {
                                ++k10;
                                j10 = 0;
                            }
                        }

                        int chest2;
                        int loot1;
                        for (k11 = 0; k11 < 20; ++k11)
                        {
                            chest2 = k11 / 5;
                            loot1 = k11 % 5;
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(x + loot1 + 2, y, z - chest2 - 1, Blocks.dirt);
                        }

                        for (k11 = 0; k11 < 9; ++k11)
                        {
                            chest2 = k11 / 3;
                            loot1 = k11 % 3;
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(x + loot1 + 3, y + 1, z - chest2 - 1, Blocks.air);
                        }

                        k11 = random.nextInt(2);
                        if (k11 == 0)
                        {
                            var10000 = this.genHelper;
                            RuinGenHelper.setBlock(x + 3, y + 1, z - 3, Blocks.chest);
                            TileEntityChest var45 = (TileEntityChest) world.getTileEntity(x + 3, y + 1, z - 3);
                            LootStack var46 = this.setSeedItems();
                            CustomItemStack.placeLoot(random, var45, CustomItemStack.getLootItems(random, var46.items, var46.minNum, var46.maxNum, var46.repeat));
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    private ItemStack pickCheskLootItem(Random random, ItemStack[] itemStacks)
    {
        int i = random.nextInt(itemStacks.length);
        ItemStack itemStack = new ItemStack(itemStacks[i].getItem(), 1);
        return itemStack.getItem() == Items.stick ? new ItemStack(itemStack.getItem(), random.nextInt(8) + 4) : (itemStack.getItem() == Items.wheat ? new ItemStack(itemStack.getItem(), random.nextInt(3) + 1) : (itemStack.getItem() == Items.gunpowder ? new ItemStack(itemStack.getItem(), random.nextInt(4) + 1) : (itemStack.getItem() == Items.string ? new ItemStack(itemStack.getItem(), random.nextInt(1) + 3) : (itemStack.getItem() == Item.getItemFromBlock(Blocks.planks) && random.nextInt(100) == 0 ? new ItemStack(itemStack.getItem(), random.nextInt(4) + 8) : (itemStack.getItem() == Items.dye ? new ItemStack(itemStack.getItem(), random.nextInt(1) + 1, itemStack.getItemDamage()) : new ItemStack(itemStack.getItem(), 1))))));
    }

    private String pickMobSpawner(Random random)
    {
        int i = random.nextInt(4);
        return i == 0 ? "Skeleton" : (i == 1 ? "Zombie" : (i == 2 ? "Zombie" : (i == 3 ? "Spider" : "")));
    }
}
