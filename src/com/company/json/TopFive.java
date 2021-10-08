package com.company.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopFive {
    public List<Score> scores = new ArrayList<>();

    @Override
    public String toString() {
        return "\033[31m** TOP 5 SCORE **\033[0m\n" + scores.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}