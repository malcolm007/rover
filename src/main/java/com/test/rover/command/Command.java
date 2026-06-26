package com.test.rover.command;

import com.test.rover.models.Rover;

public interface Command {
    void execute(Rover rover);
}
