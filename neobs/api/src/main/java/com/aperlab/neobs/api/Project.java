package com.aperlab.neobs.api;

import java.util.Collection;
import java.util.Optional;

public interface Project
        extends IPrintableStructure, IHasId {

    Target target(String name);

    Collection<Target> getAllTargets();

    Optional<Target> getTargetByName(String name);


}
