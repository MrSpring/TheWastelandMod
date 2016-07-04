package dk.mrspring.wasteland.utils;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;

public class Schematic
{
    public int width = 0;
    public int length = 0;
    public int height = 0;
    public byte[] blocks = new byte[0];
    public byte[] data = new byte[0];
    public int chestNum = 0;

    public void countChests()
    {
        this.chestNum = 0;
        int chestID = Block.getIdFromBlock(Blocks.chest);
        for (byte block : this.blocks) if (block == chestID) this.chestNum++;
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        this.width = compound.getShort("Width");
        this.length = compound.getShort("Length");
        this.height = compound.getShort("Height");
        this.blocks = compound.getByteArray("Blocks");
        this.data = compound.getByteArray("Data");
        this.countChests();
    }
}
