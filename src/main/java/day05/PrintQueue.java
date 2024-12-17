package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrintQueue {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day05.txt";

    public Integer getAmountOfCorrectUpdates() throws IOException {
        List<String> rules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        List<Integer> intlist = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                //Add Rule
                if (line.trim().isEmpty()) {
                    continue;
                }
                if (line.contains("|")) {
                    rules.add(line);
                }
                //Add Update
                if (line.contains(",")) {
                    updates.add(line);
                }
            }

            // erstelle graph mit rules
            Map<Integer, Set<Integer>> graph = getRules(rules);


            //Ergebnisliste
            List<String> result = updateResultList(updates, graph);

            for (String s : result) {
                String[] split = s.split(",");
                intlist.add(Integer.parseInt(split[split.length / 2]));
            }
        }

        return intlist.stream().mapToInt(i -> i).sum();
    }

    private List<String> updateResultList(List<String> updates, Map<Integer, Set<Integer>> graph) {
        List<String> result = new ArrayList<>();
        Set<Integer> dependencies;
        for (String update : updates) {
            boolean isUpdateValid = true;
            String[] valueSplitted = update.split(",");
            for (int k = 0; k < valueSplitted.length - 1; k++) {
                Integer updateValue = Integer.parseInt(valueSplitted[k]);
                Integer nextValue = Integer.parseInt(valueSplitted[k + 1]);
                dependencies = graph.get(updateValue);
                if (dependencies == null || !dependencies.contains(nextValue)) {
                    isUpdateValid = false;
                    break;

                }
            }
            if (isUpdateValid) {
                result.add(update);
            }

        }
        return result;
    }

    private Map<Integer, Set<Integer>> getRules(List<String> rules) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (String rule : rules) {
            String[] values = rule.split("\\|");
            int left = Integer.parseInt(values[0]);
            int right = Integer.parseInt(values[1]);
            graph.computeIfAbsent(left, k -> new HashSet<>()).add(right);
        }
        return graph;
    }
}
