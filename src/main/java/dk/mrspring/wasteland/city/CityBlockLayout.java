//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.utils.Rectangle;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CityBlockLayout
{
    List<CityBlock> block = new ArrayList();

    public CityBlockLayout(List<MultiVector> chunkLocation, MultiVector center, Random random, World world)
    {
        this.connectChunks(chunkLocation);
        List chunks = this.reduceHeightVariation(chunkLocation, center);
        this.createLayout(chunks, random, world);
        this.setBuildings(random);
    }

    private void setBuildings(Random random)
    {
        if (!this.block.isEmpty())
        {
            for (int i = 0; i < this.block.size(); ++i)
            {
                CityBlock current = (CityBlock) this.block.get(i);
                int r;
                if (current.area.width == 32 && current.area.length == 32)
                {
                    r = random.nextInt(6);
                    switch (r)
                    {
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
                } else if (current.area.width == 16 && current.area.length == 16)
                {
                    r = random.nextInt(5);
                    switch (r)
                    {
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
                } else
                {
                    r = random.nextInt(6);
                    switch (r)
                    {
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

    private void connectChunks(List<MultiVector> chunks)
    {
        for (int i = 0; i < chunks.size(); ++i)
        {
            MultiVector current = (MultiVector) chunks.get(i);

            for (int j = 0; j < chunks.size(); ++j)
            {
                MultiVector compare = (MultiVector) chunks.get(j);
                if (current.copy().add(new Vector(0, 0, 16)).equalsXZ(compare))
                {
                    current.connectedChunk[0] = compare;
                } else if (current.copy().add(new Vector(0, 0, -16)).equalsXZ(compare))
                {
                    current.connectedChunk[2] = compare;
                } else if (current.copy().add(new Vector(16, 0, 0)).equalsXZ(compare))
                {
                    current.connectedChunk[1] = compare;
                } else if (current.copy().add(new Vector(-16, 0, 0)).equalsXZ(compare))
                {
                    current.connectedChunk[3] = compare;
                }
            }
        }

    }

    private List<MultiVector> reduceHeightVariation(List<MultiVector> chunkLocationIn, MultiVector center)
    {
        List chunkLocation = this.orderChunks(chunkLocationIn, center);
        boolean p1 = false;
        int p2 = 0;

        for (int i = 1; i < chunkLocation.size(); ++i)
        {
            MultiVector v = (MultiVector) chunkLocation.get(i);
            int var14;
            if (v.X > center.X)
            {
                var14 = v.connectedChunk[3] != null ? v.connectedChunk[3].Y : 0;
                if (v.Z > center.Z)
                {
                    p2 = v.connectedChunk[2] != null ? v.connectedChunk[2].Y : 0;
                } else if (v.Z < center.Z)
                {
                    p2 = v.connectedChunk[0] != null ? v.connectedChunk[0].Y : 0;
                }
            } else if (v.X < center.X)
            {
                var14 = v.connectedChunk[1] != null ? v.connectedChunk[1].Y : 0;
                if (v.Z > center.Z)
                {
                    p2 = v.connectedChunk[2] != null ? v.connectedChunk[2].Y : 0;
                } else if (v.Z < center.Z)
                {
                    p2 = v.connectedChunk[0] != null ? v.connectedChunk[0].Y : 0;
                }
            } else if (v.Z > center.Z)
            {
                var14 = v.connectedChunk[2] != null ? v.connectedChunk[2].Y : 0;
            } else
            {
                var14 = v.connectedChunk[0] != null ? v.connectedChunk[0].Y : 0;
            }

            if (p2 == 0 && var14 > 0)
            {
                if (v.Y > var14)
                {
                    v.Y = var14 + 1;
                } else if (v.Y < var14)
                {
                    v.Y = var14 - 1;
                }
            } else if (p2 > 0 && var14 > 0)
            {
                if (Math.abs(p2 - var14) == 2)
                {
                    v.Y = var14 < p2 ? var14 + 1 : p2 + 1;
                } else if (Math.abs(p2 - var14) == 1)
                {
                    int min1;
                    int var15;
                    if (p2 > var14)
                    {
                        var15 = p2;
                        min1 = var14;
                    } else
                    {
                        var15 = var14;
                        min1 = p2;
                    }

                    if (v.Y > var15)
                    {
                        v.Y = var15;
                    } else if (v.Y < min1)
                    {
                        v.Y = min1;
                    }
                } else if (p2 == var14)
                {
                    if (v.Y > p2)
                    {
                        v.Y = p2 + 1;
                    } else if (v.Y < p2)
                    {
                        v.Y = p2 - 1;
                    }
                } else
                {
                    System.out.println("Difference is too high: " + String.valueOf(Math.abs(p2 - var14)));
                    v.Y = (int) ((double) (p2 + var14) / 2.0D);
                }
            } else if (p2 == 0 && var14 == 0)
            {
                System.out.println("no near chunks found: " + v.toCustomString());
                double min = Vector.VtoVlengthXZ(v, center);
                int index = 0;

                for (int k = i - 1; k >= 0; --k)
                {
                    double diff = Vector.VtoVlengthXZ(v, (Vector) chunkLocation.get(k));
                    if (diff < min)
                    {
                        index = k;
                        min = diff;
                    }
                }

                v.Y = ((MultiVector) chunkLocation.get(index)).Y;
            }
        }

        return chunkLocation;
    }

    private List<MultiVector> orderChunks(List<MultiVector> chunkLocation, MultiVector center)
    {
        ArrayList chunkLocationOut = new ArrayList();
        chunkLocationOut.add(center);

        int radius;
        for (radius = chunkLocation.size() - 1; radius >= 0; --radius)
        {
            if (center.equalsXZ((Vector) chunkLocation.get(radius)))
            {
                chunkLocation.remove(radius);
            }
        }

        for (radius = 1; !chunkLocation.isEmpty(); ++radius)
        {
            for (int i = chunkLocation.size() - 1; i >= 0; --i)
            {
                MultiVector v = (MultiVector) chunkLocation.get(i);
                int diff = Math.abs((center.X - v.X) / 16) + Math.abs((center.Z - v.Z) / 16);
                if (diff == radius)
                {
                    chunkLocationOut.add(v);
                    chunkLocation.remove(i);
                }
            }
        }

        return chunkLocationOut;
    }

    private void createLayout(List<MultiVector> chunkLocation, Random random, World world)
    {
        Vector[] vectors = new Vector[chunkLocation.size()];

        int i;
        for (i = 0; i < vectors.length; ++i)
        {
            vectors[i] = ((MultiVector) chunkLocation.get(i)).copy();
        }

        CityBlock block;
        while (!chunkLocation.isEmpty())
        {
            i = chunkLocation.size() - 1;
            if (random.nextInt(4) == 0)
            {
                block = this.largeBlock((MultiVector) chunkLocation.get(i), chunkLocation, i);
                this.block.add(block != null ? block : this.smallBlock((MultiVector) chunkLocation.get(i), chunkLocation, i));
            } else if (random.nextInt(2) == 0)
            {
                block = this.midBlock((MultiVector) chunkLocation.get(i), chunkLocation, i, random);
                this.block.add(block != null ? block : this.smallBlock((MultiVector) chunkLocation.get(i), chunkLocation, i));
            } else
            {
                this.block.add(this.smallBlock((MultiVector) chunkLocation.get(i), chunkLocation, i));
            }
        }

        for (int j = 0; j < this.block.size(); ++j)
        {
            block = (CityBlock) this.block.get(j);
            this.setConnections(block, this.block);
            this.setCornerHeights(block, vectors);
            int maxD = 0;

            for (int k = 0; k < block.connectedBlocks.length; ++k)
            {
                if (block.connectedBlocks[k] != null)
                {
                    int diff = Math.abs(block.area.position.Y - block.connectedBlocks[k].area.position.Y);
                    maxD = diff > maxD ? diff : maxD;
                }
            }

            block.doGenerate = maxD < 3;
        }

    }

    private CityBlock largeBlock(MultiVector p, List<MultiVector> c, int id)
    {
        int[] chunkID = this.getAdjChunksInd(p, c);
        int[] northChunkIDs;
        CityBlock block;
        if (chunkID[0] >= 0)
        {
            northChunkIDs = this.getAdjChunksInd(new MultiVector(p.X, p.Y, p.Z + 16), c);
            if (chunkID[1] >= 0 && northChunkIDs[1] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[0], chunkID[1], northChunkIDs[1]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 32));
                return block;
            }

            if (chunkID[3] >= 0 && northChunkIDs[3] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[0], chunkID[3], northChunkIDs[3]});
                block = new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 32));
                return block;
            }
        } else if (chunkID[2] >= 0)
        {
            northChunkIDs = this.getAdjChunksInd(new MultiVector(p.X, p.Y, p.Z - 16), c);
            if (chunkID[1] >= 0 && northChunkIDs[1] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[2], chunkID[1], northChunkIDs[1]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 32, 32));
                return block;
            }

            if (chunkID[3] >= 0 && northChunkIDs[3] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[2], chunkID[3], northChunkIDs[3]});
                block = new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z - 16), 32, 32));
                return block;
            }
        }

        return null;
    }

    private CityBlock midBlock(MultiVector p, List<MultiVector> c, int id, Random rand)
    {
        int[] chunkID = this.getAdjChunksInd(p, c);
        CityBlock block;
        if (rand.nextInt(2) == 0)
        {
            if (chunkID[0] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[0]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 32));
                return block;
            }

            if (chunkID[2] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[2]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 16, 32));
                return block;
            }

            if (chunkID[1] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[1]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 16));
                return block;
            }

            if (chunkID[3] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[3]});
                block = new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 16));
                return block;
            }
        } else
        {
            if (chunkID[1] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[1]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 32, 16));
                return block;
            }

            if (chunkID[3] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[3]});
                block = new CityBlock(new Rectangle(new Vector(p.X - 16, p.Y, p.Z), 32, 16));
                return block;
            }

            if (chunkID[0] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[0]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 32));
                return block;
            }

            if (chunkID[2] >= 0)
            {
                this.removeIDs(c, new int[]{id, chunkID[2]});
                block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z - 16), 16, 32));
                return block;
            }
        }

        return null;
    }

    private CityBlock smallBlock(MultiVector p, List<MultiVector> c, int id)
    {
        this.removeIDs(c, new int[]{id});
        CityBlock block = new CityBlock(new Rectangle(new Vector(p.X, p.Y, p.Z), 16, 16));
        return block;
    }

    private void setConnections(CityBlock block, List<CityBlock> c)
    {
        int w = block.area.width / 16;
        int l = block.area.length / 16;
        block.connectedFaces = new boolean[2 * w + 2 * l];

        int[] adj;
        Vector chunk;
        int i;
        for (i = 0; i < w; ++i)
        {
            chunk = block.area.position.copy();
            chunk.X += 16 * i;
            adj = this.getAdjBlocksInd(chunk, c);
            if (adj[2] >= 0)
            {
                block.connectedFaces[2 * w + l - i - 1] = adj[2] >= 0;
                block.setConnectedCityBlock((CityBlock) c.get(adj[2]), 2 * w + l - i - 1);
            }

            chunk.Z = chunk.Z + block.area.length - 16;
            adj = this.getAdjBlocksInd(chunk, c);
            if (adj[0] >= 0)
            {
                block.connectedFaces[i] = adj[0] >= 0;
                block.setConnectedCityBlock((CityBlock) c.get(adj[0]), i);
            }
        }

        for (i = 0; i < l; ++i)
        {
            chunk = block.area.position.copy();
            chunk.Z += 16 * i;
            adj = this.getAdjBlocksInd(chunk, c);
            if (adj[3] >= 0)
            {
                block.connectedFaces[2 * w + l + i] = adj[3] >= 0;
                block.setConnectedCityBlock((CityBlock) c.get(adj[3]), 2 * w + l + i);
            }

            chunk.X = chunk.X + block.area.width - 16;
            adj = this.getAdjBlocksInd(chunk, c);
            if (adj[1] >= 0)
            {
                block.connectedFaces[w + l - i - 1] = adj[1] >= 0;
                block.setConnectedCityBlock((CityBlock) c.get(adj[1]), w + l - i - 1);
            }
        }

    }

    private void setCornerHeights(CityBlock block, Vector[] vectors)
    {
        for (int i = 0; i < block.cornerHeight.length; ++i)
        {
            Vector corner = block.getPositionFromCorner(i);

            for (int flag = 0; flag < vectors.length; ++flag)
            {
                if (corner.equalsXZ(vectors[flag]))
                {
                    block.cornerHeight[i] = vectors[flag].Y;
                }
            }

            if (block.cornerHeight[i] == 0)
            {
                boolean var7 = true;

                for (int j = 0; j < vectors.length && var7; ++j)
                {
                    if (corner.copy().add(new Vector(0, 0, -16)).equalsXZ(vectors[j]))
                    {
                        block.cornerHeight[i] = vectors[j].Y;
                    } else if (corner.copy().add(new Vector(-16, 0, 0)).equalsXZ(vectors[j]))
                    {
                        block.cornerHeight[i] = vectors[j].Y;
                    } else if (corner.copy().add(new Vector(0, 0, 16)).equalsXZ(vectors[j]))
                    {
                        block.cornerHeight[i] = vectors[j].Y;
                    } else if (corner.copy().add(new Vector(16, 0, 0)).equalsXZ(vectors[j]))
                    {
                        block.cornerHeight[i] = vectors[j].Y;
                    }
                }
            }
        }

    }

    private int[] getAdjChunksInd(MultiVector p, List<MultiVector> c)
    {
        int[] ind = new int[]{-1, -1, -1, -1};

        for (int i = 0; i < c.size(); ++i)
        {
            int xD = p.X - ((MultiVector) c.get(i)).X;
            int zD = p.Z - ((MultiVector) c.get(i)).Z;
            if (xD == 0 && zD == -16)
            {
                ind[0] = i;
            } else if (xD == -16 && zD == 0)
            {
                ind[1] = i;
            } else if (xD == 0 && zD == 16)
            {
                ind[2] = i;
            } else if (xD == 16 && zD == 0)
            {
                ind[3] = i;
            }
        }

        return ind;
    }

    private int[] getAdjBlocksInd(Vector block, List<CityBlock> cityBlocks)
    {
        int[] ind = new int[]{-1, -1, -1, -1};
        Rectangle north = new Rectangle(block.copy().add(new Vector(4, 0, 20)), 8, 8);
        Rectangle east = new Rectangle(block.copy().add(new Vector(20, 0, 4)), 8, 8);
        Rectangle south = new Rectangle(block.copy().add(new Vector(4, 0, -12)), 8, 8);
        Rectangle west = new Rectangle(block.copy().add(new Vector(-12, 0, 4)), 8, 8);

        for (int i = 0; i < cityBlocks.size(); ++i)
        {
            if (Rectangle.checkConflict(north, ((CityBlock) cityBlocks.get(i)).area, 0))
            {
                ind[0] = i;
            } else if (Rectangle.checkConflict(east, ((CityBlock) cityBlocks.get(i)).area, 0))
            {
                ind[1] = i;
            } else if (Rectangle.checkConflict(south, ((CityBlock) cityBlocks.get(i)).area, 0))
            {
                ind[2] = i;
            } else if (Rectangle.checkConflict(west, ((CityBlock) cityBlocks.get(i)).area, 0))
            {
                ind[3] = i;
            }
        }

        return ind;
    }

    private void removeIDs(List<MultiVector> c, int[] id)
    {
        if (id.length > 1)
        {
            Arrays.sort(id);

            for (int i = id.length - 1; i >= 0; --i)
            {
                c.remove(id[i]);
            }
        } else
        {
            c.remove(id[0]);
        }

    }
}
