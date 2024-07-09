package dev.magyul.axiomkoreansupport.mixin;

import com.moulberry.axiom.tools.text.TextDrawing;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(TextDrawing.class)
public class TextDrawingMixin {
    @Redirect(method = "loadFont", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getResource(Lnet/minecraft/util/Identifier;)Ljava/util/Optional;"))
    private static Optional<Resource> loadFont(ResourceManager manager, Identifier identifier) {
        return manager.getResource(identifier.withPath("pretendard-medium.ttf"));
    }
}
