package com.aperlab.neobs.hcl;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.Runner;
import com.aperlab.neobs.plugin.IPlugin;
import com.aperlab.neobs.plugin.Plugin;
import com.aperlab.neobs.util.Lazy;
import com.google.auto.service.AutoService;

@Plugin(ID = "hcl")
@AutoService(value = IPlugin.class)
public class HclPlugin implements IPlugin {

    static final String id = "kts";

    static NeoKey makeID(String path) { return NeoKey.ofPlugin(id+"/"+path); }

    @Override
    public void RegisterExtensions(Runner runner) {
        runner.loaderRegistry.register(makeID("hcl-loader"), Lazy.of(()->new HclLoader(runner)));
    }
}
