package com.aperlab.neobs.extensions.npm.model;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class PackageJson {

    public String name;

    public Map<String, String> scripts = new HashMap<>();

    public static PackageJson of(File packageFile) throws FileNotFoundException {
        Gson g = new Gson();
        return g.fromJson(new FileReader(packageFile), PackageJson.class);
    }

}
