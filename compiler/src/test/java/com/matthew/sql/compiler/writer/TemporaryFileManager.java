package com.matthew.sql.compiler.writer;

import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class TemporaryFileManager {

    private static int temporaryFolderCounter = 0;

    private Path testFolder;

    public void create() throws IOException {
        checkState(testFolder == null);

        testFolder = java.nio.file.Files.createTempDirectory(null);
    }

    public void destroy() throws IOException {
        checkState(testFolder != null);

        delete(testFolder.toFile());
        testFolder = null;
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
