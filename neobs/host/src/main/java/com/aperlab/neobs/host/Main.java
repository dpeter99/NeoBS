package com.aperlab.neobs.host;

import com.aperlab.neobs.Runner;
import com.aperlab.neobs.model.Target;

import java.io.File;

public class Main {

    public static void main(String[] args){
        System.out.println("NEO BS Build System V0.0.0");

        if(args.length <= 0){
            System.out.println("Usage: neobs [OPTIONS] [TARGET]");
        }

        String targetId = args[0];

        Runner runner = new Runner();

        runner.openWorkspace(new File("."));

        Target target = runner.findTarget(targetId);

        if(target != null) {
            target.run();
        }
        else{
            System.out.println("Can't find target");
        }

        runner.PrintStructure();
    }

}
