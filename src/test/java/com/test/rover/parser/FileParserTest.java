package com.test.rover.parser;

import com.test.rover.models.RoverMission;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class FileParserTest {

    @Test
    void should_parse_valid_input() {
        FileParser parser = new FileParser();
        List<String> lines = List.of(
                "5 5",
                "1 2 N",
                "LMLMLMLMM",
                "3 3 E",
                "MMRMMRMRRM"
        );

        RoverMission mission = parser.parseLines(lines);

        assertEquals(2, mission.roverInstructions().size());
    }

    @Test
    void should_fail_when_input_is_empty() {

        // GIVEN
        FileParser parser = new FileParser();
        List<String> lines = List.of();

        // WHEN + THEN
        assertThrows(IllegalArgumentException.class,
                () -> parser.parseLines(lines));
    }


    @ParameterizedTest
    @MethodSource("invalidInputs")
    void should_fail_for_invalid_inputs(List<String> lines) {

        // GIVEN
        FileParser parser = new FileParser();

        // WHEN + THEN
        assertThrows(IllegalArgumentException.class,
                () -> parser.parseLines(lines));
    }

    static Stream<Arguments> invalidInputs() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of("5 5", "1 2 N")),
                Arguments.of(List.of("5 5", "1 2 N", "LMLM", "3 3 E")),
                Arguments.of(List.of("5", "1 2 N", "LMLM")),
                Arguments.of(List.of("5 5", "1 2 N", "INVALID"))
        );
    }

    @Test
    void should_parse_multiple_rovers() {

        // GIVEN
        FileParser parser = new FileParser();
        List<String> lines = List.of(
                "5 5",
                "1 2 N",
                "LMLM",
                "3 3 E",
                "MMR"
        );

        // WHEN
        RoverMission mission = parser.parseLines(lines);

        // THEN
        assertEquals(2, mission.roverInstructions().size());
    }

    @Test
    void should_parse_plateau_correctly() {

        // GIVEN
        FileParser parser = new FileParser();
        List<String> lines = List.of(
                "10 8",
                "1 2 N",
                "LMLM"
        );

        // WHEN
        RoverMission mission = parser.parseLines(lines);

        // THEN
        assertEquals(1, mission.roverInstructions().size());
        assertEquals("1 2 N",
                mission.roverInstructions()
                        .getFirst()
                        .rover()
                        .toString());
    }
}