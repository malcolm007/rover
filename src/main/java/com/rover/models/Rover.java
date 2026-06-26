package com.rover.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Rover {

    private Position position;
    private Direction direction;
    private final Plateau plateau;

    public Rover(Position position,
                 Direction direction,
                 Plateau plateau) {

        this.position = position;
        this.direction = direction;
        this.plateau = plateau;
    }



    @Override
    public String toString() {
        return position.x() + " "
                + position.y() + " "
                + direction;
    }
}