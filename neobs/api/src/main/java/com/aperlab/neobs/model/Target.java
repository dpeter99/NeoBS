package com.aperlab.neobs.model;

import com.aperlab.neobs.ID;

import java.util.ArrayList;
import java.util.List;

public class Target {

    public ID id;
    public List<Action> actions = new ArrayList<>();

    public Target(ID id) {
        this.id = id;
    }

    public void run() {
        for (Action action : actions) {
            action.execute();
        }
    }
}
