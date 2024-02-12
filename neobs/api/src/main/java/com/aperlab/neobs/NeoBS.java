package com.aperlab.neobs;

import com.aperlab.neobs.loader.FileLoadingException;
import com.aperlab.neobs.loader.ILoaderPlugin;
import com.aperlab.neobs.model.Target;
import com.aperlab.neobs.model.Workspace;
import com.aperlab.neobs.plugin.IPlugin;
import com.aperlab.neobs.plugin.PluginManager;
import com.aperlab.neobs.registry.MappedRegistry;
import com.aperlab.neobs.registry.Registry;
import com.aperlab.serialization.Decoder;
import com.aperlab.utils.Lazy;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class NeoBS {

    PluginManager pluginManager;

    @Getter
    @Setter
    Workspace workspace;

    public Registry<Lazy<ILoaderPlugin>> loaderRegistry = new Registry<>();

    public MappedRegistry<Decoder<?>, String> codecRegistry = new MappedRegistry<>(Decoder::getName);

    public NeoBS() {
        BuiltinRegistry.register(this);

        pluginManager = new PluginManager(this);
        pluginManager.LoadPlugins();
    }

    public void openWorkspace(File workspace_folder) throws WorkspaceNotFoundException, FileLoadingException {

        loadWorkspace(workspace_folder);


        try (Stream<Path> walkStream = Files.walk(workspace_folder.toPath().resolve(workspace.sourceDir))) {
            Stream<FileWithLoader> files = filterLoadableFiles(walkStream.map(Path::toFile), "");

            for (FileWithLoader file : files.toArray(FileWithLoader[]::new)) {
                System.out.println("Loading file " + file.file.getName() + " with plugin " + file.plugin.getClass().getName());
                file.plugin.LoadFile(file.file);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Target findTarget(String target) {
        NeoKey id = NeoKey.of(target);
        if(!id.isTarget()){
            //throw new Exception("ID is not a valid target ID");
            System.out.println("ID is not a valid target ID");
            return null;
        }
        Target t = workspace.findTarget(id);

        return t;
    }



    private void loadWorkspace(File workspace_folder) throws WorkspaceNotFoundException, FileLoadingException {
        File[] filesList = workspace_folder.listFiles();
        if(filesList == null) {
            System.out.println("No files found in workspace folder: " + workspace_folder.getAbsolutePath());
            throw new WorkspaceNotFoundException();
        }

        Optional<FileWithLoader> files = filterLoadableFiles(Arrays.stream(filesList), "workspace").findFirst();

        if (files.isEmpty()) {
            System.out.println("No valid workspace file found");
            throw new WorkspaceNotFoundException();
        }

        FileWithLoader file = files.get();
        System.out.println("Loading workspace file " + file.file.getName() + " with plugin " + file.plugin.getClass().getName());
        file.plugin.LoadFile(file.file);
    }



    static class FileWithLoader {

        public File file;
        public ILoaderPlugin plugin;

        public FileWithLoader(File file, ILoaderPlugin plugin) {
            this.file = file;
            this.plugin = plugin;
        }
    }

    private Stream<FileWithLoader> filterLoadableFiles(Stream<File> files, String name) {
        Set<Map.Entry<NeoKey, Lazy<ILoaderPlugin>>> loaderPlugins = loaderRegistry.getEntries();

        return files
                .filter(f -> f.isFile())
                .filter(f -> f.getName().startsWith(name))
                .flatMap(f -> loaderPlugins.stream()
                        .map(e -> e.getValue().get()) // Extract the Lazy<ILoaderPlugin> from the Map.Entry
                        .filter(plugin -> plugin.CanLoad(f))
                        .findFirst()
                        .map(plugin -> new FileWithLoader(f, plugin))
                        .stream()
                );
    }

}
