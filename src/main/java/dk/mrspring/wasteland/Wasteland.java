package dk.mrspring.wasteland;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dk.mrspring.wasteland.GuiEventHandler;
import dk.mrspring.wasteland.PostModWorldGenerator;
import dk.mrspring.wasteland.WastelandEventHandler;
import dk.mrspring.wasteland.city.CityGenerator;
import dk.mrspring.wasteland.config.CityLootConfig;
import dk.mrspring.wasteland.config.EntitySpawnConfig;
import dk.mrspring.wasteland.config.ModConfig;
import dk.mrspring.wasteland.config.RuinConfig;
import dk.mrspring.wasteland.entity.EntityDayZombie;
import dk.mrspring.wasteland.entity.RenderDayZombie;
import dk.mrspring.wasteland.items.ItemRegistry;
import dk.mrspring.wasteland.ruin.RuinVillageGenerator;
import dk.mrspring.wasteland.utils.Message;
import dk.mrspring.wasteland.world.WastelandWorldData;
import dk.mrspring.wasteland.world.WorldTypeWasteland;
import dk.mrspring.wasteland.world.biome.BiomeGenWastelandBase;
import dk.mrspring.wasteland.world.gen.WastelandGeneratorInfo;
import java.io.File;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(
   modid = "WLM",
   name = "The Wasteland Mod",
   version = "1.4.3",
   useMetadata = true
)
public class Wasteland {

   public static WorldType wastelandWorldType;
   public static RuinVillageGenerator villageGenerator;
   public static CityGenerator cityGenerator;
   public static SimpleNetworkWrapper NETWORK;
   public static int lastID = 0;
   @Instance("WLM")
   public static Wasteland instance;
   public static WastelandEventHandler eventHandler;
   public static WastelandWorldData worldData = new WastelandWorldData();
   public static ItemRegistry items;


   @EventHandler
   public static void preInit(FMLPreInitializationEvent event) {
      NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("WLM".toLowerCase());
      NETWORK.registerMessage(Message.HandlerClient.class, Message.class, 0, Side.CLIENT);
      NETWORK.registerMessage(Message.HandlerServer.class, Message.class, 0, Side.SERVER);
      Configuration config = new Configuration(new File("config/Wasteland/TerrainGen.cfg"));
      Configuration ruinConfig = new Configuration(new File("config/Wasteland/ChestLoot.cfg"));
      Configuration cityConfig = new Configuration(new File("config/Wasteland/CityLoot.cfg"));
      ModConfig.load(config);
      RuinConfig.load(ruinConfig);
      CityLootConfig.load(cityConfig);
      int id = EntityRegistry.findGlobalUniqueEntityId();
      EntityRegistry.registerGlobalEntityID(EntityDayZombie.class, "Day Zombie", id);
      EntityRegistry.registerModEntity(EntityDayZombie.class, "Day Zombie", id, instance, 128, 1, true);
      items = new ItemRegistry();
      if(event.getSide().isClient()) {
         registerRendering();
         MinecraftForge.EVENT_BUS.register(new GuiEventHandler());
      }

      villageGenerator = new RuinVillageGenerator();
      cityGenerator = new CityGenerator();
      eventHandler = new WastelandEventHandler();
      MinecraftForge.EVENT_BUS.register(eventHandler);
      eventHandler.initialize(villageGenerator, cityGenerator, worldData);
   }

   @EventHandler
   public static void init(FMLInitializationEvent event) {
      wastelandWorldType = new WorldTypeWasteland("wasteland");
      WastelandGeneratorInfo var10000 = WorldTypeWasteland.genInfo;
      WastelandGeneratorInfo.createDefault();
   }

   @EventHandler
   public static void postInit(FMLPostInitializationEvent event) {
      Configuration spawnConfig = new Configuration(new File("config/Wasteland/CreatureSpawns.cfg"));
      EntitySpawnConfig.load(spawnConfig);
      BiomeGenWastelandBase.load();
      GameRegistry.registerWorldGenerator((new PostModWorldGenerator()).toIWorldGenerator(), 10);
   }

   @SideOnly(Side.CLIENT)
   private static void registerRendering() {
      RenderingRegistry.registerEntityRenderingHandler(EntityDayZombie.class, new RenderDayZombie());
   }

}
