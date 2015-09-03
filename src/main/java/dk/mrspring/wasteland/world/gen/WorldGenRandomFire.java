//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.config.ModConfig;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenRandomFire extends WorldGenerator
{
    public WorldGenRandomFire()
    {
        super(true);
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock()))
        {
            world.setBlock(x, y, z, Blocks.netherrack);
            world.setBlock(x, y + 1, z, Blocks.fire);
            return true;
        } else
        {
            return false;
        }
    }
}
