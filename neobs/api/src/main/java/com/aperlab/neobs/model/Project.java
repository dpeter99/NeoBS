package com.aperlab.neobs.model;

import com.aperlab.neobs.ID;

import java.util.HashMap;
import java.util.Map;

public class Project implements ExtensionAware {
    public String Name;

    public ID id;

    public Workspace workspace;

    private Map<ID, Target> targets = new HashMap<>();

    public Project(Workspace w, String name) {
        workspace = w;
        Name = name;
        id = ID.of(workspace, this);
    }

    public ID getID(){
        return id;
    }

    public void addTarget(Target t){
        targets.put(t.id, t);
    }

    public void PrintStructure() {
        System.out.println(id);
    }

    public Target getTarget(ID id) {
        return targets.get(id);
    }
}
