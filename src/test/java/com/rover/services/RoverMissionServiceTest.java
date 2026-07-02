package com.rover.services;

import com.rover.models.Plateau;
import com.rover.models.Rover;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoverMissionServiceTest {

	@Test
	void move_valid_sequence_should_return_history_and_final_position() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");
		Rover rover = new Rover.RoverBuilder()
				.id("R1")
				.positionAndDirection("1 2 N")
				.instructions("LMLM")
				.plateau(plateau)
				.build();

		RoverMissionService service = new RoverMissionService();
		List<String> history = service.move(rover);

		// initial + 4 instructions = 5 entries
		assertEquals(5, history.size());
		assertEquals("R1 : 1 2 N", history.getFirst());
		assertEquals("R1 : 0 1 S", history.getLast());
	}

	@Test
	void move_out_of_plateau_should_throw_illegal_state() {
		Plateau plateau = Plateau.PlateauBuilder.fromLine("0 0");
		Rover rover = new Rover.RoverBuilder()
				.id("R3")
				.positionAndDirection("0 0 N")
				.instructions("M")
				.plateau(plateau)
				.build();

		RoverMissionService service = new RoverMissionService();
		assertThrows(IllegalStateException.class, () -> service.move(rover));
	}

	@Test
	void printRoverPositionHistory_should_not_throw_and_format_entries() {
		RoverMissionService service = new RoverMissionService();
		List<String> history = List.of("R1 : 0 0 N", "R1 : 0 1 N");

		// should simply log the history without throwing
		assertDoesNotThrow(() -> service.printRoverPositionHistory(history));
	}
}
