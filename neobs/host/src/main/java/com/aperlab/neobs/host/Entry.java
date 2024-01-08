package com.aperlab.neobs.host;

import com.aperlab.neobs.Runner;
import com.aperlab.neobs.model.Target;
import picocli.CommandLine;

import java.io.File;

public class Entry {

    public static void main(String[] args){
        System.out.println("NEO BS Build System V0.0.0");

        NeoBSHost tar = new NeoBSHost();
        new CommandLine(tar)
                .setUsageHelpAutoWidth(true)
                //.setAbbreviatedOptionsAllowed(true)
                .addSubcommand(new BuildCommand())
                .execute(args);


    }

}

