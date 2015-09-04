//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dk.mrspring.wasteland;

import dk.mrspring.wasteland.city.CityGenerator;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.gui.ProgressGui;
import dk.mrspring.wasteland.items.BlockRadFluid;
import dk.mrspring.wasteland.items.ItemRegistry;
import dk.mrspring.wasteland.ruin.RuinGenHelper;
import dk.mrspring.wasteland.ruin.RuinVillageGenerator;
import dk.mrspring.wasteland.utils.Vector;
import dk.mrspring.wasteland.world.WastelandWorldData;
import dk.mrspring.wasteland.world.WorldChunkManagerWasteland;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.material.Material;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class WastelandEventHandler
{
    RuinVillageGenerator villageGeneratorHook;
    CityGenerator cityGeneratorHook;
    WastelandWorldData worldSaveData;
    boolean newSpawn;
    int spawnHeight;
    BlockPos spawnLoc;

    public WastelandEventHandler()
    {
    }

    @SubscribeEvent
    public void loadData(Load event)
    {
        if (!event.world.isRemote)
        {
            ServerCommandManager spawn = (ServerCommandManager) MinecraftServer.getServer().getCommandManager();
            GetBiomesCommand villageLocation = new GetBiomesCommand();
            GetNamesCommand cityLocation = new GetNamesCommand();
            if (!spawn.getCommands().containsKey(villageLocation.getName()))
            {
                spawn.registerCommand(villageLocation);
            }

            if (!spawn.getCommands().containsKey(cityLocation.getName()))
            {
                spawn.registerCommand(cityLocation);
            }
        }

        if (!event.world.isRemote && event.world.getWorldChunkManager().getClass().getName().equals(WorldChunkManagerWasteland.class.getName()))
        {
            if (MinecraftServer.getServer().isSinglePlayer())
            {
                this.worldSaveData.setFile("saves/" + MinecraftServer.getServer().getFolderName() + "/data/WastelandMod.dat");
            } else
            {
                this.worldSaveData.setFile(MinecraftServer.getServer().getFolderName() + "/data/WastelandMod.dat");
            }

            Vector spawn1 = new Vector(event.world.getWorldInfo().getSpawnX(), event.world.getWorldInfo().getSpawnY() + 10, event.world.getWorldInfo().getSpawnZ());
            if (!this.worldSaveData.checkIfExists())
            {
                this.worldSaveData.createFile();
                this.villageGeneratorHook.resetData();
                this.cityGeneratorHook.resetData();
                this.newSpawn = true;
            } else
            {
                List villageLocation1 = this.worldSaveData.loadVillageData();
                List cityLocation1 = this.worldSaveData.loadCityData();
                this.villageGeneratorHook.loadData(villageLocation1, villageLocation1.size());
                this.cityGeneratorHook.loadData(cityLocation1, cityLocation1.size());
                this.spawnLoc = this.worldSaveData.loadSpawnLoc();
            }

            if (this.newSpawn && ModConfig.spawnBunker)
            {
                this.spawnHeight = this.getMinWorldHeight(spawn1, 3, event.world) - 7;
                spawn1.Y = this.spawnHeight;
                RuinVillageGenerator.spawnBunker(spawn1, event.world);
                this.spawnLoc = new BlockPos(spawn1.X, spawn1.Y + 1, spawn1.Z);
                this.worldSaveData.saveSpawnLoc(this.spawnLoc);
            }
        }

    }

    private int getMinWorldHeight(Vector spawn, int rad, World world)
    {
        int min = RuinVillageGenerator.getWorldHeight(world, spawn.X, spawn.Z);
        min = min == 0 ? 100 : min;

        for (int j = 0; j < 2 * rad + 1; ++j)
        {
            for (int i = 0; i < 2 * rad + 1; ++i)
            {
                int height = RuinVillageGenerator.getWorldHeight(world, spawn.X - rad + i, spawn.Z - rad + i);
                if (height != 0)
                {
                    min = height < min ? height : min;
                }
            }
        }

        return min;
    }

    @SubscribeEvent
    public void saveData(Save event)
    {
        if (!event.world.isRemote && event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName())
        {
            this.villageGeneratorHook.saveData(this.worldSaveData);
            this.cityGeneratorHook.saveData(this.worldSaveData);
            this.newSpawn = false;
        }

    }

    @SubscribeEvent
    public void changeStartSpawn(EntityJoinWorldEvent event)
    {
        if (ModConfig.spawnBunker && event.world.getWorldChunkManager().getClass().getName() == WorldChunkManagerWasteland.class.getName() && event.entity instanceof EntityPlayer && !event.world.isRemote)
        {
//            Vector pos = new Vector((int) event.entity.posX, (int) event.entity.posY, (int) event.entity.posZ);
            EntityPlayer player = (EntityPlayer) event.entity;
            BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
            if (this.isNewPlayer(player) && pos.distanceSq(this.spawnLoc)/*Vector.VtoVlengthXZ(pos, this.spawnLoc)*/ < 16.0D)
            {
                player.setPosition(this.spawnLoc.getX(), this.spawnLoc.getY(), this.spawnLoc.getZ());
                BlockPos spawnPos = new BlockPos(this.spawnLoc.getX() - 2, this.spawnLoc.getY(), this.spawnLoc.getZ() + 1);
                player.setSpawnChunk(spawnPos, false, event.world.provider.getDimensionId());
                this.worldSaveData.savePlayerName(player.getDisplayNameString());
            }
        }

        if (event.entity instanceof EntityPlayer && event.world.isRemote)
        {
            ProgressGui.visible = false;
        }

    }

    @SubscribeEvent
    public void disableSleep(PlayerInteractEvent event)
    {
        if (event.action == Action.RIGHT_CLICK_BLOCK && ModConfig.disableSleep)
        {
            RuinGenHelper.setWorld(event.world);
            Block block = RuinGenHelper.getBlock(event.pos.getX(), event.pos.getY(), event.pos.getZ());
            if (block instanceof BlockBed && !event.world.isRemote)
            {
                BlockPos spawnPos = event.pos;
                event.entityPlayer.setSpawnChunk(spawnPos, false, event.world.provider.getDimensionId());
                event.entityPlayer.addChatMessage(new ChatComponentText("Spawn point set..."));
                event.setCanceled(true);
            }
        }

    }

    private boolean isNewPlayer(EntityPlayer player)
    {
        List loadedPlayers = this.worldSaveData.getPlayerNames();
        return loadedPlayers != null ? !loadedPlayers.contains(player.getDisplayName()) : true;
    }

    public void initialize(RuinVillageGenerator villageGen, CityGenerator cityGen, WastelandWorldData data)
    {
        this.villageGeneratorHook = villageGen;
        this.cityGeneratorHook = cityGen;
        this.worldSaveData = data;
        this.newSpawn = false;
    }

    @SubscribeEvent
    public void playerIntract(PlayerInteractEvent event)
    {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR)
        {
            ItemStack item = event.entityPlayer.getCurrentEquippedItem();
            if (item != null && item.getItem().equals(Items.glass_bottle))
            {
                double y1 = -1.0D * Math.sin((double) event.entityPlayer.rotationPitch * 3.141592653589793D / 180.0D);
                double z1 = Math.cos((double) event.entityPlayer.rotationPitch * 3.141592653589793D / 180.0D) * Math.cos((double) event.entityPlayer.rotationYaw * 3.141592653589793D / 180.0D);
                double x1 = -1.0D * Math.cos((double) event.entityPlayer.rotationPitch * 3.141592653589793D / 180.0D) * Math.sin((double) event.entityPlayer.rotationYaw * 3.141592653589793D / 180.0D);

                for (double i = 0.0D; i < 20.0D; i += 0.1D)
                {
                    int bx = (int) (event.entityPlayer.posX + x1 * i - 1.0D);
                    int by = (int) (event.entityPlayer.posY + (double) event.entityPlayer.eyeHeight + y1 * i);
                    int bz = (int) (event.entityPlayer.posZ + z1 * i);
                    RuinGenHelper.setWorld(event.world);
                    Block block = RuinGenHelper.getBlock(bx, by, bz);
                    if (block != null && block.getMaterial() == Material.water)
                    {
                        if (!block.getUnlocalizedName().equals(Blocks.water.getUnlocalizedName()))
                        {
                            event.setCanceled(true);
                            return;
                        }

                        return;
                    }
                }
            }
        }

    }

    @SubscribeEvent
    public void onItemUse(PlayerUseItemEvent event)
    {
        System.out.println(event.item.toString());
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        ItemStack result = this.fillCustomBucket(event.world, event.target);
        if (result != null)
        {
            event.result = result;
            event.setResult(Event.Result.ALLOW);
        }
    }

    private ItemStack fillCustomBucket(World world, MovingObjectPosition pos)
    {
        RuinGenHelper.setWorld(world);
        Block block = RuinGenHelper.getBlock(pos.getBlockPos().getX(), pos.getBlockPos().getY(), pos.getBlockPos().getZ());
        if (block instanceof BlockRadFluid)
        {
            world.setBlockToAir(pos.getBlockPos());
            return new ItemStack(ItemRegistry.radiationWasteBucket);
        } else
        {
            return null;
        }
    }
}
