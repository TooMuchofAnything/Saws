package net.tme.saws.mixin.client;

import net.minecraft.client.tutorial.CraftPlanksTutorialStepHandler;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CraftPlanksTutorialStepHandler.class)
public class SawsCraftPlanksMixin {
    @Redirect(method = "tick", at = @At(value = "FIELD", target =
            "Lnet/minecraft/client/tutorial/CraftPlanksTutorialStepHandler;ticks:I", opcode = Opcodes.GETFIELD))
    private int injected(CraftPlanksTutorialStepHandler instance) {
        return 0;
    } // very duct-tape fix!
}
