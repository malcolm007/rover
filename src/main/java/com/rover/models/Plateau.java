package com.rover.models;

public class Plateau {

    private final int maxX;
    private final int maxY;

    private Plateau(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public boolean contains(Position position) {
        return position.x() >= 0
                && position.x() <= maxX
                && position.y() >= 0
                && position.y() <= maxY;
    }

    public static class PlateauBuilder {

        private int maxX;
        private int maxY;

        public PlateauBuilder maxX(int maxX) {
            this.maxX = maxX;
            return this;
        }

        public PlateauBuilder maxY(int maxY) {
            this.maxY = maxY;
            return this;
        }

        public static Plateau fromLine(String line) {
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Plateau must contain exactly 2 numbers");
            }
            try {
                return new Plateau(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Plateau coordinates must be integers");
            }
        }

        public Plateau build() {
            return new Plateau(maxX, maxY);
        }
    }
}