package com.aperlab.serialization;


/**
 *
 * @param <A>
 */
public interface Decoder<A> {

    public <T> DataResult<? extends A> decode(FormatOps<T> ops, T input);

    public String getName();
}
