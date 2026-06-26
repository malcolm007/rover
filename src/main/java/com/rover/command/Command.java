package com.rover.command;

import com.rover.models.Rover;

public interface Command {
    void execute(Rover rover);
}
