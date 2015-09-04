package dk.mrspring.wasteland.world.gen;

import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenRandomFire extends WorldGenerator
{
    public WorldGenRandomFire()
    {
        super(true);
    }

    public boolean generate(World world, Random rand, BlockPos pos)
    {
        RuinGenHelper.setWorld(world);
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        if (RuinGenHelper.getBlock(x, y, z).equals(ModConfig.getSurfaceBlock()))
        {
            RuinGenHelper.setBlock(x, y, z, Blocks.netherrack);
            RuinGenHelper.setBlock(x, y + 1, z, Blocks.fire);
            return true;
        } else
        {
            return false;
        }
    }
}
