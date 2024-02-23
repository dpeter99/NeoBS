package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.api.Project;
import com.aperlab.neobs.api.Step;
import com.aperlab.neobs.api.Target;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public class DefaultTarget implements Target {

    Project project;

    NeoKey Id;

    public DefaultTarget(Project project, NeoKey id) {
        this.project = project;
        Id = id;
    }

    @Override
    public NeoKey getId() {
        return this.Id;
    }

    @Override
    public Project getProject() {
        return null;
    }

    @Override
    public Step step(NeoKey name) {
        return null;
    }

    @Override
    public Set<Step> getSteps() {
        return null;
    }

    @Override
    public void run() throws InterruptedException, ExecutionException {

    }

    @Override
    public void PrintStructure() {

    }
}
