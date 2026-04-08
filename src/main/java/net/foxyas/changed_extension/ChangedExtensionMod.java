package net.foxyas.changed_extension;

import net.foxyas.changed_extension.init.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.IEventBusInvokeDispatcher;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.IModBusEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChangedExtensionMod.MODID)
public class ChangedExtensionMod {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogManager.getLogger(ChangedExtensionMod.class);
    public static final String MODID = "changed_extension";
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(resourceLoc(MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    private static int messageID = 0;


    public ChangedExtensionMod(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ChangedExtensionTabs.REGISTRY.register(modEventBus);
        ChangedExtensionTransfurVariants.REGISTRY.register(modEventBus);
        ChangedExtensionEntities.REGISTRY.register(modEventBus);
        ChangedExtensionAbilities.REGISTRY.register(modEventBus);
        ChangedExtensionItems.REGISTRY.register(modEventBus);
        ChangedExtensionBlocks.REGISTRY.register(modEventBus);
    }

    //Thanks :D
    public static ResourceLocation resourceLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    public static String resourceLocString(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path).toString();
    }

    public static ResourceLocation textureLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path + ".png");
    }

    public static ModelLayerLocation layerLocation(String path, String layer) {
        return new ModelLayerLocation(resourceLoc(path), layer);
    }

    public static <T extends Event> boolean postEvent(T event) {
        return MinecraftForge.EVENT_BUS.post(event);
    }

    public static <T extends Event> boolean postEvent(T event, IEventBusInvokeDispatcher dispatcher) {
        return MinecraftForge.EVENT_BUS.post(event, dispatcher);
    }

    public static <T extends Event & IModBusEvent> void postModLoadingEvent(T event) {
        ModLoader.get().postEvent(event);
    }

    public static <T extends Event> void addEventListener(Consumer<T> listener) {
        MinecraftForge.EVENT_BUS.addListener(listener);
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
        messageID++;
    }

    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer, NetworkDirection direction) {
        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer, Optional.of(direction));
        messageID++;
    }
}
