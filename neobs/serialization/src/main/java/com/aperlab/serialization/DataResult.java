package com.aperlab.serialization;

import java.util.Optional;
import java.util.function.Function;

/**
 * Represents the result of a data operation
 * Can be either a value or an error
 * @param <A> The type of the value
 */
public class DataResult<A> {

    Optional<A> value;
    String error;

    public DataResult(Optional<A> value, String error) {
        this.value = value;
        this.error = error;
    }

    public static <A> DataResult<A> create(Optional<A> value, String error){
        return new DataResult<>(value, error);
    }

    public static <A> DataResult<A> ofSuccess(A value){
        return new DataResult<>(Optional.of(value), null);
    }

    public static <A> DataResult<A> ofError(String error){
        return new DataResult<>(Optional.empty(), error);
    }


    public Optional<A> getValue() {
        return value;
    }

    public A get() {
        return value.get();
    }


    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return value.isPresent();
    }

    public boolean isError() {
        return !value.isPresent();
    }

    public A getOrElse(A defaultValue) {
        return value.orElse(defaultValue);
    }

    public <T> DataResult<T> map(final Function<? super A, ? extends T> function) {
        return create(
                value.map(function),
                error
        );
    }

    public A orElse(A s) {
        return value.orElse(s);
    }
}
