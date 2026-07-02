package com.rover.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Getter
@Slf4j
public class Rover {

    private final String id;
    private Position position;
    private Direction direction;
    private final String instructions;
    private final Plateau plateau;

    private Rover(String id,
                  Position position,
                  Direction direction,
                  String instructions,
                  Plateau plateau) {
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.instructions = instructions;
        this.plateau = plateau;
    }



    @Override
    public String toString() {
        return id + " : " + position.x() + " "
                + position.y() + " "
                + direction;
    }

    public static class RoverBuilder {
        private Position position;
        private Direction direction;
        private Plateau plateau;
        private String instructions;
        private String id;

        public RoverBuilder position(int x, int y) {
            this.position = new Position(x, y);
            return this;
        }

        public RoverBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RoverBuilder plateau(Plateau plateau) {
            this.plateau = plateau;
            return this;
        }

        public RoverBuilder direction(String direction) {
            this.direction = Direction.valueOf(direction);
            log.debug("Building rover facing {}", direction);
            return this;
        }

        public Rover build() {
            return new Rover(id, position, direction, instructions, plateau);
        }

        public RoverBuilder positionAndDirection(String line) {
            String[] roverData = line.split("\\s+");

            int x = getInt(roverData[0]);
            int y = getInt(roverData[1]);

            log.debug("Building rover at position ({}, {})", x, y);
            return position(x, y).direction(roverData[2]);
        }

        private static int getInt(String value) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                log.debug("Building rover with invalid integer value: {}", value);
                throw new IllegalArgumentException(
                        "Invalid integer value: " + value
                );
            }
        }

        public RoverBuilder instructions(String line) {
            validateInstructions(line);
            log.debug("Building rover with instructions {}", line);
            this.instructions = line;
            return this;
        }

        private void validateInstructions(String instructions) {
            if (instructions == null || instructions.isBlank()) {
                log.debug("Building rover with empty instructions");
                throw new IllegalArgumentException("Instructions cannot be empty");
            }
            for (char c : instructions.toCharArray()) {
                if (c != 'L' && c != 'R' && c != 'M') {
                    log.debug("Building rover with invalid instruction: {}", c);
                    throw new IllegalArgumentException(
                            "Invalid instruction: " + c
                    );
                }
            }
        }
    }
}