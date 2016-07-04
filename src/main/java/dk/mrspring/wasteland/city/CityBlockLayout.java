package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CityBlockLayout {

   List<CityBlock> block = new ArrayList<CityBlock>();


   public CityBlockLayout(List<MultiVector> chunkLocation, MultiVector center, Random random, World world) {
      this.connectChunks(chunkLocation);
      List<MultiVector> chunks = this.reduceHeightVariation(chunkLocation, center);
      this.createLayout(chunks, random, world);
      this.setBuildings(random);
   }

   private void setBuildings(Random random) {
      if(!this.block.isEmpty()) {
         for(int i = 0; i < this.block.size(); ++i) {
            CityBlock current = this.block.get(i);
            int r;
            if(current.area.width == 32 && current.area.length == 32) {
               r = random.nextInt(6);
               switch(r) {
               case 0:
                  current.buildingName = "LTA";
                  break;
               case 1:
                  current.buildingName = "LTB";
                  break;
               case 2:
                  current.buildingName = "LTC";
                  break;
               case 3:
                  current.buildingName = "LTD";
                  break;
               case 4:
                  current.buildingName = "LTE";
                  break;
               case 5:
                  current.buildingName = "LTF";
               }
            } else if(current.area.width == 16 && current.area.length == 16) {
               r = random.nextInt(5);
               switch(r) {
               case 0:
                  current.buildingName = "STA";
                  break;
               case 1:
                  current.buildingName = "STB";
                  break;
               case 2:
                  current.buildingName = "STC";
                  break;
               case 3:
                  current.buildingName = "SSA";
                  break;
               case 4:
                  current.buildingName = "SSB";
               }
            } else {
               r = random.nextInt(6);
               switch(r) {
               case 0:
                  current.buildingName = "MAA";
                  break;
               case 1:
                  current.buildingName = "MAB";
                  break;
               case 2:
                  current.buildingName = "MAC";
                  break;
               case 3:
                  current.buildingName = "MAD";
                  break;
               case 4:
                  current.buildingName = "MAE";
                  break;
               case 5:
                  current.buildingName = "MCA";
               }
            }
         }
      }

   }

   private void connectChunks(List chunks) {
      for(int i = 0; i < chunks.size(); ++i) {
         MultiVector current = (MultiVector)chunks.get(i);

         for(int j = 0; j < chunks.size(); ++j) {
            MultiVector compare = (MultiVector)chunks.get(j);
            if(Vector.equalsXZ(current.pos.add(new BlockPos(0, 0, 16)), compare.pos)) {
               current.connectedChunk[0] = compare;
            } else if(Vector.equalsXZ(current.pos.add(new BlockPos(0, 0, -16)), compare.pos)) {
               current.connectedChunk[2] = compare;
            } else if(Vector.equalsXZ(current.pos.add(new BlockPos(16, 0, 0)), compare.pos)) {
               current.connectedChunk[1] = compare;
            } else if(Vector.equalsXZ(current.pos.add(new BlockPos(-16, 0, 0)), compare.pos)) {
               current.connectedChunk[3] = compare;
            }
         }
      }

   }

   private List<MultiVector> reduceHeightVariation(List<MultiVector> chunkLocationIn, MultiVector center) {
      List<MultiVector> chunkLocation = this.orderChunks(chunkLocationIn, center);
      boolean p1 = false;
      int p2 = 0;

      for(int i = 1; i < chunkLocation.size(); ++i) {
         MultiVector v = chunkLocation.get(i);
         int var14;
         if(v.pos.getX() > center.pos.getX()) {
            var14 = v.connectedChunk[3] != null?v.connectedChunk[3].pos.getY():0;
            if(v.pos.getZ() > center.pos.getZ()) {
               p2 = v.connectedChunk[2] != null?v.connectedChunk[2].pos.getY():0;
            } else if(v.pos.getZ() < center.pos.getZ()) {
               p2 = v.connectedChunk[0] != null?v.connectedChunk[0].pos.getY():0;
            }
         } else if(v.pos.getX() < center.pos.getX()) {
            var14 = v.connectedChunk[1] != null?v.connectedChunk[1].pos.getY():0;
            if(v.pos.getZ() > center.pos.getZ()) {
               p2 = v.connectedChunk[2] != null?v.connectedChunk[2].pos.getY():0;
            } else if(v.pos.getZ() < center.pos.getZ()) {
               p2 = v.connectedChunk[0] != null?v.connectedChunk[0].pos.getY():0;
            }
         } else if(v.pos.getZ() > center.pos.getZ()) {
            var14 = v.connectedChunk[2] != null?v.connectedChunk[2].pos.getY():0;
         } else {
            var14 = v.connectedChunk[0] != null?v.connectedChunk[0].pos.getY():0;
         }

         if(p2 == 0 && var14 > 0) {
            if(v.pos.getY() > var14) {
               v.pos = new BlockPos(v.pos.getX(), var14 + 1, v.pos.getZ());//.getY() = var14 + 1;
            } else if(v.pos.getY() < var14) {
               v.pos = new BlockPos(v.pos.getX(), var14 - 1, v.pos.getZ());
            }
         } else if(p2 > 0 && var14 > 0) {
            if(Math.abs(p2 - var14) == 2) {
               v.pos = new BlockPos(v.pos.getX(), var14 < p2?var14 + 1:p2 + 1, v.pos.getZ());
            } else if(Math.abs(p2 - var14) == 1) {
               int min1;
               int var15;
               if(p2 > var14) {
                  var15 = p2;
                  min1 = var14;
               } else {
                  var15 = var14;
                  min1 = p2;
               }

               if(v.pos.getY() > var15) {
                  v.pos = new BlockPos(v.pos.getX(), var15, v.pos.getZ());
               } else if(v.pos.getY() < min1) {
                  v.pos = new BlockPos(v.pos.getX(), min1, v.pos.getZ());
               }
            } else if(p2 == var14) {
               if(v.pos.getY() > p2) {
                  v.pos = new BlockPos(v.pos.getX(), p2 + 1, v.pos.getZ());
               } else if(v.pos.getY() < p2) {
                  v.pos = new BlockPos(v.pos.getX(), p2 - 1, v.pos.getZ());
               }
            } else {
               System.out.println("Difference is too high: " + String.valueOf(Math.abs(p2 - var14)));
               v.pos = new BlockPos(v.pos.getX(), (int)((double)(p2 + var14) / 2.0D), v.pos.getZ());
            }
         } else if(p2 == 0 && var14 == 0) {
            System.out.println("no near chunks found: " + v.pos.toString());
            double min = Vector.VtoVlengthXZ(v.pos, center.pos);
            int index = 0;

            for(int k = i - 1; k >= 0; --k) {
               double diff = Vector.VtoVlengthXZ(v.pos, chunkLocation.get(k).pos);
               if(diff < min) {
                  index = k;
                  min = diff;
               }
            }

            v.pos =new BlockPos(v.pos.getX(), chunkLocation.get(index).pos.getY(), v.pos.getZ());
         }
      }

      return chunkLocation;
   }

   private List<MultiVector> orderChunks(List<MultiVector> chunkLocation, MultiVector center) {
      List<MultiVector> chunkLocationOut = new ArrayList<MultiVector>();
      chunkLocationOut.add(center);

      int radius;
      for(radius = chunkLocation.size() - 1; radius >= 0; --radius) {
         if(center.equalsXZ(chunkLocation.get(radius))) {
            chunkLocation.remove(radius);
         }
      }

      for(radius = 1; !chunkLocation.isEmpty(); ++radius) {
         for(int i = chunkLocation.size() - 1; i >= 0; --i) {
            MultiVector v = chunkLocation.get(i);
            int diff = Math.abs((center.pos.getX() - v.pos.getX()) / 16) + Math.abs((center.pos.getZ() - v.pos.getZ()) / 16);
            if(diff == radius) {
               chunkLocationOut.add(v);
               chunkLocation.remove(i);
            }
         }
      }

      return chunkLocationOut;
   }

   private void createLayout(List<MultiVector> chunkLocation, Random random, World world) {
      MultiVector[] vectors = new MultiVector[chunkLocation.size()];

      int i;
      for(i = 0; i < vectors.length; ++i) {
         vectors[i] = chunkLocation.get(i).copy();
      }

      CityBlock block;
      while(!chunkLocation.isEmpty()) {
         i = chunkLocation.size() - 1;
         if(random.nextInt(4) == 0) {
            block = this.largeBlock(chunkLocation.get(i), chunkLocation, i);
            this.block.add(block != null?block:this.smallBlock(chunkLocation.get(i), chunkLocation, i));
         } else if(random.nextInt(2) == 0) {
            block = this.midBlock(chunkLocation.get(i), chunkLocation, i, random);
            this.block.add(block != null?block:this.smallBlock(chunkLocation.get(i), chunkLocation, i));
         } else {
            this.block.add(this.smallBlock(chunkLocation.get(i), chunkLocation, i));
         }
      }

      for(int j = 0; j < this.block.size(); ++j) {
         block = this.block.get(j);
         this.setConnections(block, this.block);
         this.setCornerHeights(block, vectors);
         int maxD = 0;

         for(int k = 0; k < block.connectedBlocks.length; ++k) {
            if(block.connectedBlocks[k] != null) {
               int diff = Math.abs(block.area.position.getY() - block.connectedBlocks[k].area.position.getY());
               maxD = diff > maxD?diff:maxD;
            }
         }

         block.doGenerate = maxD < 3;
      }

   }

   private CityBlock largeBlock(MultiVector p, List c, int id) {
      int[] chunkID = this.getAdjChunksInd(p, c);
      int[] northChunkIDs;
      CityBlock block;
      if(chunkID[0] >= 0) {
         northChunkIDs = this.getAdjChunksInd(new MultiVector(p.pos.getX(), p.pos.getY(), p.pos.getZ() + 16), c);
         if(chunkID[1] >= 0 && northChunkIDs[1] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[0], chunkID[1], northChunkIDs[1]});
            block = new CityBlock(new Rectangle(new BlockPos(p.pos), 32, 32));
            return block;
         }

         if(chunkID[3] >= 0 && northChunkIDs[3] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[0], chunkID[3], northChunkIDs[3]});
            block = new CityBlock(new Rectangle(p.pos.add(-16, 0, 0), 32, 32));
            return block;
         }
      } else if(chunkID[2] >= 0) {
         northChunkIDs = this.getAdjChunksInd(new MultiVector(p.pos.getX(), p.pos.getY(), p.pos.getZ() - 16), c);
         if(chunkID[1] >= 0 && northChunkIDs[1] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[2], chunkID[1], northChunkIDs[1]});
            block = new CityBlock(new Rectangle(p.pos.add(0, 0, -16), 32, 32));
            return block;
         }

         if(chunkID[3] >= 0 && northChunkIDs[3] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[2], chunkID[3], northChunkIDs[3]});
            block = new CityBlock(new Rectangle(p.pos.add(-16, 0, -16), 32, 32));
            return block;
         }
      }

      return null;
   }

   private CityBlock midBlock(MultiVector p, List c, int id, Random rand) {
      int[] chunkID = this.getAdjChunksInd(p, c);
      CityBlock block;
      if(rand.nextInt(2) == 0) {
         if(chunkID[0] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[0]});
            block = new CityBlock(new Rectangle(new BlockPos(p.pos), 16, 32));
            return block;
         }

         if(chunkID[2] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[2]});
            block = new CityBlock(new Rectangle(p.pos.add(0, 0, -16), 16, 32));
            return block;
         }

         if(chunkID[1] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[1]});
            block = new CityBlock(new Rectangle(new BlockPos(p.pos), 32, 16));
            return block;
         }

         if(chunkID[3] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[3]});
            block = new CityBlock(new Rectangle(p.pos.add(-16, 0, 0), 32, 16));
            return block;
         }
      } else {
         if(chunkID[1] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[1]});
            block = new CityBlock(new Rectangle(new BlockPos(p.pos), 32, 16));
            return block;
         }

         if(chunkID[3] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[3]});
            block = new CityBlock(new Rectangle(p.pos.add(-16, 0, 0), 32, 16));
            return block;
         }

         if(chunkID[0] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[0]});
            block = new CityBlock(new Rectangle(new BlockPos(p.pos), 16, 32));
            return block;
         }

         if(chunkID[2] >= 0) {
            this.removeIDs(c, new int[]{id, chunkID[2]});
            block = new CityBlock(new Rectangle(p.pos.add(0, 0, -16), 16, 32));
            return block;
         }
      }

      return null;
   }

   private CityBlock smallBlock(MultiVector p, List c, int id) {
      this.removeIDs(c, new int[]{id});
       return new CityBlock(new Rectangle(new BlockPos(p.pos), 16, 16));
   }

   private void setConnections(CityBlock block, List c) {
      int w = block.area.width / 16;
      int l = block.area.length / 16;
      block.connectedFaces = new boolean[2 * w + 2 * l];

      int[] adj;
      BlockPos chunk;
      int i;
      for(i = 0; i < w; ++i) {
         chunk = block.area.position.add(16 * i, 0, 0);
         adj = this.getAdjBlocksInd(chunk, c);
         if(adj[2] >= 0) {
            block.connectedFaces[2 * w + l - i - 1] = adj[2] >= 0;
            block.setConnectedCityBlock((CityBlock)c.get(adj[2]), 2 * w + l - i - 1);
         }

         chunk = chunk.add(0, 0, block.area.length - 16);
         adj = this.getAdjBlocksInd(chunk, c);
         if(adj[0] >= 0) {
            block.connectedFaces[i] = adj[0] >= 0;
            block.setConnectedCityBlock((CityBlock)c.get(adj[0]), i);
         }
      }

      for(i = 0; i < l; ++i) {
         chunk = block.area.position.add(0, 0, 16 * i);
         adj = this.getAdjBlocksInd(chunk, c);
         if(adj[3] >= 0) {
            block.connectedFaces[2 * w + l + i] = adj[3] >= 0;
            block.setConnectedCityBlock((CityBlock)c.get(adj[3]), 2 * w + l + i);
         }

         chunk = chunk.add(block.area.width - 16, 0, 0);
         adj = this.getAdjBlocksInd(chunk, c);
         if(adj[1] >= 0) {
            block.connectedFaces[w + l - i - 1] = adj[1] >= 0;
            block.setConnectedCityBlock((CityBlock)c.get(adj[1]), w + l - i - 1);
         }
      }

   }

   private void setCornerHeights(CityBlock block, MultiVector[] vectors) {
      for(int i = 0; i < block.cornerHeight.length; ++i) {
         BlockPos corner = block.getPositionFromCorner(i);

         for(int flag = 0; flag < vectors.length; ++flag) {
            if(Vector.equalsXZ(corner, vectors[flag].pos)) {
               block.cornerHeight[i] = vectors[flag].pos.getY();
            }
         }

         if(block.cornerHeight[i] == 0) {
            boolean var7 = true;

            for(int j = 0; j < vectors.length && var7; ++j) {
               if(Vector.equalsXZ(corner.add(0, 0, -16), vectors[j].pos)) {
                  block.cornerHeight[i] = vectors[j].pos.getY();
               } else if(Vector.equalsXZ(corner.add(-16, 0, 0), vectors[j].pos)) {
                  block.cornerHeight[i] = vectors[j].pos.getY();
               } else if(Vector.equalsXZ(corner.add(0, 0, 16), vectors[j].pos)) {
                  block.cornerHeight[i] = vectors[j].pos.getY();
               } else if(Vector.equalsXZ(corner.add(16, 0, 0), vectors[j].pos)) {
                  block.cornerHeight[i] = vectors[j].pos.getY();
               }
            }
         }
      }

   }

   private int[] getAdjChunksInd(MultiVector p, List<MultiVector> c) {
      int[] ind = new int[]{-1, -1, -1, -1};

      for(int i = 0; i < c.size(); ++i) {
         int xD = p.pos.getX() - c.get(i).pos.getX();
         int zD = p.pos.getZ() - c.get(i).pos.getZ();
         if(xD == 0 && zD == -16) {
            ind[0] = i;
         } else if(xD == -16 && zD == 0) {
            ind[1] = i;
         } else if(xD == 0 && zD == 16) {
            ind[2] = i;
         } else if(xD == 16 && zD == 0) {
            ind[3] = i;
         }
      }

      return ind;
   }

   private int[] getAdjBlocksInd(BlockPos block, List cityBlocks) {
      int[] ind = new int[]{-1, -1, -1, -1};
      Rectangle north = new Rectangle(block.add(4, 0, 20), 8, 8);
      Rectangle east = new Rectangle(block.add(20, 0, 4), 8, 8);
      Rectangle south = new Rectangle(block.add(4, 0, -12), 8, 8);
      Rectangle west = new Rectangle(block.add(-12, 0, 4), 8, 8);

      for(int i = 0; i < cityBlocks.size(); ++i) {
         if(Rectangle.checkConflict(north, ((CityBlock)cityBlocks.get(i)).area, 0)) {
            ind[0] = i;
         } else if(Rectangle.checkConflict(east, ((CityBlock)cityBlocks.get(i)).area, 0)) {
            ind[1] = i;
         } else if(Rectangle.checkConflict(south, ((CityBlock)cityBlocks.get(i)).area, 0)) {
            ind[2] = i;
         } else if(Rectangle.checkConflict(west, ((CityBlock)cityBlocks.get(i)).area, 0)) {
            ind[3] = i;
         }
      }

      return ind;
   }

   private void removeIDs(List c, int[] id) {
      if(id.length > 1) {
         Arrays.sort(id);

         for(int i = id.length - 1; i >= 0; --i) {
            c.remove(id[i]);
         }
      } else {
         c.remove(id[0]);
      }

   }
}
