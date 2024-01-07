package com.aperlab.neobs;

import com.aperlab.neobs.model.Project;
import com.aperlab.neobs.model.Workspace;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.Provider;
import java.util.Optional;


// concrete target: @totum//src/denarius/denarius-client:build
// plugin: @plugins//kts-plugin
public class ID implements Comparable<ID>{

    public static final char ABSOLUTE_STARTER = '@';
    public static final char TARGET_SEPARATOR = ':';

    private final String Repo;
    private final String Project;
    private final Optional<String> Target;

    public static ID ofPlugin(@NotNull String s) {
        return new ID("plugins", s, "");
    }

    public ID(String repo, String project, String target) {
        Repo = repo;
        Project = project;
        Target = Optional.of(target);
    }

    public ID(String repo, String project) {
        Repo = repo;
        Project = project;
        Target = Optional.empty();
    }

    public static ID of(String stringID) {
        String[] parts = stringID.split("//");

        String repo = parts[0].replace(ABSOLUTE_STARTER, ' ').trim();

        String path = parts[1].split(String.valueOf(TARGET_SEPARATOR))[0];

        String target = parts[1].split(String.valueOf(TARGET_SEPARATOR))[1];

        return new ID(repo, path, target);
    }

    public static ID of(Workspace w, Project p){
        return new ID(w.Name, p.Name);
    }

    @Override
    public String toString() {
        return ABSOLUTE_STARTER + Repo + "//" + Project + (Target.map(s -> ":" + s).orElse(""));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof ID)) {
            return false;
        } else {
            ID other = (ID)object;
            return this.Repo.equals(other.Repo) &&
                    this.Project.equals(other.Project) &&
                    this.Target.equals(other.Target);
        }
    }

    public int hashCode() {
        return 15 * this.Repo.hashCode() +
                (15* this.Project.hashCode()) +
                this.Target.hashCode();
    }

    @Override
    public int compareTo(@NotNull ID o) {
        int i = this.Project.compareTo(o.Project);
        if (i == 0) {
            i = this.Repo.compareTo(o.Repo);
        }

        return i;
    }

    public ID getProject(){
        return new ID(Repo, Project);
    }

    public boolean isTarget() {
        return Target.isPresent();
    }

    public ID withTarget(@NotNull String name) {
        return new ID(Repo, Project, name);
    }
}
