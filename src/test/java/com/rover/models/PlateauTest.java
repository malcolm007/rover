package com.rover.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlateauTest {

    @Test
    public void contains_should_accept_edges_and_inside() {
        Plateau plateau = Plateau.PlateauBuilder.fromLine("5 5");

        assertTrue(plateau.contains(new Position(0, 0)));
        assertTrue(plateau.contains(new Position(5, 5)));
        assertTrue(plateau.contains(new Position(3, 4)));

        assertFalse(plateau.contains(new Position(6, 0)));
        assertFalse(plateau.contains(new Position(-1, 0)));
    }

    @Test
    public void fromLine_should_validate_input() {
        assertThrows(IllegalArgumentException.class, () -> Plateau.PlateauBuilder.fromLine("5"));
        assertThrows(IllegalArgumentException.class, () -> Plateau.PlateauBuilder.fromLine("a b"));
    }
}

