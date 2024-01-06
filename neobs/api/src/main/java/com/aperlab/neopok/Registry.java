package com.aperlab.neobs;

import com.aperlab.neobs.plugin.IPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry<T> {

    private final Map<ID, T> idMap = new HashMap<>();

    public void register(ID id, T inst){
        idMap.put(id, inst);
    }

    public T get(ID id){
        return idMap.get(id);
    }

    public Set<ID> getKeys(){
        return Collections.unmodifiableSet(idMap.keySet());
    }

    public Set<Map.Entry<ID, T>> getEntries(){
        return Collections.unmodifiableSet(idMap.entrySet());
    }
}
