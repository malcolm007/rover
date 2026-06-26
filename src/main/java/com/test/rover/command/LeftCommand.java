package com.test.rover.command;

import com.test.rover.models.Direction;
import com.test.rover.models.Rover;

public class LeftCommand implements Command {

    @Override
    public void execute(Rover rover) {
        rover.setDirection(left(rover.getDirection()));
    }

    public Direction left(Direction direction) {
        return switch (direction) {
            case N -> Direction.W;
            case W -> Direction.S;
            case S -> Direction.E;
            case E -> Direction.N;
        };
    }
}
