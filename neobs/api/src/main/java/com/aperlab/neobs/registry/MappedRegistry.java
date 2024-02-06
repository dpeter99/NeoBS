package com.aperlab.neobs.registry;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.Registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MappedRegistry<T, K> extends Registry<T>{

    Map<K, T> idMap = new HashMap<>();

    Function<T, K> keyFunction;

    public MappedRegistry(Function<T, K> keyFunction) {
        this.keyFunction = keyFunction;
    }

    @Override
    public void register(NeoKey id, T inst) {
        super.register(id, inst);
        idMap.put(keyFunction.apply(inst), inst);
    }

    public Optional<T> get(K id){
        return Optional.ofNullable(idMap.get(id));
    }
}
