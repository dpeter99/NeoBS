package com.aperlab.neobs.extensions.npm.project;

import com.aperlab.neobs.NeoKey;
import com.aperlab.neobs.actions.CmdAction;
import com.aperlab.neobs.extensions.npm.model.PackageJson;
import com.aperlab.neobs.model.AbstractProjectImpl;
import com.aperlab.neobs.model.Target;
import com.aperlab.neobs.model.Workspace;
import com.aperlab.neobs.model.api.IProjectSpec;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NpmProjectImpl extends AbstractProjectImpl<NpmProjectImpl, NpmProjectImpl.NpmProject> {

    public static class NpmProject extends AbstractProject<NpmProjectImpl> {
        private File packagesFile;
        private PackageJson packageData;

        public List<String> includeScripts = new ArrayList<>();

        public NpmProject(Workspace w, File projectFolder) {
            super(w, projectFolder);
            setPackagesFile(Paths.get(projectFolder.toString(), "./package.json").toFile());
        }

        private void loadPackagesData(PackageJson data){
            this.packageData = data;

            data.scripts.keySet().stream()
                    .filter(s -> includeScripts.isEmpty() || includeScripts.contains(s))
                    .map(s -> {
                        Target t = new Target(getId().withTarget(s));
                        CmdAction action = new CmdAction("npm run " + s, packagesFile.getParentFile());
                        t.addAction(action);
                        return t;
                    })
                    .forEach(this::addTarget);

            Target install = new Target(getId().withTarget("install"));
            install.addAction(new CmdAction("npm install", packagesFile.getParentFile()));
            addTarget(install);
        }

        @Override
        public NeoKey getId() {
            return NeoKey.of(workspace, packageData.name);
        }

        public void setPackagesFile(File file) {
            this.packagesFile = file.toPath().toAbsolutePath().normalize().toFile();
            Gson gson = new Gson();
            PackageJson json;

            try {
                json = gson.fromJson(new FileReader(packagesFile), PackageJson.class);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            loadPackagesData(json);
        }

        @Override
        public NpmProjectImpl build() {
            return new NpmProjectImpl(this);
        }
    }

    public NpmProjectImpl(NpmProject id) {
        super(id);
    }
}
