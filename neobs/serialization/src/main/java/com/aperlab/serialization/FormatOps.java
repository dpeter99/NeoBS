package com.aperlab.serialization;

/**
 * Represents the set of functions needed to red and write  a given format
 * @param <T> The types that this writes (JsonObject for a json ops)
 */
public interface FormatOps<T> {
    T empty();

    DataResult<T> get(String key, T input);

    DataResult<String> getStringValue(T input);

    DataResult<String> getAttributeString(T input, String key);
    DataResult<Integer> getAttributeInt(T input, String key);

    DataResult<String> getLabel(T input, int i);
}
