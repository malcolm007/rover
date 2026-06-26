package com.test.rover.services;

import com.test.rover.command.CommandRegistry;
import com.test.rover.models.Rover;
import com.test.rover.models.RoverInstruction;

public class RoverMissionService {

    private final CommandRegistry registry;

    public RoverMissionService(CommandRegistry registry) {
        this.registry = registry;
    }

    public void execute(RoverInstruction instruction) {

        Rover rover = instruction.rover();
        for (char c : instruction.instructions().toCharArray()) {
            registry.get(c)
                    .execute(rover);
        }
    }
}
