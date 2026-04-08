package net.foxyas.changed_extension.init;

import com.mojang.datafixers.util.Pair;
import net.foxyas.changed_extension.ChangedExtensionMod;
import net.foxyas.changedaddon.ChangedAddonMod;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraftforge.data.loading.DatagenModLoader;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChangedExtensionEntities {

    // ========================================================== Datagen ========================================================== //
    /**
     * Todo: Make this Class Use HashMaps to make it registration more dynamic
     * example -> ATTRIBUTES.put(PROTOTYPE, PrototypeEntity::createAttributes);
     */

    public static @NotNull List<EntityType<?>> canUseAccessories() {
        //final List<EntityType<?>> ADDON_CHANGED_ENTITIES = getAddonHumanoidsChangedEntities();
        //ADDON_CHANGED_ENTITIES.remove(REYN.get());
        //ADDON_CHANGED_ENTITIES.remove(BUNY.get());
        return getAddonHumanoidsChangedEntities();
    }

    public static @NotNull List<EntityType<?>> canUseExoskeleton() {
        final List<EntityType<?>> ADDON_CHANGED_ENTITIES = getAddonHumanoidsChangedEntities();
        return ADDON_CHANGED_ENTITIES;
    }

    @Contract(" -> new")
    public static @NotNull List<EntityType<?>> getAddonHumanoidsChangedEntities() {
        ArrayList<EntityType<?>> entityTypes = new ArrayList<>();
        LatexEntitiesThatCanUseAccessories.stream().map(Supplier::get)
                .sorted(Comparator.comparing(entityType -> ForgeRegistries.ENTITY_TYPES.getKey(entityType).getPath()))
                .forEach(entityTypes::add);
        return entityTypes;
    }

    public static final List<Supplier<EntityType<?>>> LatexEntities;
    public static final List<Supplier<EntityType<?>>> LatexEntitiesThatCanUseAccessories;
    public static final List<Pair<Supplier<EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>>> EntitiesWithDefaultAttributes = new ArrayList<>();
    public static final List<Pair<Supplier<EntityType<?>>, Supplier<LootTable.Builder>>> EntitiesWithLoot;

    static {
        if(DatagenModLoader.isRunningDataGen()){
            LatexEntities = new ArrayList<>();
            LatexEntitiesThatCanUseAccessories = new ArrayList<>();
            EntitiesWithLoot = new ArrayList<>();
        } else {
            LatexEntities = null;
            LatexEntitiesThatCanUseAccessories = null;
            EntitiesWithLoot = null;
        }
    }
    // ========================================================== /Datagen ========================================================= //

    //Todo: Make this Class a bit less Chaotic
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChangedExtensionMod.MODID);

    /// Latex Changed Entity Registers
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntity(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return registerChangedEntity(registryName, entityTypeBuilder, true, true);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntity(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories) {
        return registerChangedEntity(registryName, entityTypeBuilder, true, canUseAccessories);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithLoot(String registryName, EntityType.Builder<T> entityTypeBuilder, Supplier<LootTable.Builder> lootDataBuilder) {
        return registerChangedEntityWithLoot(registryName, entityTypeBuilder, true, true, lootDataBuilder);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithLoot(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, Supplier<LootTable.Builder> lootDataBuilder) {
        return registerChangedEntityWithLoot(registryName, entityTypeBuilder, true, canUseAccessories, lootDataBuilder);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithAttributes(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, @Nullable Supplier<AttributeSupplier.Builder> builderSupplier) {
        return registerChangedEntityWithLootAndAttributes(registryName, entityTypeBuilder, true, canUseAccessories, null, builderSupplier);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithLootAndAttributes(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, Supplier<LootTable.Builder> lootDataBuilder, @Nullable Supplier<AttributeSupplier.Builder> builderSupplier) {
        return registerChangedEntityWithLootAndAttributes(registryName, entityTypeBuilder, true, canUseAccessories, lootDataBuilder, builderSupplier);
    }

    /// Non Latex Changed Entity Registers
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntity(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return registerChangedEntity(registryName, entityTypeBuilder, false, true);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntity(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories) {
        return registerChangedEntity(registryName, entityTypeBuilder, false, canUseAccessories);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntityWithLoot(String registryName, EntityType.Builder<T> entityTypeBuilder, Supplier<LootTable.Builder> lootDataBuilder) {
        return registerChangedEntityWithLoot(registryName, entityTypeBuilder, false, true, lootDataBuilder);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntityWithLoot(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, Supplier<LootTable.Builder> lootDataBuilder) {
        return registerChangedEntityWithLoot(registryName, entityTypeBuilder, false, canUseAccessories, lootDataBuilder);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntityWithAttributes(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, @Nullable Supplier<AttributeSupplier.Builder> builderSupplier) {
        return registerChangedEntityWithLootAndAttributes(registryName, entityTypeBuilder, false, canUseAccessories, null, builderSupplier);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerOrganicChangedEntityWithLootAndAttributes(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean canUseAccessories, Supplier<LootTable.Builder> lootDataBuilder, @Nullable Supplier<AttributeSupplier.Builder> builderSupplier) {
        return registerChangedEntityWithLootAndAttributes(registryName, entityTypeBuilder, false, canUseAccessories, lootDataBuilder, builderSupplier);
    }

    /// Generic/Manual Changed Entity Registers
    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntity(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean latex, boolean canUseAccessories) {
        return registerChangedEntityWithLoot(registryName, entityTypeBuilder, latex, canUseAccessories, null);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithLoot(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean latex, boolean canUseAccessories, @Nullable Supplier<LootTable.Builder> lootDataBuilder) {
        return registerChangedEntityWithLootAndAttributes(registryName, entityTypeBuilder, latex, canUseAccessories, lootDataBuilder, null);
    }

    private static <T extends LivingEntity> RegistryObject<EntityType<T>> registerChangedEntityWithLootAndAttributes(String registryName, EntityType.Builder<T> entityTypeBuilder, boolean latex, boolean canUseAccessories, @Nullable Supplier<LootTable.Builder> lootDataBuilder, @Nullable Supplier<AttributeSupplier.Builder> builderSupplier) {
        RegistryObject<EntityType<T>> register = REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
        if (builderSupplier != null) {
            EntitiesWithDefaultAttributes.add(Pair.of(register::get, builderSupplier));
        }
        if(DatagenModLoader.isRunningDataGen()){
            if (latex) LatexEntities.add(register::get);
            if (canUseAccessories) LatexEntitiesThatCanUseAccessories.add(register::get);
            if (lootDataBuilder != null) EntitiesWithLoot.add(Pair.of(register::get, lootDataBuilder));
        }
        return register;
    }

    // Basic Registers
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> registerMob(String registryName, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryName, () -> entityTypeBuilder.build(registryName));
    }

    @SubscribeEvent
    public static void registerAttributes(@NotNull EntityAttributeCreationEvent event) {
        //event.put(X,Y);
    }

    @SubscribeEvent
    public static void init(@NotNull FMLCommonSetupEvent event) {
        //event.enqueueWork(Y::init);
    }

}
