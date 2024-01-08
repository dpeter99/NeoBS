package com.aperlab.neobs.host;

import picocli.CommandLine;

import java.io.File;

@CommandLine.Command(name = "neobs", abbreviateSynopsis = true)
public class NeoBSHost{

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;


    @CommandLine.Option(
            names = { "-w", "--workspace" },
            paramLabel = "WORKSPACE",
            description = "The workspace folder to use",
            defaultValue = "."
    )
    File workspaceFolder = new File(".");

    @CommandLine.Option(names = { "-l", "--log-leve" }, description = "Log level")
    System.Logger.Level logLevel;



}
