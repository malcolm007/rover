package com.rover.command;

import com.rover.models.Direction;
import com.rover.models.Plateau;
import com.rover.models.Plateau.PlateauBuilder;
import com.rover.models.Position;
import com.rover.models.Rover;
import com.rover.models.Rover.RoverBuilder;
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
        Plateau plateau = new PlateauBuilder().maxX(5).maxY(5).build();
        Rover rover = new RoverBuilder()
                .id("1")
                .position(1, 2)
                .direction("N")
                .plateau(plateau)
                .instructions("LLL")
                .build();

        // WHEN
        command.execute(rover);

        // THEN
        assertEquals(new Position(1, 3), rover.getPosition());
    }

    @Test
    void should_throw_exception_when_execute_with_out_of_plateau_position() {
        // GIVEN
        Plateau plateau = new PlateauBuilder().maxX(5).maxY(5).build();
        Rover rover = new RoverBuilder()
                .id("1")
                .position(5, 5)
                .direction("N")
                .plateau(plateau)
                .instructions("LLL")
                .build();

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    @Test
    void should_throw_exception_with_correct_message_when_out_of_plateau() {
        // GIVEN
        Plateau plateau = new PlateauBuilder().maxX(5).maxY(5).build();
        Rover rover = new RoverBuilder()
                .id("1")
                .position(5, 5)
                .direction("N")
                .plateau(plateau)
                .instructions("LLL")
                .build();

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
        Plateau plateau = new PlateauBuilder().maxX(5).maxY(5).build();
        Rover rover = new RoverBuilder()
                .id("1")
                .position(0, 0)
                .direction("S")
                .plateau(plateau)
                .instructions("LLL")
                .build();

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    @Test
    void should_handle_move_at_plateau_west_boundary() {
        // GIVEN
        Plateau plateau = new PlateauBuilder().maxX(5).maxY(5).build();
        Rover rover = new RoverBuilder()
                .id("1")
                .position(0, 2)
                .direction("W")
                .plateau(plateau)
                .instructions("LLL")
                .build();

        // WHEN & THEN
        assertThrows(IllegalStateException.class, () -> command.execute(rover));
    }

    private Rover createRover(Position position, Direction direction) {
        Plateau plateau = new PlateauBuilder().maxX(10).maxY(10).build();
        return new RoverBuilder()
                .id("1")
                .position(position.x(), position.y())
                .direction(direction.name())
                .plateau(plateau)
                .instructions("LLL")
                .build();
    }
}

