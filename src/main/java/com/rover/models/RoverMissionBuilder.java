package com.rover.models;

import java.util.List;

public interface RoverMissionBuilder {

    RoverMission build(List<String> lines);
}
