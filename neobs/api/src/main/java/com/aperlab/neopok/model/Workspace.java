package com.aperlab.neobs.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Workspace {

     public String Name;
     public String SourceDir;

     private Map<String, Project> projects = new HashMap<>();

     public Workspace(String name) {
          Name = name;
     }

     public void AddProject(Project p){
          projects.put(p.getID(), p);
     }

     public void PrintStructure() {
          System.out.println(Name);
          projects.forEach(((s, project) -> project.PrintStructure()));
     }
}
