package com.aperlab.neobs;

import java.io.File;

public interface ILoaderPlugin {

    void LoadFile(File file);

    boolean CanLoad(File f);
}
