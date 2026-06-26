package com.test.rover.command;

import com.test.rover.models.Position;
import com.test.rover.models.Rover;

public class MoveCommand implements Command {

    @Override
    public void execute(Rover rover) {
        Position newPosition = move(rover);
        if (!rover.getPlateau().contains(newPosition)) {
            throw new IllegalStateException("Out of plateau");
        }
        rover.setPosition(newPosition);
    }

    public Position move(Rover rover) {
        Position pos = rover.getPosition();
        return switch (rover.getDirection()) {
            case N -> new Position(pos.x(), pos.y() + 1);
            case E -> new Position(pos.x() + 1, pos.y());
            case S -> new Position(pos.x(), pos.y() - 1);
            case W -> new Position(pos.x() - 1, pos.y());
        };
    }
}
