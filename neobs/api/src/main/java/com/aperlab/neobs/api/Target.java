package com.aperlab.neobs.api;

import com.aperlab.neobs.NeoKey;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface Target extends IPrintableStructure, IHasId {

    Project getProject();

    Step step(NeoKey name);

    Set<Step> getSteps();


    void run() throws InterruptedException, ExecutionException;
}
