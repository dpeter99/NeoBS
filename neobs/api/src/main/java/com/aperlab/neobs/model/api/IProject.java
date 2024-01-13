package com.aperlab.neobs.model.api;

import com.aperlab.neobs.model.ExtensionAware;

public interface IProject<T extends IProject<T>> extends ExtensionAware, IProjectSpec<T> {

    void PrintStructure();
}
