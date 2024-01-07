package com.aperlab.neobs.actions;

import com.aperlab.neobs.model.Action;

import java.io.IOException;

public class CmdAction implements Action {

    public String Command;

    @Override
    public void execute() {

        try {
            new ProcessBuilder()
                    .command("sh", "-c", Command)
                    .inheritIO()
                    .start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
