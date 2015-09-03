//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import net.minecraft.util.MathHelper;

public class ModHelper
{
    public static final float pixel = 0.0625F;

    public ModHelper()
    {
    }

    public static String getCoordAsString(int x, int y, int z)
    {
        return String.valueOf(x + ", " + y + ", " + z);
    }

    public static String getCoordAsString(int x, int z)
    {
        return String.valueOf(x + ", " + z);
    }

    public static int getEntityRotationAsDirection(float rotationYaw)
    {
        return MathHelper.floor_double((double) (rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
    }

    public static class ResourceInfo
    {
        public static final String schematicDomain = "assets/wlm/schematics/";

        public ResourceInfo()
        {
        }
    }

    public static class ModInfo
    {
        public static final String modid = "WLM";
        public static final String name = "The Wasteland Mod";
        public static final String version = "1.4.3";
        public static final boolean isDev = "1.4.3".contains("DEV");

        public ModInfo()
        {
        }
    }

    public class Directions
    {
        public static final int SOUTH = 0;
        public static final int WEST = 1;
        public static final int NORTH = 2;
        public static final int EAST = 3;

        public Directions()
        {
        }
    }
}
