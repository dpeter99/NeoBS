package com.aperlab.neobs;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Registry<T> {

    private final Map<NeoKey, T> idMap = new HashMap<>();

    public void register(NeoKey id, T inst){
        idMap.put(id, inst);
    }

    public T get(NeoKey id){
        return idMap.get(id);
    }

    public Set<NeoKey> getKeys(){
        return Collections.unmodifiableSet(idMap.keySet());
    }

    public Set<Map.Entry<NeoKey, T>> getEntries(){
        return Collections.unmodifiableSet(idMap.entrySet());
    }
}
