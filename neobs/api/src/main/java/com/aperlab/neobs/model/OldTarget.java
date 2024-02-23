package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.api.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class OldTarget {

    public NeoKey id;
    public List<Action> actions = new ArrayList<>();

    public OldTarget(NeoKey id) {
        this.id = id;
    }

    public void run() throws InterruptedException, ExecutionException {
        System.out.println("Running target: " + id);

        for (Action action : actions) {
            CompletableFuture<Boolean> future = action.execute();
            boolean res;
            synchronized (future) {
                res = future.get();
            }
            if(!res){
                System.out.println("Action did not run successfully");
                break;
            }
        }
    }

    public void addAction(Action action) {
        actions.add(action);
    }
}
