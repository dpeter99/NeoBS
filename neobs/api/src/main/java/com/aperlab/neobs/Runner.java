package com.aperlab.neobs;

import com.aperlab.neobs.model.Target;
import com.aperlab.neobs.model.Workspace;
import com.aperlab.neobs.plugin.IPlugin;
import com.aperlab.neobs.util.Lazy;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Runner {
    Workspace workspace;

    public Registry<Lazy<ILoaderPlugin>> loaderRegistry = new Registry<>();

    public Runner() {
        ServiceLoader<IPlugin> loader = ServiceLoader.load(IPlugin.class);

        for (IPlugin plugin : loader) {
            System.out.println("Found plugin: " + plugin.getClass().getName());
        }

        loader.forEach(p -> p.RegisterExtensions(this));
    }

    public void openWorkspace(File workspace_folder) throws WorkspaceNotFoundException {
        loadWorkspace(workspace_folder);

        try (Stream<Path> walkStream = Files.walk(Paths.get(workspace.SourceDir))) {
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
        ID id = ID.of(target);
        if(!id.isTarget()){
            //throw new Exception("ID is not a valid target ID");
            System.out.println("ID is not a valid target ID");
            return null;
        }
        Target t = workspace.findTarget(id);

        return t;
    }



    private void loadWorkspace(File workspace_folder) throws WorkspaceNotFoundException {
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
        Set<Map.Entry<ID, Lazy<ILoaderPlugin>>> loaderPlugins = loaderRegistry.getEntries();

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

    public void SetWorkspace(Workspace w) {
        workspace = w;
    }

    public void PrintStructure() {
        System.out.println("Structure:");
        workspace.PrintStructure();
    }

    public Workspace GetWorkspace() {
        return workspace;
    }
}
