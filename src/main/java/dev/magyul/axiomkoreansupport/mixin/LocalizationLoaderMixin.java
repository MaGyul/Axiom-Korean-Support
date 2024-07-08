package dev.magyul.axiomkoreansupport.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import com.moulberry.axiom.i18n.LocalizationLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Objects;

@Mixin(LocalizationLoader.class)
public class LocalizationLoaderMixin {

    @Inject(method = "createLocalizations", at = @At(value = "INVOKE", target = "Ljava/util/SortedMap;containsKey(Ljava/lang/Object;)Z", ordinal = 0, shift = At.Shift.BEFORE), remap = false)
    private static void createLocalizations(CallbackInfoReturnable<Map<String, byte[]>> cir, @Local(ordinal = 1) LocalRef<String> localRef) {
        if (Objects.equals(localRef.get(), "ko")) {
            localRef.set("ko_kr");
        }
    }
}
