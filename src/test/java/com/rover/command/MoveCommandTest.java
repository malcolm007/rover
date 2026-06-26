package com.rover.command;

import com.rover.models.Direction;
import com.rover.models.Plateau;
import com.rover.models.Position;
import com.rover.models.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveCommandTest {

    private final MoveCommand command = new MoveCommand();

    @Test
    void should_move_north_from_position() {
        // GIVEN
        Position position = new Position(1, 2);
        Rover rover = createRover(position, Direction.N);

        // WHEN
        Position newPosition = command.move(rover);

        // THEN
        assertEquals(new Position(1, 3), newPosition);
    }

    @Test
    void should_move_east_from_position() {
        // GIVEN
        Position position = new Position(1, 2);
        Rover rover = createRover(position, Direction.E);

        // WHEN
        Position newPosition = command.move(rover);

        // THEN
        assertEquals(new Position(2, 2), newPosition);
    }

    @Test
    void should_move_south_from_position() {
        // GIVEN
        Position position = new Position(1, 2);
        Rover rover = createRover(position, Direction.S);

        // WHEN
        Position newPosition = command.move(rover);

        // THEN
        assertEquals(new Position(1, 1), newPosition);
    }

    @Test
    void should_move_west_from_position() {
        // GIVEN
        Position position = new Position(1, 2);
        Rover rover = createRover(position, Direction.W);

        // WHEN
        Position newPosition = command.move(rover);

        // THEN
        assertEquals(new Position(0, 2), newPosition);
    }

    @Test
    void should_update_rover_position_when_execute_with_valid_position() {
        // GIVEN
        Position initialPosition = new Position(1, 2);
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(initialPosition, Direction.N, plateau);

        // WHEN
        command.execute(rover);

        // THEN
        assertEquals(new Position(1, 3), rover.getPosition());
    }

    @Test
    void should_throw_exception_when_execute_with_out_of_plateau_position() {
        // GIVEN
        Position initialPosition = new Position(5, 5);
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(initialPosition, Direction.N, plateau);

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    @Test
    void should_throw_exception_with_correct_message_when_out_of_plateau() {
        // GIVEN
        Position initialPosition = new Position(5, 5);
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(initialPosition, Direction.N, plateau);

        // WHEN & THEN
        IllegalStateException exception = assertThrows(
            IllegalStateException.class,
            () -> command.execute(rover)
        );
        assertEquals("Out of plateau", exception.getMessage());
    }

    @Test
    void should_handle_move_at_plateau_boundary() {
        // GIVEN
        Position initialPosition = new Position(0, 0);
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(initialPosition, Direction.S, plateau);

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    @Test
    void should_handle_move_at_plateau_west_boundary() {
        // GIVEN
        Position initialPosition = new Position(0, 2);
        Plateau plateau = new Plateau(5, 5);
        Rover rover = new Rover(initialPosition, Direction.W, plateau);

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    private Rover createRover(Position position, Direction direction) {
        Plateau plateau = new Plateau(10, 10);
        return new Rover(position, direction, plateau);
    }
}

