package com.test.rover.command;

import com.test.rover.models.Direction;
import com.test.rover.models.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LeftCommandTest {

    private final LeftCommand command = new LeftCommand();

    @Test
    void should_turn_left_from_N_to_W() {
        assertEquals(Direction.W, command.left(Direction.N));
    }

    @Test
    void should_turn_left_from_W_to_S() {
        assertEquals(Direction.S, command.left(Direction.W));
    }

    @Test
    void should_turn_left_from_S_to_E() {
        assertEquals(Direction.E, command.left(Direction.S));
    }

    @Test
    void should_turn_left_from_E_to_N() {
        assertEquals(Direction.N, command.left(Direction.E));
    }

    @Test
    void should_update_rover_direction_when_execute() {

        // GIVEN
        Rover rover = mock(Rover.class);

        when(rover.getDirection()).thenReturn(Direction.N);

        LeftCommand leftCommand = new LeftCommand();

        // WHEN
        leftCommand.execute(rover);

        // THEN
        verify(rover).setDirection(Direction.W);
    }
}
