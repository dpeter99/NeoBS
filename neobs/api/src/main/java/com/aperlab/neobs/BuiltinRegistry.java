package com.aperlab.neobs;

import com.aperlab.neobs.model.Workspace;

public class BuiltinRegistry {

    public static void register(Runner runner) {

        runner.codecRegistry.register(NeoKey.of("@core//workspace"), Workspace.decoder);

    }

}
