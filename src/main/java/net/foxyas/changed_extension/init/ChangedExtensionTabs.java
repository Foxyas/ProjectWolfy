package net.foxyas.changed_extension.init;

import net.foxyas.changedaddon.ChangedAddonMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ChangedExtensionTabs {

    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ChangedAddonMod.MODID);

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = REGISTRY.register("main_tab", () -> CreativeModeTab.builder()
            //.icon(() -> ChangedExtensionItems.LATEX_KAYLA_SHARK_SPAWN_EGG.get().getDefaultInstance())
            .icon(Items.STICK::getDefaultInstance)
            .title(Component.translatable("itemGroup.changed_extension.main_tab"))
            .displayItems((params, items) -> {
                for (RegistryObject<Item> itemRegistryObject : ChangedExtensionItems.REGISTRY.getEntries()) {
                    items.accept(itemRegistryObject.get().getDefaultInstance());
                }

            }).build());
}
