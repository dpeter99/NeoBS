package com.aperlab.neobs.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Project implements ExtensionAware {
    public String Name;

    //public Optional<Lazy<Optional<Project>>> Parent;

    private Map<String, Target> targets = new HashMap<>();

    public Project(String name) {
        Name = name;
    }

    public String getID(){
        return Name;
    }

    public void AddTarget(Target t){
        targets.put(t.Name, t);
    }

    public void PrintStructure() {
        System.out.println(Name);
    }
}
