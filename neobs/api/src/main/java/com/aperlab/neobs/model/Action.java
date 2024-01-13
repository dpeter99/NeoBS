package com.aperlab.neobs.model;

import java.util.concurrent.CompletableFuture;

@SamWithReceiver
public interface Action {

    CompletableFuture<Boolean> execute();

}