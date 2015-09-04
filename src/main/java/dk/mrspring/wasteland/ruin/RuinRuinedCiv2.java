//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class RuinRuinedCiv2 extends Ruin implements IWorldGenerator
{
    public RuinRuinedCiv2(String par1Name)
    {
        super(par1Name);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        RuinGenHelper.setWorld(world);
        int j1 = 0;
        int k1 = 0;
        Material material = RuinGenHelper.getBlock(i, j, k).getMaterial();
        Material material1 = RuinGenHelper.getBlock(i, j - 1, k).getMaterial();
        Material material2 = RuinGenHelper.getBlock(i + 6, j, k).getMaterial();
        Material material3 = RuinGenHelper.getBlock(i, j, k + 6).getMaterial();
        Material material4 = RuinGenHelper.getBlock(i + 6, j, k + 6).getMaterial();
        Material material5 = RuinGenHelper.getBlock(i, j + 1, k).getMaterial();
        Material material6 = RuinGenHelper.getBlock(i + 6, j + 1, k).getMaterial();
        Material material7 = RuinGenHelper.getBlock(i, j + 1, k + 6).getMaterial();
        Material material8 = RuinGenHelper.getBlock(i + 6, j + 1, k + 6).getMaterial();
        if ((material.isSolid() || material1.isSolid()) && material2.isSolid() && material3.isSolid() && material4.isSolid() && !material5.isSolid() && !material6.isSolid() && !material7.isSolid() && !material8.isSolid())
        {
            boolean flag1 = true;
            if (flag1)
            {
                Block l1 = Blocks.air;
                int i2 = random.nextInt(3);
                if (i2 == 0)
                {
                    l1 = Blocks.cobblestone;
                }

                if (i2 == 1)
                {
                    l1 = Blocks.mossy_cobblestone;
                }

                if (i2 == 2)
                {
                    l1 = Blocks.cobblestone;
                }

                if (i2 == 3)
                {
                    l1 = Blocks.mossy_cobblestone;
                }

                int j2 = random.nextInt(2);
                Block k2 = Blocks.air;
                if (j2 == 0)
                {
                    k2 = Blocks.cobblestone;
                } else if (j2 == 1)
                {
                    k2 = Blocks.mossy_cobblestone;
                } else if (j2 == 2)
                {
                    k2 = Blocks.planks;
                } else if (j2 == 3)
                {
                    k2 = Blocks.planks;
                }

                boolean flag2 = false;
                if (!flag2)
                {
                    int i3;
                    int k3;
                    for (i3 = 1; i3 <= 3; ++i3)
                    {
                        for (k3 = 0; k3 < 80; ++k3)
                        {
                            RuinGenHelper.setBlock(i + k1, j + i3, k + j1, Blocks.air);
                            ++k1;
                            if (k1 == 8)
                            {
                                ++j1;
                                k1 = 0;
                            }
                        }
                    }

                    i3 = 0;
                    k3 = 0;

                    int i4;
                    for (i4 = 0; i4 < 80; ++i4)
                    {
                        RuinGenHelper.setBlock(i + k3, j, k + i3, k2);
                        ++k3;
                        if (k3 == 8)
                        {
                            ++i3;
                            k3 = 0;
                        }
                    }

                    i4 = random.nextInt(3);
                    int j4 = 0;
                    int k4 = 0;
                    int l4 = random.nextInt(2);
                    int k6;
                    if (i4 > 0 || l4 == 0)
                    {
                        for (k6 = 0; k6 < 99; ++k6)
                        {
                            RuinGenHelper.setBlock(i + j4, j + 4, k + k4, l1);
                            ++j4;
                            if (j4 == 9)
                            {
                                ++k4;
                                j4 = 0;
                            }
                        }
                    }

                    int l10;
                    for (k6 = 0; k6 < 4; ++k6)
                    {
                        for (l10 = 0; l10 < 8; ++l10)
                        {
                            RuinGenHelper.setBlock(i + l10, j + k6, k, l1);
                        }

                        for (l10 = 0; l10 < 9; ++l10)
                        {
                            RuinGenHelper.setBlock(i + l10, j + k6, k + 10, l1);
                        }

                        for (l10 = 0; l10 < 10; ++l10)
                        {
                            RuinGenHelper.setBlock(i, j + k6, k + l10, l1);
                        }

                        for (l10 = 0; l10 < 10; ++l10)
                        {
                            RuinGenHelper.setBlock(i + 8, j + k6, k + l10, l1);
                        }
                    }

                    if (i4 > 0)
                    {
                        for (k6 = 0; k6 < 4; ++k6)
                        {
                            for (l10 = 0; l10 < 8; ++l10)
                            {
                                RuinGenHelper.setBlock(i + l10, j + k6 + 4, k, l1);
                            }

                            for (l10 = 0; l10 < 9; ++l10)
                            {
                                RuinGenHelper.setBlock(i + l10, j + k6 + 4, k + 10, l1);
                            }

                            for (l10 = 0; l10 < 10; ++l10)
                            {
                                RuinGenHelper.setBlock(i, j + k6 + 4, k + l10, l1);
                            }

                            for (l10 = 0; l10 < 10; ++l10)
                            {
                                RuinGenHelper.setBlock(i + 8, j + k6 + 4, k + l10, l1);
                            }
                        }
                    }

                    if (i4 > 1)
                    {
                        for (k6 = 0; k6 < 4; ++k6)
                        {
                            for (l10 = 0; l10 < 8; ++l10)
                            {
                                RuinGenHelper.setBlock(i + l10, j + k6 + 8, k, l1);
                            }

                            for (l10 = 0; l10 < 9; ++l10)
                            {
                                RuinGenHelper.setBlock(i + l10, j + k6 + 8, k + 10, l1);
                            }

                            for (l10 = 0; l10 < 10; ++l10)
                            {
                                RuinGenHelper.setBlock(i, j + k6 + 8, k + l10, l1);
                            }

                            for (l10 = 0; l10 < 10; ++l10)
                            {
                                RuinGenHelper.setBlock(i + 8, j + k6 + 8, k + l10, l1);
                            }
                        }
                    }

                    j4 = 0;
                    k4 = 0;
                    if (i4 > 0)
                    {
                        for (k6 = 0; k6 < 99; ++k6)
                        {
                            RuinGenHelper.setBlock(i + j4, j + 8, k + k4, l1);
                            ++j4;
                            if (j4 == 9)
                            {
                                ++k4;
                                j4 = 0;
                            }
                        }
                    }

                    j4 = 0;
                    k4 = 0;
                    if (i4 > 1)
                    {
                        for (k6 = 0; k6 < 99; ++k6)
                        {
                            RuinGenHelper.setBlock(i + j4, j + 12, k + k4, l1);
                            ++j4;
                            if (j4 == 9)
                            {
                                ++k4;
                                j4 = 0;
                            }
                        }
                    }

                    k6 = random.nextInt(2);
                    int i12;
                    int flag3;
                    if (k6 == 0)
                    {
                        if (i4 >= 1)
                        {
                            l10 = 0;
                            i12 = 0;

                            for (flag3 = 0; flag3 < 63; ++flag3)
                            {
                                if (l10 == 0 || l10 == 6)
                                {
                                    RuinGenHelper.setBlock(i + l10 + 1, j + 5, k + i12 + 1, Blocks.bookshelf);
                                }

                                if (i12 == 0 || i12 == 8)
                                {
                                    RuinGenHelper.setBlock(i + l10 + 1, j + 5, k + i12 + 1, Blocks.bookshelf);
                                }

                                ++i12;
                                if (i12 == 9)
                                {
                                    ++l10;
                                    i12 = 0;
                                }
                            }
                        }

                        if (i4 > 2)
                        {
                            l10 = 0;
                            i12 = 0;

                            for (flag3 = 0; flag3 < 63; ++flag3)
                            {
                                if (l10 == 0 || l10 == 6)
                                {
                                    RuinGenHelper.setBlock(i + l10 + 1, j + 9, k + i12 + 1, Blocks.furnace);
                                }

                                if (i12 == 0 || i12 == 8)
                                {
                                    RuinGenHelper.setBlock(i + l10 + 1, j + 9, k + i12 + 1, Blocks.furnace);
                                }

                                ++i12;
                                if (i12 == 9)
                                {
                                    ++l10;
                                    i12 = 0;
                                }
                            }
                        }
                    }

                    int l13;
                    if (k6 == 1)
                    {
                        if (i4 >= 1)
                        {
                            for (l10 = 0; l10 < 3; ++l10)
                            {
                                i12 = 0;
                                flag3 = 0;

                                for (l13 = 0; l13 < 63; ++l13)
                                {
                                    if (i12 == 0 || i12 == 6)
                                    {
                                        RuinGenHelper.setBlock(i + i12 + 1, j + 5 + l10, k + flag3 + 1, Blocks.bookshelf);
                                    }

                                    if (flag3 == 0 || flag3 == 8)
                                    {
                                        RuinGenHelper.setBlock(i + i12 + 1, j + 5 + l10, k + flag3 + 1, Blocks.bookshelf);
                                    }

                                    ++flag3;
                                    if (flag3 == 9)
                                    {
                                        ++i12;
                                        flag3 = 0;
                                    }
                                }

                                RuinGenHelper.setBlock(i + 3, j + 5, k + 3, Blocks.planks);
                                RuinGenHelper.setBlock(i + 4, j + 5, k + 3, Blocks.planks);
                                RuinGenHelper.setBlock(i + 5, j + 5, k + 3, Blocks.planks);
                                RuinGenHelper.setBlock(i + 3, j + 5, k + 4, Blocks.planks);
                                RuinGenHelper.setBlock(i + 4, j + 5, k + 4, Blocks.planks);
                                RuinGenHelper.setBlock(i + 5, j + 5, k + 4, Blocks.planks);
                                RuinGenHelper.setBlock(i + 3, j + 5, k + 6, Blocks.planks);
                                RuinGenHelper.setBlock(i + 4, j + 5, k + 6, Blocks.planks);
                                RuinGenHelper.setBlock(i + 5, j + 5, k + 6, Blocks.planks);
                                RuinGenHelper.setBlock(i + 3, j + 5, k + 7, Blocks.planks);
                                RuinGenHelper.setBlock(i + 4, j + 5, k + 7, Blocks.planks);
                                RuinGenHelper.setBlock(i + 5, j + 5, k + 7, Blocks.planks);
                            }
                        }

                        if (i4 >= 2)
                        {
                            for (l10 = 0; l10 < 3; ++l10)
                            {
                                i12 = 0;
                                flag3 = 0;

                                for (l13 = 0; l13 < 63; ++l13)
                                {
                                    if (i12 == 0 || i12 == 6)
                                    {
                                        RuinGenHelper.setBlock(i + i12 + 1, j + 9 + l10, k + flag3 + 1, Blocks.bookshelf);
                                    }

                                    if (flag3 == 0 || flag3 == 8)
                                    {
                                        RuinGenHelper.setBlock(i + i12 + 1, j + 9 + l10, k + flag3 + 1, Blocks.bookshelf);
                                    }

                                    ++flag3;
                                    if (flag3 == 9)
                                    {
                                        ++i12;
                                        flag3 = 0;
                                    }
                                }
                            }

                            RuinGenHelper.setBlock(i + 3, j + 9, k + 3, Blocks.planks);
                            RuinGenHelper.setBlock(i + 4, j + 9, k + 3, Blocks.planks);
                            RuinGenHelper.setBlock(i + 5, j + 9, k + 3, Blocks.planks);
                            RuinGenHelper.setBlock(i + 3, j + 9, k + 4, Blocks.planks);
                            RuinGenHelper.setBlock(i + 4, j + 9, k + 4, Blocks.planks);
                            RuinGenHelper.setBlock(i + 5, j + 9, k + 4, Blocks.planks);
                            RuinGenHelper.setBlock(i + 3, j + 9, k + 6, Blocks.planks);
                            RuinGenHelper.setBlock(i + 4, j + 9, k + 6, Blocks.planks);
                            RuinGenHelper.setBlock(i + 5, j + 9, k + 6, Blocks.planks);
                            RuinGenHelper.setBlock(i + 3, j + 9, k + 7, Blocks.planks);
                            RuinGenHelper.setBlock(i + 4, j + 9, k + 7, Blocks.planks);
                            RuinGenHelper.setBlock(i + 5, j + 9, k + 7, Blocks.planks);
                        }
                    }

                    if (i4 > 1)
                    {
                        RuinGenHelper.setBlock(i + 4, j + 9, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 4, j + 10, k + 1, Blocks.torch);
                        RuinGenHelper.setBlock(i + 1, j + 10, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 7, j + 10, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 4, j + 10, k + 9, Blocks.torch);
                    }

                    if (i4 > 0)
                    {
                        RuinGenHelper.setBlock(i + 4, j + 5, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 4, j + 6, k + 1, Blocks.torch);
                        RuinGenHelper.setBlock(i + 1, j + 6, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 7, j + 6, k + 5, Blocks.torch);
                        RuinGenHelper.setBlock(i + 4, j + 6, k + 9, Blocks.torch);
                    }

                    RuinGenHelper.setBlock(i + 4, j + 1, k, Blocks.air);
                    RuinGenHelper.setBlock(i + 4, j + 2, k, Blocks.air);
                    RuinGenHelper.setBlock(i + 4, j + 1, k + 5, Blocks.torch);
                    RuinGenHelper.setBlock(i + 3, j + 2, k + 1, Blocks.torch);
                    RuinGenHelper.setBlock(i + 5, j + 2, k + 1, Blocks.torch);
                    RuinGenHelper.setBlock(i + 1, j + 2, k + 5, Blocks.torch);
                    RuinGenHelper.setBlock(i + 7, j + 2, k + 5, Blocks.torch);
                    RuinGenHelper.setBlock(i + 4, j + 2, k + 9, Blocks.torch);
                    l10 = random.nextInt(24) + 16;

                    for (i12 = 0; i12 < l10; ++i12)
                    {
                        boolean var40 = false;
                        if (!var40)
                        {
                            l13 = random.nextInt(9);
                            int i14 = random.nextInt(16);
                            int j14 = random.nextInt(11);
                            RuinGenHelper.setBlock(i + l13, j + i14, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 1, j + i14, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 1, j + i14, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14, k + j14 + 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14, k + j14 - 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 1, j + i14 - 1, k + j14 - 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 1, j + i14 + 1, k + j14 + 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 1, j + i14, k + j14 + 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 1, j + i14, k + j14 - 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 2, j + i14, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 2, j + i14, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14, k + j14 + 2, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14, k + j14 - 2, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 - 2, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 - 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 1, j + i14 - 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 1, j + i14 - 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 - 1, k + j14 + 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 - 1, k + j14 - 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 + 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 + 2, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 - 1, j + i14 + 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13 + 1, j + i14 + 1, k + j14, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 + 1, k + j14 + 1, Blocks.air);
                            RuinGenHelper.setBlock(i + l13, j + i14 + 1, k + j14 - 1, Blocks.air);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }
}
