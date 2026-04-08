package net.foxyas.wolfy_prototype.mixins;

import com.mojang.authlib.GameProfile;
import net.foxyas.changedaddon.entity.simple.WolfyEntity;
import net.foxyas.wolfy_prototype.annotations.ConditionalMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.common.extensions.IForgePlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
//@ConditionalMixin(value = {"modid", "modid2"}) // This define when the mixin will be applied. each mod id need to be loaded in game so this mixin get applied.
//@ConditionalMixin(value = {"!modid", "!modid2"}) // This define when the mixin will be applied. !means "not loaded". each mod id need to be NOT loaded in game for this mixin to be applied.
@ConditionalMixin("changed")
public abstract class ExampleMixin extends LivingEntity { //Alt + enter here to get the "auto put mixin in common option"

    private boolean VERY_INPORTANT_BOOL = false;
    private boolean VERY_INPORTANT_BOOL2 = false;

    protected ExampleMixin(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        // DON'T PUT STUFF HERE. THIS IS NEVER CALLED.
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void initializationOfInstanceHook(Level pLevel, BlockPos pPos, float pYRot, GameProfile pGameProfile, CallbackInfo ci) {
        // THIS IS WHERE YOU NEED TO PUT STUFF FOR the INITIALIZATION EX:
        VERY_INPORTANT_BOOL = pLevel.dimensionType() == pLevel.registryAccess()
                .lookup(Registries.DIMENSION_TYPE)
                .orElseThrow().get(BuiltinDimensionTypes.OVERWORLD)
                .orElseThrow().get();

        VERY_INPORTANT_BOOL2 = pLevel.getLevelData().isHardcore();
    }

    // Any mixin file in wolfy_prototype.mixins.json will ALWAYS be loaded. not matter if they have the @ConditionalMixin.
    // Any mixin file in wolfy_prototype.compatibility.mixins.json will ALWAYS check if they have the @ConditionalMixin before applying them.

    @Shadow public abstract void displayClientMessage(Component pChatComponent, boolean pActionBar);

    @Inject(method = "touch", at = @At("TAIL")) // On the last line of the normal function "touch" this will ALWAYS be injected
    //  PRIVATE IF IS A HOOK FOR SOMETHING
    private void sayTouchHook(Entity pEntity, CallbackInfo ci) {
        if (pEntity instanceof WolfyEntity) {
            this.displayClientMessage(Component.literal("THIS PLAYER TOUCH WOLFY, TRIGGER HAPPEN BECAUSE MOD Changed WAS LOADED"), false);
        }
    }
}
