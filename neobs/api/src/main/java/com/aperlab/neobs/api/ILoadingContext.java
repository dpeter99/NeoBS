package com.aperlab.neobs.api;

import com.aperlab.neobs.model.Workspace;

import java.util.Optional;

public interface ILoadingContext {

    Optional<Project> getParent();

    Workspace getWorkspace();

}
