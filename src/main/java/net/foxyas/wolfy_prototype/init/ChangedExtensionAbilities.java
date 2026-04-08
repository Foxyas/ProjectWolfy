package net.foxyas.wolfy_prototype.init;

import net.foxyas.wolfy_prototype.ChangedExtensionMod;
import net.ltxprogrammer.changed.ability.AbstractAbility;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;

import static net.ltxprogrammer.changed.init.ChangedRegistry.ABILITY;

@Mod.EventBusSubscriber(modid = ChangedExtensionMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedExtensionAbilities /*extends ChangedAbilities*/ {

    public static final DeferredRegister<AbstractAbility<?>> REGISTRY = ABILITY.createDeferred(ChangedExtensionMod.MODID);

    public static void addUniversalAbilities(TransfurVariant.UniversalAbilitiesEvent event) {
    }

   /* @SubscribeEvent
    public static void registerAbilities(FMLConstructModEvent event) {
        ChangedPartialKitsuneAbilities.REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
    }*/

    @SubscribeEvent
    public static void clientLoad(FMLClientSetupEvent event) {
    }

}