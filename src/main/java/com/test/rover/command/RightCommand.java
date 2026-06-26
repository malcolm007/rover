package com.test.rover.command;

import com.test.rover.models.Direction;
import com.test.rover.models.Rover;

public class RightCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.setDirection(right(rover.getDirection()));
    }

    public Direction right(Direction direction) {
        return switch (direction) {
            case N -> Direction.E;
            case E -> Direction.S;
            case S -> Direction.W;
            case W -> Direction.N;
        };
    }
}
