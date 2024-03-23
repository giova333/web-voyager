package com.webvoyager.utils;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ResourceLoader {

    @SneakyThrows
    public static String loadResource(String path) {
        var url = ResourceLoader.class.getClassLoader().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.toURI())));
    }
}
