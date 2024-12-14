package de.maxhenkel.datapack.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.*;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.nio.file.Path;

@Mixin(ServerPacksSource.class)
public class ServerPacksSourceMixin {

    @ModifyArg(method = "createPackRepository(Ljava/nio/file/Path;Lnet/minecraft/world/level/validation/DirectoryValidator;)Lnet/minecraft/server/packs/repository/PackRepository;", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"), index = 0)
    private static RepositorySource[] createPackRepository(RepositorySource[] repositorySources, @Local(argsOnly = true) DirectoryValidator directoryValidator) {
        RepositorySource[] sources = new RepositorySource[repositorySources.length + 1];
        System.arraycopy(repositorySources, 0, sources, 0, repositorySources.length);
        sources[repositorySources.length] = new FolderRepositorySource(Path.of("datapacks"), PackType.SERVER_DATA, PackSource.WORLD, directoryValidator);
        return sources;
    }

}
