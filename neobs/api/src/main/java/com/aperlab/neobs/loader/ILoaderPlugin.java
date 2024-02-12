package com.aperlab.neobs.loader;

import java.io.File;

public interface ILoaderPlugin {

    void LoadFile(File file) throws FileLoadingException;

    boolean CanLoad(File f);
}
