package com.aperlab.neobs.plugin;

import com.aperlab.neobs.NeoBS;

import java.util.ServiceLoader;

public class PluginManager {

    NeoBS neobs;

    public PluginManager(NeoBS neobs) {
        this.neobs = neobs;
    }

    public void LoadPlugins(){
        ServiceLoader<IPlugin> loader = ServiceLoader.load(IPlugin.class);

        for (IPlugin plugin : loader) {
            System.out.println("Found plugin: " + plugin.getClass().getName());
        }

        loader.forEach(p -> p.RegisterExtensions(neobs));
    }

}
