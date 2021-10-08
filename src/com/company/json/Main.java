package com.company.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main {
    static Path top5File = Paths.get("tpofive.json");

    public static void main(String[] args) throws IOException {
        addScore("James Gosling", 300);
        addScore("Anders Hejlsberg", 500);
        addScore("Chris Lattner", 400);
        addScore("Brendan Eich", 200);
        addScore("Bjarne Stroustrup", 600);
        addScore("Guido van Rossum", 100);
    }

    static void addScore(String name, int points) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        // Read scores from file
        TopFive topFive;
        try {
            topFive = objectMapper.readValue(top5File.toFile(), TopFive.class);
        } catch (Exception e) {
            // File not found, create new empty TopFive
            topFive = new TopFive();
        }

        // add new score
        topFive.scores.add(new Score(name, points));

        // sort scores and slice top 5
        topFive.scores = topFive.scores.stream()
                .sorted(Comparator.comparing(Score::getPoints).reversed())
                .limit(5)
                .collect(Collectors.toList());

        // write scores to file
        objectMapper.writeValue(top5File.toFile(), topFive);

        // print scores
        System.out.println(topFive);
    }
}
