//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland.utils;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import dk.mrspring.wasteland.GetBiomesCommand;
import dk.mrspring.wasteland.client.ClientProxy;
import dk.mrspring.wasteland.gui.ProgressGui;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.biome.BiomeGenBase;

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
        dataOut[1] = (byte) ((size & -16777216) >> 24);
        dataOut[2] = (byte) ((size & 16711680) >> 16);
        dataOut[3] = (byte) ((size & '\uff00') >> 8);
        dataOut[4] = (byte) (size & 255);
        int count = 5;

        int i;
        int id;
        for (i = 0; i < size; ++i)
        {
            for (id = 0; id < size; ++id)
            {
                dataOut[count] = (byte) ((biomeMap[i][id] & -16777216) >> 24);
                dataOut[count + 1] = (byte) ((biomeMap[i][id] & 16711680) >> 16);
                dataOut[count + 2] = (byte) ((biomeMap[i][id] & '\uff00') >> 8);
                dataOut[count + 3] = (byte) (biomeMap[i][id] & 255);
                count += 4;
            }
        }

        for (i = 0; i < numBiomes; ++i)
        {
            id = ((BiomeGenBase) biomes.get(i)).biomeID;
            dataOut[count] = (byte) ((id & -16777216) >> 24);
            dataOut[count + 1] = (byte) ((id & 16711680) >> 16);
            dataOut[count + 2] = (byte) ((id & '\uff00') >> 8);
            dataOut[count + 3] = (byte) (id & 255);
            count += 4;
        }

        return new Message(dataOut);
    }

    public static Message createChatMessage(String string)
    {
        byte[] dataOut = new byte[string.length() + 1];
        dataOut[0] = 2;

        for (int i = 1; i < string.length() + 1; ++i)
        {
            dataOut[i] = (byte) string.charAt(i - 1);
        }

        return new Message(dataOut);
    }

    public static Message createPlayerInfoMessage(EntityPlayer player)
    {
        String playerName = player.getDisplayName();
        Vector pos = new Vector((int) player.posX, (int) player.posY, (int) player.posZ);
        byte[] dataOut = new byte[12 + playerName.length() + 1];
        dataOut[0] = 3;
        int count = 1;

        int i;
        for (i = 0; i < 3; ++i)
        {
            int p = i == 0 ? pos.X : (i == 1 ? pos.Y : pos.Z);
            dataOut[count] = (byte) ((p & -16777216) >> 24);
            dataOut[count + 1] = (byte) ((p & 16711680) >> 16);
            dataOut[count + 2] = (byte) ((p & '\uff00') >> 8);
            dataOut[count + 3] = (byte) (p & 255);
            count += 4;
        }

        for (i = 0; i < playerName.length(); ++i)
        {
            dataOut[count] = (byte) playerName.charAt(i);
            ++count;
        }

        return new Message(dataOut);
    }

    public static Message createProgressMessage(int progress, int total)
    {
        byte[] dataOut = new byte[]{(byte) 4, (byte) ((progress & -16777216) >> 24), (byte) ((progress & 16711680) >> 16), (byte) ((progress & '\uff00') >> 8), (byte) (progress & 255), (byte) ((total & -16777216) >> 24), (byte) ((total & 16711680) >> 16), (byte) ((total & '\uff00') >> 8), (byte) (total & 255)};
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

    public static int getInt(byte[] data, int index)
    {
        return data[index] << 24 & -16777216 | data[index + 1] << 16 & 16711680 | data[index + 2] << 8 & '\uff00' | data[index + 3] << 0 & 255;
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

        private MessageType()
        {
        }
    }

    public static class HandlerClient implements IMessageHandler<Message, IMessage>
    {
        public HandlerClient()
        {
        }

        public IMessage onMessage(Message message, MessageContext ctx)
        {
            byte[] data = message.data;
            int progress;
            if (data[0] == 1)
            {
                progress = Message.getInt(data, 1);
                int[][] total = new int[progress][progress];
                int count = 5;

                int numBiomes;
                for (int biomes = 0; biomes < progress; ++biomes)
                {
                    for (numBiomes = 0; numBiomes < progress; ++numBiomes)
                    {
                        total[biomes][numBiomes] = Message.getInt(data, count);
                        count += 4;
                    }
                }

                ArrayList var13 = new ArrayList();
                numBiomes = (data.length - count) / 4;

                for (int i = 0; i < numBiomes; ++i)
                {
                    int id = Message.getInt(data, count);
                    var13.add(BiomeGenBase.getBiome(id));
                    count += 4;
                }

                ClientProxy.displayBiomeMap(total, 200, var13);
            } else
            {
                int var12;
                if (data[0] == 2)
                {
                    char[] var11 = new char[data.length - 1];

                    for (var12 = 1; var12 < data.length; ++var12)
                    {
                        var11[var12 - 1] = (char) data[var12];
                    }

                    Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(String.valueOf(var11, 0, var11.length)));
                } else if (data[0] == 4)
                {
                    progress = Message.getInt(data, 1);
                    var12 = Message.getInt(data, 5);
                    if (progress == 0)
                    {
                        ProgressGui.progress = 0.0F;
                        ProgressGui.visible = true;
                    } else if (progress == var12)
                    {
                        ProgressGui.progress = 1.0F;
                        ProgressGui.visible = false;
                    } else
                    {
                        ProgressGui.progress = (float) progress / (float) var12;
                    }
                }
            }

            return null;
        }
    }

    public static class HandlerServer implements IMessageHandler<Message, IMessage>
    {
        public HandlerServer()
        {
        }

        public IMessage onMessage(Message message, MessageContext ctx)
        {
            byte[] data = message.data;
            if (data[0] == 3)
            {
                Vector pos = new Vector(Message.getInt(data, 1), Message.getInt(data, 5), Message.getInt(data, 9));
                int range = Message.getInt(data, 13);
                byte c = 17;
                byte[] nameBytes = new byte[data.length - c];

                for (int name = 0; name < nameBytes.length; ++name)
                {
                    nameBytes[name] = data[c + name];
                }

                String var9 = new String(nameBytes);
                GetBiomesCommand.processCommandServer(var9, range, pos);
            }

            return null;
        }
    }
}
