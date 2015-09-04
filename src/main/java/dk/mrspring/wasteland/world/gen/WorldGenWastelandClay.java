//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.BlockRadFluid;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenWastelandClay extends WorldGenerator
{
    private Block field_150546_a;
    private int numberOfBlocks;
    private static final String __OBFID = "CL_00000405";

    public WorldGenWastelandClay(int p_i2011_1_)
    {
        this.numberOfBlocks = p_i2011_1_;
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        boolean generateClay = false;
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        RuinGenHelper.setWorld(world);

        for (int i = 0; i < 10; ++i)
        {
            if (RuinGenHelper.getBlock(x, y - i, z) instanceof BlockRadFluid && !(RuinGenHelper.getBlock(x, y - i - 1, z) instanceof BlockRadFluid))
            {
                generateClay = true;
                y -= i;
            }
        }

        if (!generateClay)
        {
            return false;
        } else
        {
            Block placing = rand.nextInt(ModConfig.clayRarity) == 0 ? Blocks.clay : Blocks.sand;
            int l = rand.nextInt(this.numberOfBlocks - 2) + 2;
            byte b0 = 1;

            for (int i1 = x - l; i1 <= x + l; ++i1)
            {
                for (int j1 = z - l; j1 <= z + l; ++j1)
                {
                    int k1 = i1 - x;
                    int l1 = j1 - z;
                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = y - b0; i2 <= y + b0; ++i2)
                        {
                            Block block = RuinGenHelper.getBlock(i1, i2, j1);
                            Block topBlock = RuinGenHelper.getBlock(i1, i2 + 1, j1);
                            if (block != Blocks.air && !(block instanceof BlockRadFluid) && topBlock != Blocks.air)
                            {
                                RuinGenHelper.setBlock(i1, i2, j1, placing, 0);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
