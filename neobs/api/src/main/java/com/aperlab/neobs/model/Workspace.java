package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.model.api.IProject;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Workspace {

     public NeoKey id;
     public String sourceDir = ".";

     @Getter
     private Map<NeoKey, IProject<?>> projects = new HashMap<>();

     public Workspace(String name) {
          this.id = NeoKey.ofWorkspace(name);
     }

     public void addProject(IProject<?> p){
         System.out.println("[DEBUG] Adding project" + p.getId());
          projects.put(p.getId(), p);
     }

     public Target findTarget(NeoKey id) {
          NeoKey projectId = id.getProject();
          if(!projects.containsKey(projectId)){
                System.out.println("Project: " + projectId + " doesn't exist");
                return null;
           }

          IProject<?> p = projects.get(projectId);
          return p.getTarget(id);
     }

}
