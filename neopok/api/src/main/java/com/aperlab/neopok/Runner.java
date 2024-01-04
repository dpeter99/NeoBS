package com.aperlab.neopok;

import com.aperlab.neopok.model.Workspace;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Runner {

    public ILoaderPlugin loader;
    Workspace workspace;

    public Runner() {
        //this.loader = loader;
    }

    public void OpenWorkspace(File workspace_file){
        loader.LoadFile(workspace_file);

        File dir = new File(workspace.SourceDir);

        try (Stream<Path> walkStream = Files.walk(Paths.get(workspace.SourceDir))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                if (f.toString().endsWith(".neopok.kts")) {
                    loader.LoadFile(new File(f.toString()));
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void SetWorkspace(Workspace w){
        workspace = w;
    }

    public void PrintStructure(){
        System.out.println("Structure:");
        workspace.PrintStructure();
    }

    public Workspace GetWorkspace() {
        return workspace;
    }
}
