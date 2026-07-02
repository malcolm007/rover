package com.rover.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverMissionBuilderTest {

	@Test
	void plateauBuilder_fromLine_valid() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");
		assertTrue(plateau.contains(new Position(0, 0)));
		assertTrue(plateau.contains(new Position(5, 5)));
	}

	@Test
	void plateauBuilder_fromLine_invalid_count() {
		assertThrows(IllegalArgumentException.class,
				() -> Plateau.PlateauBuilder.fromLine("5"));
	}

	@Test
	void plateauBuilder_fromLine_invalid_number() {
		assertThrows(IllegalArgumentException.class,
				() -> Plateau.PlateauBuilder.fromLine("a b"));
	}

	@Test
	void roverBuilder_build_and_toString() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");
		Rover rover = new Rover.RoverBuilder()
				.id("R1")
				.positionAndDirection("1 2 N")
				.instructions("LMLM")
				.plateau(plateau)
				.build();

		assertEquals("R1 : 1 2 N", rover.toString());
	}

	@Test
	void roverBuilder_invalid_instructions() {
		Rover.RoverBuilder builder = new Rover.RoverBuilder();
		assertThrows(IllegalArgumentException.class, () -> builder.instructions("LMX"));
	}

	@Test
	void roverBuilder_invalid_position_number() {
		Rover.RoverBuilder builder = new Rover.RoverBuilder();
		assertThrows(IllegalArgumentException.class, () -> builder.positionAndDirection("a b N"));
	}

	@Test
	void roverBuilder_invalid_direction() {
		Rover.RoverBuilder builder = new Rover.RoverBuilder();
		assertThrows(IllegalArgumentException.class, () -> builder.positionAndDirection("1 2 Z"));
	}

	@Test
	void roverMissionImpl_build_valid_and_ids() {
		List<String> lines = List.of(
				"5 5",
				"1 2 N",
				"LMLM",
				"3 3 E",
				"MMR"
		);

		RoverMission mission = new RoverMissionImpl().build(lines);
		assertEquals(2, mission.getRovers().size());
		assertEquals("Rover-1", mission.getRovers().get(0).getId());
		assertEquals("Rover-2", mission.getRovers().get(1).getId());
	}

	@Test
	void roverMissionImpl_build_empty_lines_should_throw() {
		assertThrows(IllegalArgumentException.class,
				() -> new RoverMissionImpl().build(List.of()));
	}

	@Test
	void roverMissionImpl_build_invalid_line_count_should_throw() {
		List<String> lines = List.of("5 5", "1 2 N", "LMLM", "3 3 E");
		assertThrows(IllegalArgumentException.class,
				() -> new RoverMissionImpl().build(lines));
	}
}
