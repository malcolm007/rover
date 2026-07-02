package com.rover.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoverMissionTest {

	@Test
	public void move_single_rover_should_update_position_to_expected_final() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");

		Rover rover = new Rover.RoverBuilder()
				.id("R1")
				.position(1, 2)
				.direction("N")
				.instructions("LMLMLMLMM")
				.plateau(plateau)
				.build();

		RoverMission mission = new RoverMission(List.of(rover));
		mission.move();

		assertEquals("R1 : 1 3 N", rover.toString());
	}

	@Test
	public void move_multiple_rovers_should_update_each_to_expected_final() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");

		Rover r1 = new Rover.RoverBuilder()
				.id("R1")
				.position(1, 2)
				.direction("N")
				.instructions("LMLMLMLMM")
				.plateau(plateau)
				.build();

		Rover r2 = new Rover.RoverBuilder()
				.id("R2")
				.position(3, 3)
				.direction("E")
				.instructions("MMRMMRMRRM")
				.plateau(plateau)
				.build();

		RoverMission mission = new RoverMission(List.of(r1, r2));
		mission.move();

		assertEquals("R1 : 1 3 N", r1.toString());
		assertEquals("R2 : 5 1 E", r2.toString());
	}

	@Test
	public void move_should_throw_when_rover_moves_out_of_plateau() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("0 0");

		// Rover at 0,0 facing S and moving will go to y=-1 -> out of plateau
		Rover rover = new Rover.RoverBuilder()
				.id("R1")
				.position(0, 0)
				.direction("S")
				.instructions("M")
				.plateau(plateau)
				.build();

		RoverMission mission = new RoverMission(List.of(rover));

		assertThrows(IllegalStateException.class, mission::move);
	}
}
