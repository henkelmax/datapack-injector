package de.maxhenkel.datapack;

import net.fabricmc.api.ModInitializer;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.FolderRepositorySource;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;

public class DatapackInjector implements ModInitializer {

    public static final String MODID = "datapack_injector";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final Path DATAPACK_FOLDER = Path.of("datapacks");

    @Override
    public void onInitialize() {

    }

    public static RepositorySource[] addDatapackSource(RepositorySource[] sources, DirectoryValidator directoryValidator) {
        RepositorySource[] newSources = Arrays.copyOf(sources, sources.length + 1);
        newSources[sources.length] = new FolderRepositorySource(DATAPACK_FOLDER, PackType.SERVER_DATA, PackSource.WORLD, directoryValidator);
        return newSources;
    }

}
