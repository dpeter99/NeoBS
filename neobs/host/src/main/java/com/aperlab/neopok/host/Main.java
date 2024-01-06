package com.aperlab.neobs.host;

import com.aperlab.neobs.Runner;

import java.io.File;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello World!");

        Runner runner = new Runner();

        runner.openWorkspace(new File("."));

        runner.PrintStructure();
    }

}
