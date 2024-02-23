package com.aperlab.neobs.hcl;

import com.aperlab.neobs.api.ILoadingContext;
import com.aperlab.neobs.api.Project;
import com.aperlab.neobs.model.Workspace;

import java.util.Optional;

public class BasicLoadingContext implements ILoadingContext {

    Project parent;
    Workspace workspace;

    public BasicLoadingContext(Project parent, Workspace workspace) {
        this.parent = parent;
        this.workspace = workspace;
    }

    public BasicLoadingContext(Workspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public Optional<Project> getParent() {
        return Optional.of(parent);
    }

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }
}
