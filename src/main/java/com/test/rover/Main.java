package com.test.rover;

import com.test.rover.command.CommandRegistry;
import com.test.rover.models.RoverInstruction;
import com.test.rover.models.RoverMission;
import com.test.rover.parser.FileParser;
import com.test.rover.services.RoverMissionService;

import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {
        FileParser parser = new FileParser();
        RoverMission mission = parser.parse(Path.of(args[0]));

        CommandRegistry registry = new CommandRegistry();
        RoverMissionService service = new RoverMissionService(registry);
        for (RoverInstruction instruction : mission.roverInstructions()) {
            service.execute(instruction);
            System.out.println(instruction.rover());
        }
    }
}
