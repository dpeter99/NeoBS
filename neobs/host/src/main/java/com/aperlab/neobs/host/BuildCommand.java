package com.aperlab.neobs.host;

import com.aperlab.neobs.loader.FileLoadingException;
import com.aperlab.neobs.NeoBS;
import com.aperlab.neobs.WorkspaceNotFoundException;
import com.aperlab.neobs.model.Target;
import picocli.CommandLine;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

@CommandLine.Command(name = "build")
public class BuildCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    boolean usageHelpRequested;

    @CommandLine.Parameters(paramLabel = "TARGET")
    public String targetId;

    @CommandLine.ParentCommand
    private NeoBSHost mainCmd;

    @Override
    public Integer call() {

        NeoBS neoBS = new NeoBS();

        try {
            neoBS.openWorkspace(mainCmd.workspaceFolder);
        } catch (WorkspaceNotFoundException | FileLoadingException e) {
            return 1;
        }

        Target target = neoBS.findTarget(targetId);

        if(target == null) {
            System.out.println("Can't find " + targetId + " run ``neobs vis`` to see all available targets");
            return 1;
        }

        try {
            target.run();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
