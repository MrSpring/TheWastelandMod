package dk.mrspring.wasteland.city;

import net.minecraft.util.BlockPos;

public class MultiVector {

   MultiVector[] connectedChunk = new MultiVector[]{null, null, null, null};
   BlockPos pos;

   public MultiVector(int x, int y, int z) {
      this.pos = new BlockPos(x, y, z);
   }

   public MultiVector(BlockPos pos){
      this.pos = pos;
   }

   public boolean equalsXZ(MultiVector v){
      return equalsXZ(v.pos);
   }

   public boolean equalsXZ(BlockPos pos){
      return pos.getX() == this.pos.getX() && pos.getZ() == this.pos.getZ();
   }

   public MultiVector copy() {
      return new MultiVector(new BlockPos(pos));
   }
}
