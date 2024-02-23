package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.api.ILoadingContext;
import com.aperlab.neobs.api.Project;
import com.aperlab.neobs.api.Target;
import com.aperlab.serialization.DataResult;
import com.aperlab.serialization.Decoder;
import com.aperlab.serialization.FormatOps;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Workspace {

    public static Decoder<Workspace, ILoadingContext> decoder = new Decoder<>() {
        @Override
        public <T> DataResult<Workspace> decode(FormatOps<T> ops, T input, ILoadingContext ctx){
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
     private Map<NeoKey, Project> projects = new HashMap<>();

     public Workspace(String name) {
          this.id = NeoKey.ofWorkspace(name);
     }

     public void addProject(Project p){
         System.out.println("[DEBUG] Adding project" + p.getId());
          projects.put(p.getId(), p);
     }

     public Target findTarget(NeoKey id) {
          NeoKey projectId = id.getProject();
          if(!projects.containsKey(projectId)) {
                System.out.println("Project: " + projectId + " doesn't exist");
                return null;
          }
          Project p = projects.get(projectId);

          if(!id.isTarget()){
              throw new RuntimeException("Id does not have a target section");
          }

          Optional<Target> target = p.getTargetByName(id.getTargetName().get());

          return target.get();
     }

}
