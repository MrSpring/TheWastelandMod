//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class GetNamesCommand extends CommandBase
{
    public GetNamesCommand()
    {
    }

    @Override
    public String getName()
    {
        return "itemName";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender)
    {
        return "/itemName (gives the unlocalized name of item being held)";
    }

    @Override
    public void execute(ICommandSender iCommandSender, String[] var)
    {
        if (iCommandSender instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer) iCommandSender;
            ItemStack item = player.getCurrentEquippedItem();
            if (item != null)
            {
                String itemName = Item.itemRegistry.getNameForObject(item.getItem()).toString();
                String damage = item.getItemDamage() > 0 ? ":" + String.valueOf(item.getItemDamage()) : "";
                System.out.println(itemName + damage);
                player.addChatComponentMessage(new ChatComponentText(itemName + damage));
            }
        }

    }
}
