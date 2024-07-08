package dev.magyul.axiomkoreansupport.mixin;

import com.moulberry.axiom.tools.text.TextDrawing;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextDrawing.class)
public class TextDrawingMixin {
    @Redirect(method = "loadFont", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Identifier;of(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/util/Identifier;"))
    private static Identifier loadFont(String namespace, String path) {
        return Identifier.of(namespace, "pretendard-medium.ttf");
    }
}
