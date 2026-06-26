package com.test.rover.models;

public class Plateau {

    private final int maxX;
    private final int maxY;

    public Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean contains(Position position) {
        return position.x() >= 0
                && position.x() <= maxX
                && position.y() >= 0
                && position.y() <= maxY;
    }
}