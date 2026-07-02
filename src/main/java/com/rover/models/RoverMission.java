package com.rover.models;

import com.rover.services.RoverMissionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Slf4j
public class RoverMission implements RoverMissionAction {
    private final List<Rover> rovers;

    public RoverMission(List<Rover> rovers) {
        this.rovers = rovers;
    }

    @Override
    public void move() {
        RoverMissionService service = new RoverMissionService();
        for (Rover rover : rovers) {
            List<String> roverPositionHistory = service.move(rover);
            log.info("Rover final position: {}", rover);
            service.printRoverPositionHistory(roverPositionHistory);
        }
    }
}