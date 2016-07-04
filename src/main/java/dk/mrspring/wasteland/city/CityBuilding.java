package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.city.SchematicBuilding;
import dk.mrspring.wasteland.config.CityLootConfig;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.utils.CustomItemStack;
import dk.mrspring.wasteland.utils.Schematic;
import dk.mrspring.wasteland.utils.Vector;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockRail;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CityBuilding {

   public int width;
   public int height;
   public int length;
   public int rotation;
   public String name;
   public boolean duplicate;
   private byte[] blocks;
   private byte[] data;
   private List entities;
   private List tileentities;
   private RuinGenHelper genHelper = new RuinGenHelper();
   public static LootStack easyLoot;
   public static LootStack midLoot;
   public static LootStack hardLoot;
   public static LootStack ultraLoot;
   private BlockPos[] damageLoc;
   private int[] damageSize;
   private int roofHeight;
   public static final Block surfaceBlock = ModConfig.getSurfaceBlock();
   public static final int surfaceBlockMeta = ModConfig.getSurfaceBlockMeta();
   public static final int cobbleID = Block.getIdFromBlock(Blocks.cobblestone);
   public static final int mossyCobbleID = Block.getIdFromBlock(Blocks.mossy_cobblestone);
   public static final int stoneID = Block.getIdFromBlock(Blocks.stone);
   public static final int sandStoneID = Block.getIdFromBlock(Blocks.sandstone);
   public static final int stoneBrickID = Block.getIdFromBlock(Blocks.stonebrick);
   public static final int woolID = Block.getIdFromBlock(Blocks.wool);
   public static final int glassID = Block.getIdFromBlock(Blocks.glass);
   public static final int glassPaneID = Block.getIdFromBlock(Blocks.glass_pane);
   public static final int stainedGlassID = Block.getIdFromBlock(Blocks.stained_glass);
   public static final int woodPlankID = Block.getIdFromBlock(Blocks.planks);
   public static final int chestID = Block.getIdFromBlock(Blocks.chest);
   public static final int spawnerID = Block.getIdFromBlock(Blocks.mob_spawner);
   public static final int airID = Block.getIdFromBlock(Blocks.air);
   public static final int bedrockID = Block.getIdFromBlock(Blocks.bedrock);


   public CityBuilding(String name, int w, int h, int l, byte[] b, byte[] d, int roofHeight, LootStack[] loot) {
      this.width = w;
      this.height = h;
      this.length = l;
      this.roofHeight = roofHeight;
      this.blocks = b;
      this.data = d;
      this.name = name;
      easyLoot = loot[0];
      midLoot = loot[1];
      hardLoot = loot[2];
      ultraLoot = loot[3];
   }

   public void damageBuilding(int num, int maxSize, int minSize, Random rand) {
      int inc = (this.height - this.roofHeight) / num;
      int maxR = this.width / 2 + 1;
      int minR = this.width / 2 - 1;
      this.damageLoc = new BlockPos[num];
      this.damageSize = new int[num];

      for(int i = 0; i < num; ++i) {
         this.damageLoc[i] = Vector.randomVector2D(rand, maxR, minR).add(this.width / 2, rand.nextInt(inc) + inc * i, this.length / 2);
         this.damageSize[i] = rand.nextInt(maxSize - minSize + 1) + minSize;
      }

   }

   public void generate(World world, Random random, BlockPos pos, int rot, int cityColour) {
      RuinGenHelper.setWorld(world);
      int count = 0;
      boolean doGen = true;
      BlockPos p = new BlockPos(0, 0, 0);

      for(short j = 0; j < this.height; ++j) {
         p = new BlockPos(p.getX(), j, p.getZ());

         for(short k = 0; k < this.length; ++k) {
            for(short i = 0; i < this.width; ++i) {
               p = this.rotateVector(p, rot, i, k, this.width, this.length);
               this.rotateBlock(this.blocks, this.data, count, rot);
               int meta;
               if(this.damageLoc != null) {
                  for(meta = 0; meta < this.damageLoc.length; ++meta) {
                     doGen = doGen && Vector.VtoVlength(p, this.damageLoc[meta]) > (double)this.damageSize[meta];
                  }
               }

               if(doGen) {
                  if(this.blocks[count] == bedrockID) {
                     RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, surfaceBlock, surfaceBlockMeta);
                  } else if(this.blocks[count] == airID) {
                     if(p.Y > 0 && p.Y < 3) {
                        RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.air);
                     }
                  } else if(this.blocks[count] == chestID) {
                     if(random.nextInt(10) != 0) {
                        RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.chest, this.data[count]);
                        TileEntityChest var17 = (TileEntityChest)world.getTileEntity(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z);
                        LootStack var15 = this.setItems(random);
                        CustomItemStack.placeLoot(random, var17, CustomItemStack.getLootItems(random, var15.items, var15.minNum, var15.maxNum, var15.repeat));
                     }
                  } else if(this.blocks[count] == spawnerID) {
                     if(random.nextInt(12) != 0) {
                        String var16 = ModConfig.getSpawnerCreature(random);
                        RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mob_spawner);
                        TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner)world.getTileEntity(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z);
                        mobSpawner.func_145881_a().setEntityName(var16);
                     }
                  } else {
                     int randomNumber;
                     if(this.blocks[count] != cobbleID && this.blocks[count] != mossyCobbleID) {
                        if(this.blocks[count] == stoneID) {
                           if(random.nextInt(75) != 0) {
                              randomNumber = random.nextInt(10);
                              if(randomNumber == 0) {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mossy_cobblestone);
                              } else if(randomNumber < 3) {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.cobblestone);
                              } else {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stone);
                              }
                           }
                        } else if(this.blocks[count] == stoneBrickID) {
                           if(random.nextInt(75) != 0) {
                              randomNumber = random.nextInt(10);
                              meta = randomNumber < 5?0:(randomNumber > 6?2:1);
                              RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stonebrick, meta);
                           }
                        } else if(this.blocks[count] == sandStoneID) {
                           if(random.nextInt(65) != 0) {
                              randomNumber = random.nextInt(10);
                              meta = randomNumber < 4?0:2;
                              RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.sandstone, meta);
                           }
                        } else if(this.blocks[count] != glassID && this.blocks[count] != glassPaneID) {
                           if(this.blocks[count] != woolID && this.blocks[count] != stainedGlassID) {
                              if(this.blocks[count] == woodPlankID) {
                                 if(random.nextInt(40) != 0) {
                                    RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.planks, this.data[count]);
                                 }
                              } else {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]);
                              }
                           } else if(random.nextInt(40) != 0) {
                              if(this.data[count] != 0 && this.data[count] != 15 && this.data[count] != 8) {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), cityColour);
                              } else {
                                 RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]);
                              }
                           }
                        } else if(random.nextInt(20) != 0) {
                           RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Block.getBlockById(this.blocks[count]), this.data[count]);
                        }
                     } else if(random.nextInt(50) != 0) {
                        randomNumber = random.nextInt(10);
                        if(randomNumber == 0) {
                           RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.stone);
                        } else if(randomNumber < 4) {
                           RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.mossy_cobblestone);
                        } else {
                           RuinGenHelper.setBlock(pos.X + p.X, pos.Y + p.Y, pos.Z + p.Z, Blocks.cobblestone);
                        }
                     }
                  }
               }

               doGen = true;
               ++count;
            }
         }
      }

   }

   private BlockPos rotateVector(BlockPos p, int rot, short i, short k, int width, int length) {
      int x, z;
      if(rot == 1) {
         x = k;
         z = width - i - 1;
      } else if(rot == 2) {
         x = width - i - 1;
         z = length - k - 1;
      } else if(rot == 3) {
         x = length - k - 1;
         z = i;
      } else {
         x = i;
         z = k;
      }

      return new BlockPos(x, p.getY(), z);
   }

   private void rotateBlock(byte[] blocks, byte[] data, int index, int rot) {
      Block block = Block.getBlockById(blocks[index]);
      byte meta = data[index];
      byte d;
      if(block instanceof BlockStairs) {
         d = (byte)(meta & 3);
         meta = (byte)(meta & 252);
         if(rot == 1) {
            data[index] = (byte)(d == 0?meta + 3:(d == 1?meta + 2:(d == 2?meta:meta + 1)));
         } else if(rot == 2) {
            data[index] = (byte)(d == 0?meta + 1:(d == 1?meta:(d == 2?meta + 3:meta + 2)));
         } else if(rot == 3) {
            data[index] = (byte)(d == 0?meta + 2:(d == 1?meta + 3:(d == 2?meta + 1:meta)));
         }
      } else if(block instanceof BlockDoor) {
         if((meta & 128) == 0) {
            d = (byte)(meta & 3);
            meta = (byte)(meta & 252);
            if(rot == 1) {
               data[index] = (byte)(d == 0?meta + 3:(d == 1?meta:(d == 2?meta + 1:meta + 2)));
            } else if(rot == 2) {
               data[index] = (byte)(d == 0?meta + 2:(d == 1?meta + 3:(d == 2?meta:meta + 1)));
            } else if(rot == 3) {
               data[index] = (byte)(d == 0?meta + 1:(d == 1?meta + 2:(d == 2?meta + 3:meta)));
            }
         }
      } else if(block instanceof BlockTrapDoor && (meta & 128) == 0) {
         d = (byte)(meta & 3);
         meta = (byte)(meta & 252);
         if(rot == 1) {
            data[index] = (byte)(d == 0?meta + 1:(d == 1?meta + 2:(d == 2?meta + 3:meta)));
         } else if(rot == 2) {
            data[index] = (byte)(d == 0?meta + 2:(d == 1?meta + 3:(d == 2?meta:meta + 1)));
         } else if(rot == 3) {
            data[index] = (byte)(d == 0?meta + 3:(d == 1?meta:(d == 2?meta + 1:meta + 2)));
         }
      }

      if(!(block instanceof BlockSign) && !(block instanceof BlockLadder) && !(block instanceof BlockChest) && !(block instanceof BlockFurnace)) {
         if(block instanceof BlockLever) {
            d = (byte)(meta & 7);
            meta = (byte)(meta & 248);
            if(d > 0 && d < 5) {
               if(rot == 1) {
                  data[index] = (byte)(d == 1?meta + 4:(d == 2?meta + 3:(d == 3?meta + 1:meta + 2)));
               } else if(rot == 2) {
                  data[index] = (byte)(d == 1?meta + 2:(d == 2?meta + 1:(d == 3?meta + 4:meta + 3)));
               } else if(rot == 3) {
                  data[index] = (byte)(d == 1?meta + 3:(d == 2?meta + 4:(d == 3?meta + 2:meta + 1)));
               }
            }
         } else if(block instanceof BlockBed) {
            d = (byte)(meta & 3);
            meta = (byte)(meta & 252);
            if(rot == 1) {
               data[index] = (byte)(d == 0?meta + 3:(d == 1?meta:(d == 2?meta + 1:meta + 2)));
            } else if(rot == 2) {
               data[index] = (byte)(d == 0?meta + 2:(d == 1?meta + 3:(d == 2?meta:meta + 1)));
            } else if(rot == 3) {
               data[index] = (byte)(d == 0?meta + 1:(d == 1?meta + 2:(d == 2?meta + 3:meta)));
            }
         } else if(block instanceof BlockRail) {
            d = (byte)(meta & 15);
            meta = (byte)(meta & 240);
            if(rot == 1) {
               if(d < 2) {
                  data[index] = (byte)(d == 0?meta + 1:meta);
               } else if(d >= 2 && d < 6) {
                  data[index] = (byte)(d == 2?meta + 4:(d == 3?meta + 5:(d == 4?meta + 3:meta + 2)));
               } else {
                  data[index] = (byte)(d == 6?meta + 9:(d == 7?meta + 6:(d == 8?meta + 7:meta + 8)));
               }
            } else if(rot == 2) {
               if(d >= 2 && d < 6) {
                  data[index] = (byte)(d == 2?meta + 3:(d == 3?meta + 2:(d == 4?meta + 5:meta + 4)));
               } else {
                  data[index] = (byte)(d == 6?meta + 8:(d == 7?meta + 9:(d == 8?meta + 6:meta + 7)));
               }
            } else if(rot == 3) {
               if(d < 2) {
                  data[index] = (byte)(d == 0?meta + 1:meta);
               } else if(d >= 2 && d < 6) {
                  data[index] = (byte)(d == 2?meta + 5:(d == 3?meta + 4:(d == 4?meta + 2:meta + 3)));
               } else {
                  data[index] = (byte)(d == 6?meta + 7:(d == 7?meta + 8:(d == 8?meta + 9:meta + 6)));
               }
            }
         }
      } else {
         d = (byte)(meta & 7);
         meta = (byte)(meta & 248);
         if(rot == 1) {
            data[index] = (byte)(d == 2?meta + 4:(d == 3?meta + 5:(d == 4?meta + 3:meta + 2)));
         } else if(rot == 2) {
            data[index] = (byte)(d == 2?meta + 3:(d == 3?meta + 2:(d == 4?meta + 5:meta + 4)));
         } else if(rot == 3) {
            data[index] = (byte)(d == 2?meta + 5:(d == 3?meta + 4:(d == 4?meta + 2:meta + 3)));
         }
      }

   }

   private LootStack setItems(Random random) {
      return random.nextInt(CityLootConfig.ultraLootChance) == 0?ultraLoot:(random.nextInt(CityLootConfig.hardLootChance) == 0?midLoot:(random.nextInt(CityLootConfig.midLootChance) == 0?midLoot:easyLoot));
   }

   public static CityBuilding create(String name, int floors, Random random, List buildingSchematics, LootStack[] loot) {
      if(name.equalsIgnoreCase("none")) {
         return null;
      } else {
         for(int i = 0; i < buildingSchematics.size(); ++i) {
            SchematicBuilding current = (SchematicBuilding)buildingSchematics.get(i);
            if(current.name.equals(name)) {
               boolean singleFloor = current.top == null || current.middle == null;
               byte chestID = (byte)Block.getIdFromBlock(Blocks.chest);
               byte airID = (byte)Block.getIdFromBlock(Blocks.air);
               Schematic bottom = current.bottom.length == 1?current.bottom[0]:current.bottom[random.nextInt(current.bottom.length)];
               int w = bottom.width;
               int l = bottom.length;
               int area = w * l;
               Schematic middle;
               Schematic top;
               int height;
               int topH;
               if(!singleFloor) {
                  middle = current.middle.length == 1?current.middle[0]:current.middle[random.nextInt(current.middle.length)];
                  top = current.top.length == 1?current.top[0]:current.top[random.nextInt(current.top.length)];
                  height = bottom.height + top.height + middle.height * floors;
                  topH = top.height;
               } else {
                  middle = null;
                  top = null;
                  height = bottom.height;
                  topH = 0;
               }

               int size = area * height;
               byte[] blocks = new byte[size];
               byte[] meta = new byte[size];
               int h = bottom.blocks.length;
               int chestIndx = bottom.chestNum == 0?-1:random.nextInt(bottom.chestNum);
               int chestCount = 0;

               int offset;
               for(offset = 0; offset < h; ++offset) {
                  if(bottom.blocks[offset] == chestID) {
                     blocks[offset] = chestCount == chestIndx?chestID:airID;
                     meta[offset] = chestCount == chestIndx?bottom.data[offset]:0;
                     ++chestCount;
                  } else {
                     blocks[offset] = bottom.blocks[offset];
                     meta[offset] = bottom.data[offset];
                  }
               }

               if(!singleFloor) {
                  offset = bottom.blocks.length;
                  h = middle.blocks.length;
                  boolean var27 = false;

                  int j;
                  for(j = 0; j < floors; ++j) {
                     chestIndx = middle.chestNum == 0?-1:random.nextInt(middle.chestNum);
                     chestCount = 0;

                     for(int j1 = 0; j1 < h; ++j1) {
                        if(middle.blocks[j1] == chestID) {
                           blocks[j1 + offset] = chestCount == chestIndx?chestID:airID;
                           meta[j1 + offset] = chestCount == chestIndx?middle.data[j1]:0;
                           ++chestCount;
                        } else {
                           blocks[j1 + offset] = middle.blocks[j1];
                           meta[j1 + offset] = middle.data[j1];
                        }
                     }

                     offset += middle.blocks.length;
                  }

                  h = top.blocks.length;
                  chestIndx = top.chestNum == 0?-1:random.nextInt(top.chestNum);
                  chestCount = 0;

                  for(j = 0; j < h; ++j) {
                     if(top.blocks[j] == chestID) {
                        blocks[j + offset] = chestCount == chestIndx?chestID:airID;
                        meta[j + offset] = chestCount == chestIndx?top.data[j]:0;
                        ++chestCount;
                     } else {
                        blocks[j + offset] = top.blocks[j];
                        meta[j + offset] = top.data[j];
                     }
                  }
               }

               return new CityBuilding(name, w, height, l, blocks, meta, topH, loot);
            }
         }

         System.out.println("Unknown City Building");
         return null;
      }
   }

}
