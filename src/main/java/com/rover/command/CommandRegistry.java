package com.rover.command;

import java.util.Map;

public class CommandRegistry {

    private final Map<Character, Command> commands;

    public CommandRegistry() {
        this.commands = Map.of(
                'L', new LeftCommand(),
                'R', new RightCommand(),
                'M', new MoveCommand());
    }

    public Command get(char code) {
        Command command = commands.get(code);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + code);
        }
        return command;
    }
}
