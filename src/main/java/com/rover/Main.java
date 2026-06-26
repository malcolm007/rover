package com.rover;

import com.rover.command.CommandRegistry;
import com.rover.models.RoverInstruction;
import com.rover.models.RoverMission;
import com.rover.parser.FileParser;
import com.rover.services.RoverMissionService;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        FileParser parser = new FileParser();
        RoverMission mission = parser.parse(Path.of(args[0]));

        CommandRegistry registry = new CommandRegistry();
        RoverMissionService service = new RoverMissionService(registry);
        for (RoverInstruction instruction : mission.roverInstructions()) {
            service.execute(instruction);
            log.info("Rover position: {}", instruction.rover());
        }
    }
}
