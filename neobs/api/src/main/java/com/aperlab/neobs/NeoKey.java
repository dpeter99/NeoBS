package com.aperlab.neobs;

import com.aperlab.neobs.model.Workspace;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;


// concrete target: @totum//src/denarius/denarius-client:build
// plugin: @plugins//kts-plugin
public class NeoKey implements Comparable<NeoKey>{

    public static final char ABSOLUTE_STARTER = '@';
    public static final char TARGET_SEPARATOR = ':';

    private final String repo;
    private final Optional<String> project;
    private final Optional<String> target;

    public static NeoKey ofPlugin(@NotNull String s) {
        return new NeoKey("plugins", s, "");
    }

    public NeoKey(String repo, String project, String target) {
        this.repo = repo;
        this.project = Optional.of(project);
        this.target = Optional.of(target);
    }

    public NeoKey(String repo, String project) {
        this.repo = repo;
        this.project = Optional.of(project);
        target = Optional.empty();
    }

    public NeoKey(String repo) {
        this.repo = repo;
        project = Optional.empty();
        target = Optional.empty();
    }


    public static NeoKey of(String stringID) {
        String[] parts = stringID.split("//");

        String repo = parts[0].replace(ABSOLUTE_STARTER, ' ').trim();

        String[] pathAndTarget = parts[1].split(String.valueOf(TARGET_SEPARATOR));
        String path = pathAndTarget[0];

        if(pathAndTarget.length == 1){
            return new NeoKey(repo, path);
        }

        String target = pathAndTarget[1];

        return new NeoKey(repo, path, target);
    }

    public static NeoKey of(Workspace w, String p){
        return w.id.withProject(p);
    }

    public static NeoKey ofWorkspace(String w){
        return new NeoKey(w);
    }


    @Override
    public String toString() {
        return ABSOLUTE_STARTER + repo + "//" + (project.orElse("")) + (target.map(s -> ":" + s).orElse(""));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof NeoKey)) {
            return false;
        } else {
            NeoKey other = (NeoKey)object;
            return this.repo.equals(other.repo) &&
                    this.project.equals(other.project) &&
                    this.target.equals(other.target);
        }
    }

    public int hashCode() {
        return 15 * this.repo.hashCode() +
                (15* this.project.hashCode()) +
                this.target.hashCode();
    }

    @Override
    public int compareTo(@NotNull NeoKey o) {
        int i = this.project.get().compareTo(o.project.get());
        if (i == 0) {
            i = this.repo.compareTo(o.repo);
        }

        return i;
    }

    public NeoKey getProject(){
        return project.map(s -> new NeoKey(repo, s)).orElseGet(() -> new NeoKey(repo));
    }

    public boolean isTarget() {
        return target.isPresent();
    }

    public NeoKey withTarget(@NotNull String name) {
        return new NeoKey(repo, project.get(), name);
    }

    private NeoKey withProject(String p) {
        return new NeoKey(repo, p);
    }
}
