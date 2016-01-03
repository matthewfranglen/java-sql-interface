package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;
public class NameBuilder {

    private String name;
    private String path;

    public void setName(String name) {
        checkArgument(! Strings.isNullOrEmpty(name), "name is null or blank");

        this.name = name.trim();
    }

    public void setPath(String path) {
        checkNotNull(path, "path is null");

        this.path = stripLeadingSlash(path).trim();
    }

    private String stripLeadingSlash(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public Name build() {
        return new Name(name, path);
    }

}
