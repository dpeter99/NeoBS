package com.aperlab.neobs.host;

import com.aperlab.neobs.FileLoadingException;
import com.aperlab.neobs.Runner;
import com.aperlab.neobs.WorkspaceNotFoundException;
import com.aperlab.neobs.model.Target;
import picocli.CommandLine;

import java.util.concurrent.Callable;

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

        Runner runner = new Runner();

        try {
            runner.openWorkspace(mainCmd.workspaceFolder);
        } catch (WorkspaceNotFoundException | FileLoadingException e) {
            return 1;
        }

        Target target = runner.findTarget(targetId);

        if(target != null) {
            target.run();
        }
        else{
            System.out.println("Can't find target");
            return 1;
        }

        return 0;
    }
}
