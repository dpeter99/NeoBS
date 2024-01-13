package com.aperlab.neobs.model;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.model.api.IProject;
import com.aperlab.neobs.model.api.IProjectBuilder;
import com.aperlab.neobs.model.api.IProjectSpec;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractProjectImpl<Self extends IProject<Self>, Props extends IProjectSpec<?>> implements IProject<Self> {

    public static abstract class
    AbstractProject<Impl extends IProject<Impl>>
            implements IProjectBuilder<Impl> {
        public Workspace workspace;
        @Getter
        public NeoKey id;

        public File projectFolder;

        public Map<NeoKey, Target> targets = new HashMap<>();

        public AbstractProject(Workspace w, File projectFolder) {
            this.workspace = w;
            this.projectFolder = projectFolder;
        }

        @Override
        public void setWorkspace(Workspace workspace) {
            this.workspace = workspace;
        }

        @Override
        public void setName(String name) {
            id = NeoKey.of(workspace, name);
        }

        @Override
        public Target getTarget(NeoKey id) {
            return targets.get(id);
        }

        @Override
        public List<Target> getTargets() {
            return targets.values().stream().toList();
        }

        @Override
        public void addTarget(@NotNull Target t) {
            targets.put(t.id, t);
        }
    }

    public Props props;

    public AbstractProjectImpl(Props id) {
        this.props = id;
    }

    @Override
    public void PrintStructure() {
        System.out.println(props.getId());
    }


    @Override
    public void setWorkspace(Workspace w) {
        props.setWorkspace(w);
    }

    @Override
    public NeoKey getId() {
        return props.getId();
    }

    @Override
    public Target getTarget(NeoKey id) {
        return props.getTarget(id);
    }

    @Override
    public List<Target> getTargets() {
        return props.getTargets();
    }
}
