//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenWastelandLake extends WorldGenerator
{
    private Block field_150556_a;

    public WorldGenWastelandLake(Block p_i45455_1_)
    {
        this.field_150556_a = p_i45455_1_;
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        x -= 8;
        RuinGenHelper.setWorld(world);

        for (z -= 8; y > 5 && RuinGenHelper.isAirBlock(x, y, z); --y)
        {
            ;
        }

        if (y <= 4)
        {
            return false;
        } else
        {
            y -= 4;
            boolean[] aboolean = new boolean[2048];
            int l = rand.nextInt(4) + 4;

            int i1;
            for (i1 = 0; i1 < l; ++i1)
            {
                double j1 = rand.nextDouble() * 6.0D + 3.0D;
                double flag = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - j1 - 2.0D) + 1.0D + j1 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - flag - 4.0D) + 2.0D + flag / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int k1 = 1; k1 < 15; ++k1)
                {
                    for (int l1 = 1; l1 < 15; ++l1)
                    {
                        for (int i2 = 1; i2 < 7; ++i2)
                        {
                            double d6 = ((double) k1 - d3) / (j1 / 2.0D);
                            double d7 = ((double) i2 - d4) / (flag / 2.0D);
                            double d8 = ((double) l1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
                            if (d9 < 1.0D)
                            {
                                aboolean[(k1 * 16 + l1) * 8 + i2] = true;
                            }
                        }
                    }
                }
            }

            int j2;
            int var32;
            boolean var33;
            for (i1 = 0; i1 < 16; ++i1)
            {
                for (j2 = 0; j2 < 16; ++j2)
                {
                    for (var32 = 0; var32 < 8; ++var32)
                    {
                        var33 = !aboolean[(i1 * 16 + j2) * 8 + var32] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + var32] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + var32] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + var32] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + var32] || var32 < 7 && aboolean[(i1 * 16 + j2) * 8 + var32 + 1] || var32 > 0 && aboolean[(i1 * 16 + j2) * 8 + (var32 - 1)]);
                        if (var33)
                        {
                            Material b0 = RuinGenHelper.getBlock(x + i1, y + var32, z + j2).getMaterial();
                            if (var32 >= 4 && b0.isLiquid())
                            {
                                return false;
                            }

                            if (var32 < 4 && !b0.isSolid() && RuinGenHelper.getBlock(x + i1, y + var32, z + j2) != this.field_150556_a)
                            {
                                return false;
                            }
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1)
            {
                for (j2 = 0; j2 < 16; ++j2)
                {
                    for (var32 = 0; var32 < 8; ++var32)
                    {
                        if (aboolean[(i1 * 16 + j2) * 8 + var32])
                        {
                            RuinGenHelper.setBlock(x + i1, y + var32, z + j2, var32 >= 4 ? Blocks.air : this.field_150556_a, 0);
                        }
                    }
                }
            }

            for (i1 = 0; i1 < 16; ++i1)
            {
                for (j2 = 0; j2 < 16; ++j2)
                {
                    for (var32 = 4; var32 < 8; ++var32)
                    {
                        if (aboolean[(i1 * 16 + j2) * 8 + var32] && RuinGenHelper.getBlock(x + i1, y + var32 - 1, z + j2) == ModConfig.getSurfaceBlock() && RuinGenHelper.getSavedLightValue(EnumSkyBlock.SKY, x + i1, y + var32, z + j2) > 0)
                        {
                            RuinGenHelper.setBlock(x + i1, y + var32 - 1, z + j2, ModConfig.getSurfaceBlock(), 0);
                        }
                    }
                }
            }

            if (this.field_150556_a.getMaterial() == Material.lava)
            {
                for (i1 = 0; i1 < 16; ++i1)
                {
                    for (j2 = 0; j2 < 16; ++j2)
                    {
                        for (var32 = 0; var32 < 8; ++var32)
                        {
                            var33 = !aboolean[(i1 * 16 + j2) * 8 + var32] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + var32] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + var32] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + var32] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + var32] || var32 < 7 && aboolean[(i1 * 16 + j2) * 8 + var32 + 1] || var32 > 0 && aboolean[(i1 * 16 + j2) * 8 + (var32 - 1)]);
                            if (var33 && (var32 < 4 || rand.nextInt(2) != 0) && RuinGenHelper.getBlock(x + i1, y + var32, z + j2).getMaterial().isSolid())
                            {
                                RuinGenHelper.setBlock(x + i1, y + var32, z + j2, Blocks.stone, 0);
                            }
                        }
                    }
                }
            }

            if (this.field_150556_a.getMaterial() == Material.water)
            {
                for (i1 = 0; i1 < 16; ++i1)
                {
                    for (j2 = 0; j2 < 16; ++j2)
                    {
                        byte var34 = 4;
                        if (RuinGenHelper.isBlockFreezable(x + i1, y + var34, z + j2))
                        {
                            RuinGenHelper.setBlock(x + i1, y + var34, z + j2, Blocks.ice, 0);
                        }
                    }
                }
            }

            return true;
        }
    }
}
