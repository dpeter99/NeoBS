package com.aperlab.neobs.actions;

import com.aperlab.neobs.api.Action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CmdAction implements Action {

    public String executable = "/bin/sh -c";

    public String command;

    public File workingDir;

    public boolean inheritPath = true;
    public boolean inheritEnv = true;

    public CmdAction(String command, File cwd) {
        this.command = command;
        this.workingDir = cwd;
    }

    @Override
    public CompletableFuture<Boolean> execute() {
        System.out.println("Executing: " + command);

        ProcessBuilder builder = new ProcessBuilder();

        //Command build
        List<String> parts = new ArrayList<>(Arrays.stream(executable.split(" ")).toList());
        parts.add(command);

        builder.command(parts.toArray(new String[]{}));

        //Working Dir
        builder.directory(workingDir);

        //Environment setting
        if(inheritEnv){
            builder.environment().putAll(System.getenv());
        }
        else if(inheritPath){
            builder.environment().put("PATH", System.getenv().get("PATH"));
        }
        //builder.environment().put("PATH", "/home/dpeter99/.nvm/versions/node/v18.16.1/bin/");


        builder.inheritIO();



        try {
            return builder.start()
                    .onExit()
                    .thenApply(process -> process.exitValue() == 0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
