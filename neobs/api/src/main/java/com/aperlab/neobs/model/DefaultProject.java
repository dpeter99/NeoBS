package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.api.ILoadingContext;
import com.aperlab.neobs.api.Project;
import com.aperlab.neobs.api.Target;
import com.aperlab.serialization.DataResult;
import com.aperlab.serialization.Decoder;
import com.aperlab.serialization.FormatOps;

import java.util.*;

public class DefaultProject implements Project {

    public static Decoder<Project, ILoadingContext> decoder = new Decoder<>() {
        @Override
        public <T> DataResult<Project> decode(FormatOps<T> ops, T input, ILoadingContext ctx){
            var name = ops.requireLabel(input, 0);

            Project project = new DefaultProject(ctx.getWorkspace(), name.get());

            return DataResult.ofSuccess(project);
        }

        @Override
        public String getName() {
            return "project";
        }
    };


    Workspace workspace;
    NeoKey id;

    Map<NeoKey, Target> targets = new HashMap<>();

    public DefaultProject(Workspace ws, String id) {
        this.id = ws.id.withProject(id);
        this.workspace = ws;
    }

    @Override
    public NeoKey getId() {
        return id;
    }

    @Override
    public Target target(String name) {
        Target t = new DefaultTarget(this, id.withTarget(name));
        targets.put(t.getId(), t);
        return t;
    }

    @Override
    public Collection<Target> getAllTargets() {
        return Collections.unmodifiableCollection(targets.values());
    }

    @Override
    public Optional<Target> getTargetByName(String name) {
        return Optional.of(this.targets.get(id.withTarget(name)));
    }

    @Override
    public void PrintStructure() {

    }
}
