package com.aperlab.neobs;

import org.jetbrains.annotations.NotNull;


// concrete target: @totum//src/denarius/denarius-client:build
// plugin: @plugins//kts-plugin
public class ID implements Comparable<ID>{

    public static final char ABSOLUTE_STARTER = '@';
    public static final char TARGET_SEPARATOR = ':';

    private final String Repo;
    private final String Project;
    private final String Target;

    public static ID ofPlugin(@NotNull String s) {
        return new ID("plugins", s, "");
    }

    public ID(String repo, String project, String target) {
        Repo = repo;
        Project = project;
        Target = target;
    }

    @Override
    public String toString() {
        return ABSOLUTE_STARTER + Repo + "//" + Project + (Target.isEmpty() ? "" : ":" + Target);
    }

    @Override
    public int compareTo(@NotNull ID o) {
        return 0;
    }
}
