package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                if (line.contains("|")) {
                    rules.add(line);
                }
                //Add Update
                if (line.contains(",")) {
                    updates.add(line);
                }
            }

            // erstelle graph mit rules
            Map<Integer, List<Integer>> graph = getRules(rules);


            //Ergebnisliste
            List<String> result = updateResultList(updates, graph);
            System.out.println(updateResultList(updates, graph));

            for (String s : result) {
                String[] split = s.split(",");
                intlist.add(Integer.parseInt(split[split.length / 2]));
            }
            System.out.println(intlist.size());
            System.out.println(intlist.stream().mapToInt(i -> i).sum());
        }

        //5091
        return intlist.stream().mapToInt(i -> i).sum();
    }

    private List<String> updateResultList(List<String> updates, Map<Integer, List<Integer>> graph) {
        List<String> result = new ArrayList<>();
        List<Integer> dependencies;
        boolean isUpdateValid = true;

        for (int i = 0; i < updates.size(); i++) {
            String[] valueSplitted = updates.get(i).split(",");
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
                result.add(updates.get(i));
            }
        }

        return result;
    }

    private Map<Integer, List<Integer>> getRules(List<String> rules) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (String rule : rules) {
            String[] values = rule.split("\\|");
            int left = Integer.parseInt(values[0]);
            int right = Integer.parseInt(values[1]);

            graph.compute(left, (l, r) -> {
                if (r == null) {
                    r = new ArrayList<>();
                }
                r.add(right);

                for (Integer dependent : graph.getOrDefault(right, new ArrayList<>())) {
                    if (!r.contains(dependent)) {
                        r.add(dependent);
                    }
                }
                return r;
            });
        }
        return graph;
    }
}
