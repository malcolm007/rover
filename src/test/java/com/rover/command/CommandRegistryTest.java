package com.rover.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandRegistryTest {

    private final CommandRegistry registry = new CommandRegistry();

    @Test
    void should_get_left_command() {
        // WHEN
        Command command = registry.get('L');

        // THEN
        assertNotNull(command);
        assertInstanceOf(LeftCommand.class, command);
    }

    @Test
    void should_get_right_command() {
        // WHEN
        Command command = registry.get('R');

        // THEN
        assertNotNull(command);
        assertInstanceOf(RightCommand.class, command);
    }

    @Test
    void should_get_move_command() {
        // WHEN
        Command command = registry.get('M');

        // THEN
        assertNotNull(command);
        assertInstanceOf(MoveCommand.class, command);
    }

    @Test
    void should_throw_exception_for_unknown_command() {
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> registry.get('X'));
    }

    @Test
    void should_throw_exception_with_correct_message_for_unknown_command() {
        // WHEN & THEN
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> registry.get('Z')
        );
        assertEquals("Unknown command: Z", exception.getMessage());
    }

    @Test
    void should_throw_exception_for_lowercase_command() {
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> registry.get('m'));
    }

    @Test
    void should_throw_exception_for_numeric_command() {
        // WHEN & THEN
        assertThrows(IllegalArgumentException.class, () -> registry.get('1'));
    }
}

