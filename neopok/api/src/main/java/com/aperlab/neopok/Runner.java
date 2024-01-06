package com.aperlab.neopok;

import com.aperlab.neopok.model.Workspace;
import com.aperlab.neopok.plugin.IPlugin;
import com.aperlab.neopok.util.Lazy;

import java.io.File;
import java.util.*;

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

    public void loadWorkspace(File workspace_folder) {
        Set<Map.Entry<ID, Lazy<ILoaderPlugin>>> accepted_extensions = loaderRegistry.getEntries();

        File[] filesList = workspace_folder.listFiles();
        if(filesList == null) {
            System.out.println("No files found in workspace folder: " + workspace_folder.getAbsolutePath());
            return;
        }

        // If records are unavailable, a local class will suffice
        record FileWithLoader(File file, ILoaderPlugin plugin) {}

        Optional<FileWithLoader> files = Arrays.stream(filesList)
                .filter(f -> f.isFile())
                .filter(f -> f.getName().startsWith("workspace"))
                .flatMap(f -> accepted_extensions.stream()
                        .map(e -> e.getValue().get()) // Extract the Lazy<ILoaderPlugin> from the Map.Entry
                        .filter(plugin -> plugin.CanLoad(f))
                        .findFirst()
                        .map(plugin -> new FileWithLoader(f, plugin))
                        .stream()
                )
                .findFirst();

        if (files.isEmpty()) {
            System.out.println("No valid workspace file found");
            return;
        }

        FileWithLoader file = files.get();
        System.out.println("Loading workspace file " + file.file.getName() + " with plugin " + file.plugin.getClass().getName());
        file.plugin.LoadFile(file.file);
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
