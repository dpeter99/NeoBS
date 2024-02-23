package com.aperlab.neobs.api;

import java.util.concurrent.CompletableFuture;

public interface Action {

    CompletableFuture<Boolean> execute();

}