package com.rover.models;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RoverMissionImpl implements RoverMissionBuilder {
    private final List<Rover> rovers;

    public RoverMissionImpl() {
        this.rovers = new ArrayList<>();
    }

    public RoverMission build(List<String> lines) {
        validateLines(lines);
        Plateau plateau = Plateau.PlateauBuilder.fromLine(lines.getFirst());
        int roverId = 1;
        for (int i = 1; i < lines.size(); i += 2) {
            Rover rover  = new Rover.RoverBuilder()
                    .id("Rover-" + roverId++)
                    .positionAndDirection(lines.get(i))
                    .instructions(lines.get(i + 1))
                    .plateau(plateau)
                    .build();
            rovers.add(rover);
        }
        return new RoverMission(rovers);
    }

    private void validateLines(List<String> lines) {
        if (lines.isEmpty()) {
            throw new IllegalArgumentException("Empty input file !");
        }
        if ((lines.size() - 1) % 2 != 0) {
            throw new IllegalArgumentException("Invalid rover configuration");
        }
    }
}
