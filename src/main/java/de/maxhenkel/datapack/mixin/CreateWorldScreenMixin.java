package de.maxhenkel.datapack.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.maxhenkel.datapack.DatapackInjector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.repository.RepositorySource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {

    @ModifyArg(method = "openCreateWorldScreen", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"), index = 0)
    private static RepositorySource[] openCreateWorldScreen(RepositorySource[] repositorySources, @Local(argsOnly = true) Minecraft minecraft) {
        return DatapackInjector.addDatapackSource(repositorySources, minecraft.directoryValidator());
    }

}
