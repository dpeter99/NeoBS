package com.aperlab.neopok.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface Lazy<T> extends Supplier<T> {

    static <T> Lazy<T> of(@NotNull Supplier<T> supplier){
        return new Fast<>(supplier);
    }


    final class Fast<T> implements Lazy<T> {
        private Supplier<T> supplier;
        private T instance;

        private Fast(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Nullable
        @Override
        public final T get() {
            if (supplier != null) {
                instance = supplier.get();
                supplier = null;
            }
            return instance;
        }

        public T getOr(T or){
            T v = get();
            if(v == null){
                return null;
            }
            return v;
        }
    }
}
