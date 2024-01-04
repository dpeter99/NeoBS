package com.aperlab.neopok.model;

@SamWithReceiver
public interface Action<T> {

    void execute(T subject);
}