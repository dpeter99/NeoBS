package com.aperlab.neobs.loader;

public class FileLoadingException extends Exception {

    public FileLoadingException(String message) {
        super(message);
    }

    public FileLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
