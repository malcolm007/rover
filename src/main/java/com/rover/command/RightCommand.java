package com.rover.command;

import com.rover.models.Direction;
import com.rover.models.Rover;

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
