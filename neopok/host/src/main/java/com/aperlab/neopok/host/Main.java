package com.aperlab.neopok.host;

import com.aperlab.neopok.Runner;

import java.io.File;

public class Main {

    public static void main(String[] args){
        System.out.println("Hello World!");

        Runner runner = new Runner();

        runner.loadWorkspace(new File("."));
    }

}
