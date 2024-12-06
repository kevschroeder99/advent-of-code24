package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuardPathTracker {
    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day06.txt";

    public Integer trackGuardPaths() throws IOException {
        List<List<Character>> map = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\n");

                for (String s : splitted) {
                    ArrayList<Character> row = new ArrayList<>();
                    for (char c : s.toCharArray()) {
                        row.add(c);
                    }
                    map.add(row);
                }
            }
        }
        Guard guard = new Guard();
        List<Integer> position = guard.findGuardPosition(map);
        return guard.moveGuard(map, position.get(0), position.get(1));
    }
}
