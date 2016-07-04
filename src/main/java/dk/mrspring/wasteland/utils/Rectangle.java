package dk.mrspring.wasteland.utils;

import net.minecraft.util.BlockPos;

public class Rectangle
{

    public BlockPos position;
    public int width;
    public int length;
    public int rotation;


    public Rectangle(BlockPos pos, int w, int l)
    {
        this.position = pos;
        this.width = w;
        this.length = l;
    }

    public static boolean checkConflict(Rectangle rectA, Rectangle rectB, int minDist)
    {
        return rectA.position.getX() <= rectB.position.getX() + rectB.width + minDist &&
                rectA.position.getX() + rectA.width >= rectB.position.getX() - minDist &&
                rectA.position.getZ() + rectA.length >= rectB.position.getZ() - minDist &&
                rectA.position.getZ() <= rectB.position.getZ() + rectB.length + minDist;
    }

    public Rectangle rotate(int dir)
    {
        this.rotation = dir;
        if (dir == 1 || dir == 3)
        {
            int w = this.width;
            this.width = this.length;
            this.length = w;
        }

        return this;
    }
}
