package com.test.rover.command;

import com.test.rover.models.Direction;
import com.test.rover.models.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RightCommandTest {

    private final RightCommand command = new RightCommand();

    @Test
    void should_turn_right_from_N_to_E() {
        assertEquals(Direction.E, command.right(Direction.N));
    }

    @Test
    void should_turn_right_from_W_to_N() {
        assertEquals(Direction.N, command.right(Direction.W));
    }

    @Test
    void should_turn_right_from_S_to_W() {
        assertEquals(Direction.W, command.right(Direction.S));
    }

    @Test
    void should_turn_right_from_E_to_S() {
        assertEquals(Direction.S, command.right(Direction.E));
    }

    @Test
    void should_update_rover_direction_when_execute() {

        // GIVEN
        Rover rover = mock(Rover.class);
        when(rover.getDirection()).thenReturn(Direction.N);
        RightCommand rightCommand = new RightCommand();

        // WHEN
        rightCommand.execute(rover);

        // THEN
        verify(rover).setDirection(Direction.E);
    }
}
