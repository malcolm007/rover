package com.rover.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileParser implements Parser{

    public List<String> parse(String source) throws IOException {
        return Files.readAllLines(Path.of(source));
    }
}
