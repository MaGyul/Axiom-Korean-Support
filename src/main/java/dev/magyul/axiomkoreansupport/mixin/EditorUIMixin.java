package dev.magyul.axiomkoreansupport.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.moulberry.axiom.editor.EditorUI;
import dev.magyul.axiomkoreansupport.AxiomKoreanSupport;
import imgui.ImFontConfig;
import imgui.ImGuiIO;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EditorUI.class)
public abstract class EditorUIMixin {

    @Shadow(remap = false)
    private static byte[] loadFont(String name) {
        throw new AssertionError();
    }

    @Redirect(method = "initFonts", at = @At(value = "INVOKE", target = "Lcom/moulberry/axiom/editor/EditorUI;loadFont(Ljava/lang/String;)[B", ordinal = 0), remap = false)
    private static byte[] loadFontRedirect(String name, String languageCode) {
        return loadFont("pretendard-medium.ttf");
    }

    @Inject(method = "initFonts", at = @At(value = "INVOKE", target = "Limgui/ImFontConfig;setMergeMode(Z)V", ordinal = 1, shift = At.Shift.BEFORE), remap = false)
    private static void initFonts(String languageCode, CallbackInfo ci, @Local(ordinal = 0) ImGuiIO io, @Local(ordinal = 0) int size, @Local(ordinal = 0) ImFontConfig fontConfig) {
        if (languageCode.startsWith("ko")) {
            io.getFonts().addFontFromMemoryTTF(loadFont("pretendard-medium.ttf"), (float)size, fontConfig, io.getFonts().getGlyphRangesKorean());

            AxiomKoreanSupport.LOGGER.info("Axiom ImGui ko_kr font loaded");
        }
    }
}
