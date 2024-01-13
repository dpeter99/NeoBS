package com.aperlab.neobs.model;

import java.io.File;

public class SimpleProjectImpl
        extends AbstractProjectImpl<SimpleProjectImpl, SimpleProjectImpl.SimpleProject> {

    public static class SimpleProject
            extends AbstractProject<SimpleProjectImpl> {

        public SimpleProject(Workspace w, File projectFolder) {
            super(w, projectFolder);
        }

        @Override
        public SimpleProjectImpl build() {
            return new SimpleProjectImpl(this);
        }
    }

    public SimpleProjectImpl(SimpleProject id) {
        super(id);
    }
}