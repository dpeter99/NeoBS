package com.aperlab.neobs.api;

/**
 *
 * @param <T> The type that the plugins will be applied to
 */
public interface PluginAware<T> {

    //PluginContainer getPlugins();

    void apply(Object plugin);

}
