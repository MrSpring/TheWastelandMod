//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.ruin;

import dk.mrspring.wasteland.ModHelper;
import dk.mrspring.wasteland.items.LootStack;
import dk.mrspring.wasteland.utils.CustomItemStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class RuinSpawner extends Ruin implements IWorldGenerator
{
    public RuinSpawner(String name)
    {
        super(name);
    }

    protected boolean generate(World world, Random random, int xCoord, int yCoord, int zCoord)
    {
        RuinGenHelper.setWorld(world);
        if (RuinGenHelper.getHeightValue(xCoord + 4, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord - 1) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord + 0) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord + 1) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord + 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord + 3) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 4, zCoord + 4) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 3, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 3, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 2, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 2, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 1, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 1, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 0, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord + 0, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 1, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 1, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 2, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 2, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 3, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 3, zCoord + 5) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 4, zCoord - 2) == yCoord &&
                RuinGenHelper.getHeightValue(xCoord - 4, zCoord + 5) == yCoord)
        {
            System.out.println("Generating Succesfully at: " + ModHelper.getCoordAsString(xCoord, yCoord, zCoord));
            --yCoord;
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.chest);
            TileEntityChest chest = (TileEntityChest) RuinGenHelper.getTileEntity(xCoord + 4, yCoord, zCoord + 4);
            LootStack loot = this.setItems(random);
            CustomItemStack.placeLoot(random, chest, CustomItemStack.getLootItems(random, loot.items, loot.minNum, loot.maxNum, loot.repeat));
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.planks);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.planks);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stone_stairs);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.planks);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.planks);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.air);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.planks);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.netherrack);
            RuinGenHelper.setBlock(xCoord - 2, yCoord + 1, zCoord + 4, Blocks.fire);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 1, Blocks.planks);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 2, Blocks.planks);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 0, Blocks.planks);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.cobblestone);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, RuinGenHelper.getHeightValue(xCoord + 3, zCoord - 4), zCoord - 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, RuinGenHelper.getHeightValue(xCoord - 2, zCoord - 4), zCoord - 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord - 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, RuinGenHelper.getHeightValue(xCoord - 5, zCoord - 5), zCoord - 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.stone_stairs);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.cobblestone);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.torch);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 5, Blocks.stonebrick);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 5, Blocks.stonebrick);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            ++yCoord;
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 5, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 4, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 3, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 0, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 2, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord - 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 2, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 1, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 1, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 3, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 4, Blocks.stonebrick);
            RuinGenHelper.setBlock(xCoord + 0, yCoord, zCoord + 5, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 1, yCoord, zCoord + 4, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord - 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 2, yCoord, zCoord + 3, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord - 2, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 0, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 1, Blocks.stonebrick, 2);
            RuinGenHelper.setBlock(xCoord - 3, yCoord, zCoord + 2, Blocks.stonebrick, 2);
            return true;
        } else
        {
            return false;
        }
    }

    private static ItemStack getLootItem(Random rand, ItemStack[] items)
    {
        int i = rand.nextInt(items.length);
        ItemStack itemStack = new ItemStack(items[i].getItem(), 1);
        return itemStack.getItem() == Items.rotten_flesh ? new ItemStack(itemStack.getItem(), rand.nextInt(4) + 2) : (itemStack.getItem() == Items.bucket ? new ItemStack(itemStack.getItem(), rand.nextInt(3) + 1) : (itemStack.getItem() == Items.spider_eye ? new ItemStack(itemStack.getItem(), rand.nextInt(5) + 1) : (itemStack.getItem() == Items.record_chirp ? new ItemStack(itemStack.getItem(), 1) : (itemStack.getItem() == Items.name_tag ? new ItemStack(itemStack.getItem(), rand.nextInt(1) + 1) : (itemStack.getItem() == Items.potato ? new ItemStack(itemStack.getItem(), rand.nextInt(2) + 1) : (itemStack.getItem() == Items.carrot ? new ItemStack(itemStack.getItem(), rand.nextInt(2) + 1) : (itemStack.getItem() == Items.feather ? new ItemStack(itemStack.getItem(), rand.nextInt(9) + 4) : (itemStack.getItem() == Items.leather_chestplate ? new ItemStack(itemStack.getItem(), 1, itemStack.getMaxDamage() - rand.nextInt(itemStack.getMaxDamage() / 2)) : itemStack))))))));
    }
}
