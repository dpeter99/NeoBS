package com.aperlab.neobs.model;

import com.aperlab.neobs.ID;

import java.util.HashMap;
import java.util.Map;

public class Workspace {

     public String Name;
     public String SourceDir;

     private Map<ID, Project> projects = new HashMap<>();

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

     public Target findTarget(ID id) {
          ID projectId = id.getProject();
          if(!projects.containsKey(projectId)){
                System.out.println("Project: " + projectId + " doesn't exist");
                return null;
           }

          Project p = projects.get(projectId);
          return p.getTarget(id);
     }
}
