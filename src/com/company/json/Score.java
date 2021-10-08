package com.company.json;

public class Score {
    public String name;
    public int points;

    public Score() {}

    public Score(String name, int points) {
        this.name = name; this.points = points;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%-20s%5s", name, points);
    }
}