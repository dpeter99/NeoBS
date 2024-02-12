package com.aperlab.neobs.host;

import picocli.CommandLine;

public class Entry {

    public static void main(String[] args){
        System.out.println("NEO BS Build System V0.0.0");

        NeoBSHost tar = new NeoBSHost();
        new CommandLine(tar)
                .setUsageHelpAutoWidth(true)
                //.setAbbreviatedOptionsAllowed(true)
                .addSubcommand(new BuildCommand())
                .addSubcommand(new VisualizeCommand())
                .execute(args);

    }

}

