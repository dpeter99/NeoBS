package com.aperlab.serialization;


/**
 *
 * @param <A>
 */
public interface Decoder<A, Ctx> {

    public <T> DataResult<? extends A> decode(FormatOps<T> ops, T input, Ctx ctx);

    public String getName();
}
