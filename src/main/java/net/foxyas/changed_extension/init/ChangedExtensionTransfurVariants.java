package net.foxyas.changed_extension.init;

import net.foxyas.changed_extension.ChangedExtensionMod;
import net.ltxprogrammer.changed.entity.ChangedEntity;
import net.ltxprogrammer.changed.entity.variant.TransfurVariant;
import net.ltxprogrammer.changed.init.ChangedRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.function.Supplier;

public class ChangedExtensionTransfurVariants {

    public static final DeferredRegister<TransfurVariant<?>> REGISTRY = ChangedRegistry.TRANSFUR_VARIANT.createDeferred(ChangedExtensionMod.MODID);

    public static class Gendered {
    }


    private static <T extends ChangedEntity> RegistryObject<TransfurVariant<T>> register(String name, TransfurVariant.Builder<T> builder) {
        Objects.requireNonNull(builder);
        return REGISTRY.register(name, builder::build);
    }

    private static <T extends ChangedEntity> RegistryObject<TransfurVariant<T>> register(String name, Supplier<TransfurVariant.Builder<T>> builder) {
        return REGISTRY.register(name, () -> builder.get().build());
    }


}
