package com.matthew.sql.compiler.writer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public abstract class WriterSteps {

    private static int temporaryFolderCounter = 0;

    private Path testFolder;

    protected void init() throws IOException {
        testFolder = java.nio.file.Files.createTempDirectory(null);
    }

    protected void deinit() throws IOException {
        delete(testFolder.toFile());
    }

    protected File makeTemporaryFolder() throws IOException {
        File result = new File(testFolder.toFile(), String.valueOf(temporaryFolderCounter));
        temporaryFolderCounter++;

        if (! result.mkdir()) {
            throw new IOException("Failed to create folder: " + result);
        }
        return result;
    }

    private void delete(File file) throws IOException {
        if (file.isDirectory()) {
            for (File current : file.listFiles()) {
                delete(current);
            }
        }

        if (! file.delete()) {
            throw new IOException("Failed to delete file: " + file);
        }
    }

}
