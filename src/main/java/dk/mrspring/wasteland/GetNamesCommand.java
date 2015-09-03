//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import cpw.mods.fml.common.registry.GameData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class GetNamesCommand extends CommandBase
{
    public GetNamesCommand()
    {
    }

    public String getCommandName()
    {
        return "itemName";
    }

    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return "/itemName (gives the unlocalized name of item being held)";
    }

    public void processCommand(ICommandSender iCommandSender, String[] var)
    {
        if (iCommandSender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) iCommandSender;
            ItemStack item = player.getCurrentEquippedItem();
            if (item != null)
            {
                String itemName = GameData.getItemRegistry().getNameForObject(item.getItem());
                String damage = item.getItemDamage() > 0 ? ":" + String.valueOf(item.getItemDamage()) : "";
                System.out.println(itemName + damage);
                player.addChatComponentMessage(new ChatComponentText(itemName + damage));
            }
        }

    }
}
