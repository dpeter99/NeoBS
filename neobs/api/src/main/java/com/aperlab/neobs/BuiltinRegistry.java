package com.aperlab.neobs;

import com.aperlab.neobs.model.Workspace;

public class BuiltinRegistry {

    public static void register(NeoBS neoBS) {

        neoBS.codecRegistry.register(NeoKey.of("@core//workspace"), Workspace.decoder);

    }

}
