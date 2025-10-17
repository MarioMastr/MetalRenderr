package com.metalrender.sodium.mixins;

import java.util.ArrayList;
import com.metalrender.MetalRenderClient;
import net.caffeinemc.mods.sodium.client.render.chunk.compile.BuilderTaskOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(
   targets = {"net.caffeinemc.mods.sodium.client.render.chunk.RenderSectionManager"}
)
public class RenderSectionManagerMixin {
   @Inject(
      method = {"processChunkBuildResults()V"},
      at = {@At("HEAD")},
      require = 0
   )
   private void metalrender$onProcessChunkBuildResults(ArrayList<BuilderTaskOutput> results, CallbackInfoReturnable<Runnable> cir) {
      if (MetalRenderClient.isEnabled() && MetalRenderClient.getWorldRenderer() != null) {
         for (var result : results) {
            MetalRenderClient.getWorldRenderer().uploadBuildResult(result);
         }
      }

   }
}
