package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.model.api.IProject;
import com.aperlab.serialization.DataResult;
import com.aperlab.serialization.Decoder;
import com.aperlab.serialization.FormatOps;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Workspace {

    public static Decoder<Workspace> decoder = new Decoder<Workspace>() {
        @Override
        public <T> DataResult<Workspace> decode(FormatOps<T> ops, T input){
            var name = ops.getAttributeString(input, "name");
            if (name.isError()) {
                name = ops.getLabel(input, 0);
            }
            if (name.isError()) {
                throw new RuntimeException("Workspace name not found");
            }

            Workspace ws = new Workspace(name.getValue().get());

            ws.sourceDir = ops.getAttributeString(input, "sourceDir").orElse(".");

            return DataResult.ofSuccess(ws);
        }

        @Override
        public String getName() {
            return "workspace";
        }
    };


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
