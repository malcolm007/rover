package com.rover.parser;

import com.rover.models.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileParser {

    public RoverMission parse(Path path) throws IOException {
        return parseLines(Files.readAllLines(path));
    }

    public RoverMission parseLines(List<String> lines) {
        validate(lines);
        validatePlateau(lines.getFirst());
        String[] plateauData = lines.getFirst().split("\\s+");

        Plateau plateau = new Plateau(Integer.parseInt(plateauData[0]), Integer.parseInt(plateauData[1]));
        List<RoverInstruction> roverInstructions = new ArrayList<>();
        for (int i = 1; i < lines.size(); i += 2) {
            String[] roverData = lines.get(i).split("\\s+");
            int x = Integer.parseInt(roverData[0]);
            int y = Integer.parseInt(roverData[1]);
            Direction direction = Direction.valueOf(roverData[2]);
            Rover rover = new Rover(new Position(x, y), direction, plateau);
            String instructions = lines.get(i + 1);
            validateInstructions(instructions);
            roverInstructions.add(new RoverInstruction(rover, instructions));
        }
        return new RoverMission(roverInstructions);
    }

    private void validate(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Empty input file !");
        }
        if ((lines.size() - 1) % 2 != 0) {
            throw new IllegalArgumentException("Invalid rover configuration");
        }
    }

    private void validatePlateau(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Plateau must contain exactly 2 numbers"
            );
        }
        try {
            Integer.parseInt(parts[0]);
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    "Plateau coordinates must be integers"
            );
        }
    }

    private void validateInstructions(String instructions) {
        if (instructions == null || instructions.isBlank()) {
            throw new IllegalArgumentException("Instructions cannot be empty");
        }
        for (char c : instructions.toCharArray()) {
            if (c != 'L' && c != 'R' && c != 'M') {
                throw new IllegalArgumentException(
                        "Invalid instruction: " + c
                );
            }
        }
    }
}
