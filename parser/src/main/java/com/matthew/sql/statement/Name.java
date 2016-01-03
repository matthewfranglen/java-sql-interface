package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;

import com.google.common.base.Strings;

public class Name {

    private final String name;
    private final String path;

    public Name(String name, String path) {
        checkArgument(! Strings.isNullOrEmpty(name), "name is null or blank");

        this.name = name;
        this.path = checkNotNull(path, "path is null");
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        if (isInDefaultPackage()) {
            return getName();
        }
        return getPackage() + "." + getName();
    }

    public String getPath() {
        return path;
    }

    public String getPackage() {
        if (isInDefaultPackage()) {
            return "";
        }
        return path.replaceAll(File.separator, ".");
    }

    public boolean isInDefaultPackage() {
        return Strings.isNullOrEmpty(path);
    }

}
