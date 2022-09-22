package com.ch.springfw.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource{
    private final File file;
    private final String path;

    public FileSystemResource(String path) {
        this.path = path;
        file=new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public File file() {
        return file;
    }
}
