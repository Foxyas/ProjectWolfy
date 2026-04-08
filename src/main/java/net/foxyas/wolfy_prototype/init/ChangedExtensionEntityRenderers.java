package net.foxyas.wolfy_prototype.init;

import net.ltxprogrammer.changed.client.RegisterComplexRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChangedExtensionEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    }


    @SubscribeEvent
    public static void registerComplexEntityRenderers(RegisterComplexRenderersEvent event) {
    }
}
