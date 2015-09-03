//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.utils.Rectangle;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.Random;

public class RuinedVillage
{
    private Building[] structures;
    private Building center;
    private Layout lay;
    private int locX;
    private int locZ;
    private int dimension;

    public RuinedVillage(World world, int posX, int posZ, int dim, int size, Random rand)
    {
        rand.nextInt(100);
        System.out.println("Size: " + String.valueOf(size));
        this.locX = posX;
        this.locZ = posZ;
        this.dimension = dim;
        boolean numStructures = true;
        boolean centerStruct = false;
        boolean smallStruct = true;
        boolean midStruct = false;
        int largeStruct = 0;
        int var14;
        int var15;
        int var16;
        int var17;
        if (size == 0)
        {
            var14 = rand.nextInt(4) + 3;
            var15 = rand.nextInt(2);
            var16 = 3;
            var17 = var14 - var16;
        } else if (size == 1)
        {
            var14 = rand.nextInt(5) + 5;
            var15 = rand.nextInt(2) + 1;
            var16 = 2;
            largeStruct = rand.nextInt(2) + 2;
            var17 = var14 - var16 - largeStruct;
        } else
        {
            var14 = rand.nextInt(6) + 6;
            var15 = 3;
            largeStruct = rand.nextInt(3) + 3;
            var17 = rand.nextInt(3) + 2;
            if (var17 + largeStruct > var14)
            {
                var17 = var14 - largeStruct;
                var16 = 0;
            } else
            {
                var16 = var14 - largeStruct - var17;
            }
        }

        System.out.println("Buildings: " + String.valueOf(var14) + " - S:" + var16 + " M:" + var17 + " L:" + largeStruct + " - C:" + var15);
        if (var15 == 0)
        {
            this.center = null;
        } else if (var15 == 1)
        {
            this.center = Building.create(3);
        } else if (var15 == 2)
        {
            this.center = Building.create(14);
        } else
        {
            this.center = Building.create(0);
        }

        this.structures = new Building[var14];

        for (int i = 0; i < var14; ++i)
        {
            byte build;
            if (i < var16)
            {
                build = 0;
            } else if (i >= var16 && i < var17 + var16)
            {
                build = 1;
            } else
            {
                build = 2;
            }

            for (this.structures[i] = Building.create(this.pickStruct(rand, build)); this.checkDuplicates(this.center, this.structures, i); this.structures[i] = Building.create(this.pickStruct(rand, build)))
            {
                ;
            }
        }

        this.lay = new Layout(world, rand, this.center, this.structures, posX, posZ, dim, size);
    }

    private boolean checkDuplicates(Building c, Building[] b, int num)
    {
        if (c != null && b[num].name.equals(c.name))
        {
            return true;
        } else
        {
            for (int i = 0; i < num; ++i)
            {
                if (b[num].name.equals(b[i].name) && !b[num].duplicate)
                {
                    return true;
                }
            }

            return false;
        }
    }

    private int pickStruct(Random rand, int i)
    {
        switch (i)
        {
            case 0:
                switch (rand.nextInt(5))
                {
                    case 0:
                        return 12;
                    case 1:
                        return 13;
                    case 2:
                        return 14;
                    case 3:
                        return 1;
                    case 4:
                        return 3;
                    default:
                        return 0;
                }
            case 1:
                switch (rand.nextInt(3))
                {
                    case 0:
                        return 9;
                    case 1:
                        return 10;
                    case 2:
                        return 11;
                    default:
                        return 0;
                }
            case 2:
                switch (rand.nextInt(6))
                {
                    case 0:
                        return 2;
                    case 1:
                        return 4;
                    case 2:
                        return 5;
                    case 3:
                        return 6;
                    case 4:
                        return 7;
                    case 5:
                        return 8;
                }
        }

        return 0;
    }

    public void generate(World world, Random random)
    {
        if (this.center != null)
        {
            this.center.generate(world, random, this.lay.cPos.position, this.lay.cPos.rotation);
        }

        for (int i = 0; i < this.lay.bPos.length; ++i)
        {
            if (this.lay.bPos[i] != null && this.structures[i] != null)
            {
                this.structures[i].generate(world, random, this.lay.bPos[i].position, this.lay.bPos[i].rotation);
            }
        }

    }

    private void generateRect(World world, Rectangle r, int h)
    {
        for (int i = 0; i < r.length; ++i)
        {
            for (int j = 0; j < r.width; ++j)
            {
                if (i == 0 || j == 0 || i == r.length - 1 || j == r.width - 1)
                {
                    world.setBlock(r.position.X + j, r.position.Y + h, r.position.Z + i, Blocks.sponge);
                }
            }
        }

    }
}
