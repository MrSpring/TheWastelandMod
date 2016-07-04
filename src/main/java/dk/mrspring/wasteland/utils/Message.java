package dk.mrspring.wasteland.utils;

import dk.mrspring.wasteland.GetBiomesCommand;
import dk.mrspring.wasteland.client.ClientProxy;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class Message implements IMessage
{
    private byte[] data;

    public Message()
    {
    }

    public Message(byte[] data)
    {
        this.data = data;
    }

    public static Message createBiomesMessage(int[][] biomeMap, List<BiomeGenBase> biomes, int size)
    {
        int numBiomes = biomes.size();
        int dataSize = size * size * 4;
        byte[] dataOut = new byte[dataSize + numBiomes * 4 + 5];

        dataOut[0] = 1;

        dataOut[1] = ((byte) ((size & 0xFF000000) >> 24));
        dataOut[2] = ((byte) ((size & 0xFF0000) >> 16));
        dataOut[3] = ((byte) ((size & 0xFF00) >> 8));
        dataOut[4] = ((byte) (size & 0xFF));

        int count = 5;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                dataOut[count] = ((byte) ((biomeMap[i][j] & 0xFF000000) >> 24));
                dataOut[(count + 1)] = ((byte) ((biomeMap[i][j] & 0xFF0000) >> 16));
                dataOut[(count + 2)] = ((byte) ((biomeMap[i][j] & 0xFF00) >> 8));
                dataOut[(count + 3)] = ((byte) (biomeMap[i][j] & 0xFF));

                count += 4;
            }
        }
        for (int i = 0; i < numBiomes; i++)
        {
            int id = ((BiomeGenBase) biomes.get(i)).biomeID;

            dataOut[count] = ((byte) ((id & 0xFF000000) >> 24));
            dataOut[(count + 1)] = ((byte) ((id & 0xFF0000) >> 16));
            dataOut[(count + 2)] = ((byte) ((id & 0xFF00) >> 8));
            dataOut[(count + 3)] = ((byte) (id & 0xFF));

            count += 4;
        }
        return new Message(dataOut);
    }

    public static Message createChatMessage(String string)
    {
        byte[] dataOut = new byte[string.length() + 1];

        dataOut[0] = 2;
        for (int i = 1; i < string.length() + 1; i++)
        {
            dataOut[i] = ((byte) string.charAt(i - 1));
        }
        return new Message(dataOut);
    }

    public static Message createPlayerInfoMessage(EntityPlayer player)
    {
        String playerName = player.getDisplayName().getFormattedText();
        BlockPos pos = player.getPosition();

        byte[] dataOut = new byte[12 + playerName.length() + 1];

        dataOut[0] = 3;

        int count = 1;
        for (int i = 0; i < 3; i++)
        {
            int p = i == 1 ? pos.getY() : i == 0 ? pos.getX() : pos.getZ();

            dataOut[count] = ((byte) ((p & 0xFF000000) >> 24));
            dataOut[(count + 1)] = ((byte) ((p & 0xFF0000) >> 16));
            dataOut[(count + 2)] = ((byte) ((p & 0xFF00) >> 8));
            dataOut[(count + 3)] = ((byte) (p & 0xFF));

            count += 4;
        }
        for (int i = 0; i < playerName.length(); i++)
        {
            dataOut[count] = ((byte) playerName.charAt(i));
            count++;
        }
        return new Message(dataOut);
    }

    public static Message createProgressMessage(int progress, int total)
    {
        byte[] dataOut = new byte[9];

        dataOut[0] = 4;

        dataOut[1] = ((byte) ((progress & 0xFF000000) >> 24));
        dataOut[2] = ((byte) ((progress & 0xFF0000) >> 16));
        dataOut[3] = ((byte) ((progress & 0xFF00) >> 8));
        dataOut[4] = ((byte) (progress & 0xFF));

        dataOut[5] = ((byte) ((total & 0xFF000000) >> 24));
        dataOut[6] = ((byte) ((total & 0xFF0000) >> 16));
        dataOut[7] = ((byte) ((total & 0xFF00) >> 8));
        dataOut[8] = ((byte) (total & 0xFF));

        return new Message(dataOut);
    }

    public void fromBytes(ByteBuf buf)
    {
        this.data = new byte[buf.readableBytes()];
        buf.readBytes(this.data);
    }

    public void toBytes(ByteBuf buf)
    {
        buf.writeBytes(this.data);
    }

    public static class HandlerServer
            implements IMessageHandler<Message, IMessage>
    {
        public IMessage onMessage(Message message, MessageContext ctx)
        {
            byte[] data = message.data;
            if (data[0] == 3)
            {
                BlockPos pos = new BlockPos(Message.getInt(data, 1), Message.getInt(data, 5), Message.getInt(data, 9));

                int range = Message.getInt(data, 13);

                int c = 17;
                byte[] nameBytes = new byte[data.length - c];
                for (int i = 0; i < nameBytes.length; i++)
                {
                    nameBytes[i] = data[(c + i)];
                }
                String name = new String(nameBytes);

                GetBiomesCommand.processCommandServer(name, range, pos);
            }
            return null;
        }
    }

    public static class HandlerClient
            implements IMessageHandler<Message, IMessage>
    {
        public IMessage onMessage(Message message, MessageContext ctx)
        {
            byte[] data = message.data;
            if (data[0] == 1)
            {
                int size = Message.getInt(data, 1);

                int[][] biomeData = new int[size][size];

                int count = 5;
                for (int i = 0; i < size; i++)
                {
                    for (int j = 0; j < size; j++)
                    {
                        biomeData[i][j] = Message.getInt(data, count);
                        count += 4;
                    }
                }
                List<BiomeGenBase> biomes = new ArrayList();
                int numBiomes = (data.length - count) / 4;
                for (int i = 0; i < numBiomes; i++)
                {
                    int id = Message.getInt(data, count);
                    biomes.add(BiomeGenBase.getBiome(id));
                    count += 4;
                }
                ClientProxy.displayBiomeMap(biomeData, 200, biomes);
            } else if (data[0] == 2)
            {
                char[] newMessage = new char[data.length - 1];
                for (int i = 1; i < data.length; i++)
                {
                    newMessage[(i - 1)] = ((char) data[i]);
                }
                Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(Message.getString(newMessage, 0, newMessage.length)));
            } else if (data[0] == 4)
            {
                int progress = Message.getInt(data, 1);
                int total = Message.getInt(data, 5);
                if (progress == 0)
                {
                    dk.mrspring.wasteland.gui.ProgressGui.progress = 0.0F;
                    dk.mrspring.wasteland.gui.ProgressGui.visible = true;
                } else if (progress == total)
                {
                    dk.mrspring.wasteland.gui.ProgressGui.progress = 1.0F;
                    dk.mrspring.wasteland.gui.ProgressGui.visible = false;
                } else
                {
                    dk.mrspring.wasteland.gui.ProgressGui.progress = progress / total;
                }
            }
            return null;
        }
    }

    public static int getInt(byte[] data, int index)
    {
        return data[index] << 24 & 0xFF000000 | data[(index + 1)] << 16 & 0xFF0000 | data[(index + 2)] << 8 & 0xFF00 | data[(index + 3)] << 0 & 0xFF;
    }

    public static String getString(char[] data, int index, int count)
    {
        return String.valueOf(data, index, count);
    }

    private static final class MessageType
    {
        public static final int BIOMES = 1;
        public static final int CHAT_MESSAGE = 2;
        public static final int PLAYER = 3;
        public static final int GENERATION_POGRESS = 4;
    }
}
