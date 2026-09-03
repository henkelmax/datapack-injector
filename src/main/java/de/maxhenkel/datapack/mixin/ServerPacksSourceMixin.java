package de.maxhenkel.datapack.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.maxhenkel.datapack.DatapackInjector;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerPacksSource.class)
public class ServerPacksSourceMixin {

    @ModifyArg(method = "createPackRepository(Ljava/nio/file/Path;Lnet/minecraft/world/level/validation/DirectoryValidator;)Lnet/minecraft/server/packs/repository/PackRepository;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"), index = 0)
    private static RepositorySource[] createPackRepository(RepositorySource[] repositorySources, @Local(argsOnly = true) DirectoryValidator directoryValidator) {
        return DatapackInjector.addDatapackSource(repositorySources, directoryValidator);
    }

}
