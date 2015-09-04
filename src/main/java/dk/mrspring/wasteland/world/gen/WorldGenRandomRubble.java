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
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenRandomRubble extends WorldGenerator
{
    public WorldGenRandomRubble()
    {
        super(true);
    }

    public boolean generate(World world, Random random, BlockPos pos)
    {
        RuinGenHelper.setWorld(world);
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        byte byte1 = 75;
        Material material = RuinGenHelper.getBlock(x, y + 1, z).getMaterial();
        Material material1 = RuinGenHelper.getBlock(x, y, z).getMaterial();
        Material material2 = RuinGenHelper.getBlock(x + 1, y, z).getMaterial();
        Material material3 = RuinGenHelper.getBlock(x - 1, y, z).getMaterial();
        Material material4 = RuinGenHelper.getBlock(x, y, z + 1).getMaterial();
        Material material5 = RuinGenHelper.getBlock(x, y, z - 1).getMaterial();
        if (RuinGenHelper.getBlock(x, y - 1, z).equals(ModConfig.getSurfaceBlock()) && !material.isSolid() && !material1.isSolid() && !material4.isSolid() && !material2.isSolid() && !material3.isSolid() && !material5.isSolid() && RuinGenHelper.getBlock(x, y, z) == Blocks.air && RuinGenHelper.getBlock(x, y + 1, z) == Blocks.air)
        {
            for (int j1 = 0; j1 < byte1; ++j1)
            {
                int k1 = x + random.nextInt(8);
                int l1 = y - 1 + random.nextInt(4);
                int i2 = z + random.nextInt(8);
                Material material6 = RuinGenHelper.getBlock(k1, l1 - 1, i2).getMaterial();
                if (RuinGenHelper.getBlock(k1, l1, i2) == Blocks.air && material6.isSolid())
                {
                    Block j2;
                    int k2 = random.nextInt(31);
                    if (k2 < 10)
                    {
                        j2 = Blocks.cobblestone;
                    } else if (k2 >= 10 && k2 < 20)
                    {
                        j2 = Blocks.mossy_cobblestone;
                    } else if (k2 >= 20 && k2 < 30)
                    {
                        j2 = Blocks.planks;
                    } else
                    {
                        j2 = Blocks.brick_block;
                    }

                    RuinGenHelper.setBlock(k1, l1, i2, j2);
                }
            }

            return true;
        } else
        {
            return false;
        }
    }
}
