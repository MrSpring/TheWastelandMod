//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import dk.mrspring.wasteland.utils.Message;
import dk.mrspring.wasteland.utils.Vector;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.ArrayList;
import java.util.Random;

public class GetBiomesCommand extends CommandBase
{
    public static final int minSize = 200;

    public GetBiomesCommand()
    {
    }

    public String getCommandName()
    {
        return "biomes";
    }

    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return "/biomes <range> (min range is " + String.valueOf(200) + ")";
    }

    public void processCommand(ICommandSender iCommandSender, String[] var)
    {
        if (iCommandSender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) iCommandSender;
            World world = player.worldObj;
            if (var.length > 0 && player instanceof EntityPlayerMP)
            {
                int range = Math.max(Integer.parseInt(var[0]), 200);
                processCommandServer((EntityPlayerMP) player, range, new Vector((int) player.posX, (int) player.posY, (int) player.posZ));
            }
        }

    }

    public static void processCommandServer(EntityPlayerMP player, int range, Vector pos)
    {
        sendBiomes(range, pos, player, player.worldObj);
    }

    public static void processCommandServer(String playerName, int range, Vector pos)
    {
        World world = MinecraftServer.getServer().getEntityWorld();
        EntityPlayer player = world.getPlayerEntityByName(playerName);
        if (player instanceof EntityPlayerMP)
        {
            sendBiomes(range, pos, (EntityPlayerMP) player, world);
        } else
        {
            System.out.println("Not multiplayer");
        }

    }

    public static void sendBiomes(int range, Vector position, EntityPlayerMP player, World world)
    {
        short minSize = 200;
        int interval = range / minSize;
        int[][] biomeMap = new int[minSize][minSize];
        new Random();
        Wasteland.NETWORK.sendToAll(Message.createChatMessage("Creating biomes map (please wait)..."));
        Wasteland.NETWORK.sendToAll(Message.createProgressMessage(0, 0));
        ArrayList biomes = new ArrayList();
        float count = 0.0F;

        for (int j = 0; j < minSize; ++j)
        {
            for (int i = 0; i < minSize; ++i)
            {
                BiomeGenBase currentBiome = world.getBiomeGenForCoords(position.X - minSize * interval / 2 + i * interval, position.Z - minSize * interval / 2 + j * interval);
                if (!biomes.contains(currentBiome))
                {
                    biomes.add(currentBiome);
                }

                biomeMap[j][i] = getBiomeColour(currentBiome) + -16777216;
            }

            if ((double) (count / (float) minSize) > 0.01D)
            {
                Wasteland.NETWORK.sendToAll(Message.createProgressMessage(j, minSize));
                count = 0.0F;
            }

            ++count;
        }

        Wasteland.NETWORK.sendToAll(Message.createProgressMessage(1, 1));
        Wasteland.NETWORK.sendToAll(Message.createChatMessage("...done"));
        Wasteland.NETWORK.sendTo(Message.createBiomesMessage(biomeMap, biomes, minSize), player);
    }

    private static int getBiomeColour(BiomeGenBase biomeGenBase)
    {
        return biomeGenBase != null ? biomeGenBase.color & 16777215 : 0;
    }
}
