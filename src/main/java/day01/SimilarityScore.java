package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SimiliarityScore ist Part 2 of Day 1.
 */
public class SimilarityScore {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day01.txt";

    public static List<Integer> similarityList = new ArrayList<>();

    public Integer determineSimilarity() throws IOException {
        LocationList locations1 = new LocationList();
        LocationList locations2 = new LocationList();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\\s+");
                locations1.add(Integer.parseInt(splitted[0]));
                locations2.add(Integer.parseInt(splitted[1]));
            }
        }

        for (Integer value : locations1.locationIds) {
            int appearance = locations1.getAppearanceInOtherList(value, locations2.locationIds);
            similarityList.add(value * appearance);
        }

        return similarityList.stream().mapToInt(Integer::intValue).sum();
    }
}
