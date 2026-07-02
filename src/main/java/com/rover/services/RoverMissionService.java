package com.rover.services;

import com.rover.command.CommandRegistry;
import com.rover.models.Rover;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoverMissionService {

    private final CommandRegistry registry;

    public RoverMissionService() {
        this.registry = new CommandRegistry();
    }

    public List<String> move(Rover rover) {
        List<String> roverPositionHistory = new ArrayList<>();
        roverPositionHistory.add(rover.toString());
        for (char c : rover.getInstructions().toCharArray()) {
            registry.get(c)
                    .execute(rover);
            roverPositionHistory.add(rover.toString());
        }
        return roverPositionHistory;
    }

    public void printRoverPositionHistory(List<String> roverPositionHistory) {
        StringBuilder sb = new StringBuilder("Rover positions History :");
        for (String position : roverPositionHistory) {
            sb.append("\n").append(position);
        }
        log.debug(sb.toString());
    }
}
