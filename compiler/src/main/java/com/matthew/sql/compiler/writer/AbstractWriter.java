package com.matthew.sql.compiler.writer;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.logging.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public abstract class AbstractWriter {

    private final Log log;

    public AbstractWriter(Log log) {
        this.log = log;
    }

    protected void write(File file, String content) throws IOException {
        file.getParentFile().mkdirs();
        Files.write(content, file, Charsets.UTF_8);
    }

    protected Log getLog() {
        return log;
    }

}
