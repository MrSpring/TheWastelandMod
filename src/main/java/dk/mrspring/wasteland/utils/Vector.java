package dk.mrspring.wasteland.utils;

import net.minecraft.util.BlockPos;

import java.util.Random;

public class Vector {

   public int X;
   public int Y;
   public int Z;


   private Vector(int x, int y, int z) {
      this.X = x;
      this.Y = y;
      this.Z = z;
   }

   private Vector add(Vector v) {
      this.X += v.X;
      this.Y += v.Y;
      this.Z += v.Z;
      return this;
   }

   private Vector minus(Vector v) {
      this.X -= v.X;
      this.Y -= v.Y;
      this.Z -= v.Z;
      return this;
   }

   public static boolean equalsXZ(BlockPos v1, BlockPos v2) {
      return v1.getX() == v2.getX() && v1.getZ() == v2.getZ();
   }

   private boolean equals(Vector v) {
      return this.X == v.X && this.Z == v.Z && this.Y == v.Y;
   }

   private String toCustomString() {
      return "X:" + String.valueOf(this.X) + " Y:" + this.Y + " Z:" + this.Z;
   }

   public static double length(BlockPos v) {
      return Math.floor(Math.sqrt((double)(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ())));
   }

   public static double VtoVlength(BlockPos v1, BlockPos v2) {
      return length(new BlockPos(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ()));
   }

   public static double VtoVlengthXZ(BlockPos v1, BlockPos v2) {
      return length(new BlockPos(v1.getX() - v2.getX(), 0, v1.getZ() - v2.getZ()));
   }

   public static Vector randomVector3D(Random rand, int maxSize, int minSize) {
      double a1 = (double)rand.nextInt(360) * 0.017453292519943295D;
      double a2 = (double)rand.nextInt(180) * 0.017453292519943295D;
      double r = (double)(rand.nextInt(maxSize - minSize + 1) + minSize);
      int x = (int)(Math.cos(a1) * Math.sin(a2) * r);
      int z = (int)(Math.sin(a1) * Math.sin(a2) * r);
      int y = (int)(Math.cos(a2) * r);
      return new Vector(x, y, z);
   }

   public static BlockPos randomVector2D(Random rand, int maxSize, int minSize) {
      double a1 = (double)rand.nextInt(360) * 0.017453292519943295D;
      double r = (double)(rand.nextInt(maxSize - minSize + 1) + minSize);
      int x = (int)(Math.cos(a1) * r);
      int z = (int)(Math.sin(a1) * r);
      return new BlockPos(x, 0, z);
   }

   public Vector copy() {
      return new Vector(this.X, this.Y, this.Z);
   }
}
