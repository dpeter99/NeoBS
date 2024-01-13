package com.aperlab.neobs.model.api;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.model.Target;
import com.aperlab.neobs.model.Workspace;

import java.util.List;
import java.util.Set;

public interface IProjectSpec<T extends IProject> {

    void setWorkspace(Workspace w);

    NeoKey getId();

    Target getTarget(NeoKey id);

    List<Target> getTargets();
}