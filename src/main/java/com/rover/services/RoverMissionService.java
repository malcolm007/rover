package com.rover.services;

import com.rover.command.CommandRegistry;
import com.rover.models.Rover;
import com.rover.models.RoverInstruction;

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
