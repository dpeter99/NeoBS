package com.aperlab.neobs.model.api;

import com.aperlab.neobs.model.Target;
import org.jetbrains.annotations.NotNull;

public interface IProjectBuilder<T extends IProject<T>> extends IProjectSpec<T>{

    void setName(String name);

    T build();

    void addTarget(@NotNull Target t);
}
