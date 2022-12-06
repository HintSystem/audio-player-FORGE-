package de.maxhenkel.audioplayer;

import de.maxhenkel.audioplayer.command.AudioPlayerCommands;
import de.maxhenkel.audioplayer.config.ServerConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.block.DispenserBlock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;

@Mod(AudioPlayer.MODID)
public class AudioPlayer {

    public static final String MODID = "audioplayer";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static ServerConfig SERVER_CONFIG;
    public static AudioCache AUDIO_CACHE;

    public AudioPlayer()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "audioplayer-server.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ModEvents.class);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        try {
            Files.createDirectories(AudioManager.getUploadFolder());
        } catch (IOException e) {
            LOGGER.warn("Failed to create upload folder", e);
        }

        ForgeRegistries.ITEMS.getValues().stream()
                .filter(item -> item instanceof RecordItem)
                .forEach(item -> DispenserBlock.registerBehavior(item, RecordDispenseBehavior.RECORD));

        AUDIO_CACHE = new AudioCache(SERVER_CONFIG.cacheSize.get());
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModEvents
    {
        @SubscribeEvent
        public static void onRegisterCommands(final RegisterCommandsEvent event) {
            AudioPlayerCommands.register(event.getDispatcher());
        }
    }
}
