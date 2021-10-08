package com.company.objectstream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Collectors;


public class Main {
    static Path top5File = Paths.get("tpofive.dat");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        addScore("James Gosling", 300);
        addScore("Anders Hejlsberg", 500);
        addScore("Chris Lattner", 400);
        addScore("Brendan Eich", 200);
        addScore("Bjarne Stroustrup", 600);
        addScore("Guido van Rossum", 100);
    }

    static void addScore(String name, int points) throws IOException, ClassNotFoundException {
        // Read scores from file
        TopFive topFive;
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(top5File))){
            topFive = (TopFive) ois.readObject();
        } catch (IOException e){
            // There are no scores
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
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(top5File));
        oos.writeObject(topFive);

        // print scores
        System.out.println(topFive);
    }
}
