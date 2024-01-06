package com.aperlab.neopok.actions;

import com.aperlab.neopok.model.Action;

import java.io.IOException;

public class CmdAction implements Action {

    public String Command;

    @Override
    public void execute() {

        try {
            new ProcessBuilder()
                    .command(Command)
                    .inheritIO()
                    .start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
