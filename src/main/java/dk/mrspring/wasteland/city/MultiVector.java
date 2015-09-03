//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.city;

import dk.mrspring.wasteland.utils.Vector;

public class MultiVector extends Vector
{
    MultiVector[] connectedChunk = new MultiVector[]{null, null, null, null};

    public MultiVector(int x, int y, int z)
    {
        super(x, y, z);
    }

    public MultiVector copy()
    {
        return new MultiVector(super.X, super.Y, super.Z);
    }
}
