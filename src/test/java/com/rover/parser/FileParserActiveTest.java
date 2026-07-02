package com.rover.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileParserActiveTest {

    @Test
    public void parse_valid_file_should_return_lines() throws Exception {
        Path tmp = Files.createTempFile("mission", ".txt");
        List<String> expected = List.of("5 5", "1 2 N", "LML");
        Files.write(tmp, expected);

        Parser parser = new FileParser();
        List<String> result = parser.parse(tmp.toString());

        assertEquals(expected, result);
    }

    @Test
    public void parse_nonexistent_file_should_throw_ioexception() {
        Parser parser = new FileParser();
        assertThrows(IOException.class, () -> parser.parse("no-such-file-xyz.txt"));
    }

    @Test
    public void parse_empty_file_should_return_empty_list() throws Exception {
        Path tmp = Files.createTempFile("empty", ".txt");
        Files.writeString(tmp, "");

        Parser parser = new FileParser();
        List<String> result = parser.parse(tmp.toString());

        assertTrue(result.isEmpty());
    }
}

