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
        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> updates = new ArrayList<>();
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

            //Create transitive dependency for each rule
            Map<Integer, List<Integer>> graph = new HashMap<>();

            for (String rule : rules) {
                String[] values = rule.split("\\|");
                int left = Integer.parseInt(values[0]);
                int right = Integer.parseInt(values[1]);

                if (!graph.containsKey(left)) {
                    graph.put(left, new ArrayList<>());
                }
                graph.get(left).add(right);

                for (Integer dependent : graph.getOrDefault(right, new ArrayList<>())) {
                    if (!graph.get(left).contains(dependent)) {
                        graph.get(left).add(dependent);
                    }
                }
            }

            //Just for Printing
            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                int left = entry.getKey();
                List<Integer> rights = entry.getValue();

                System.out.println(left + " depends on: " + rights);
            }

            boolean isUpdateValid = true;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < updates.size(); i++) {
                String[] valueSplitted = updates.get(i).split(",");
                for (int k = 0; k < valueSplitted.length - 1; k++) {
                    int updateValue = Integer.parseInt(valueSplitted[k]);
                    int nextValue = Integer.parseInt(valueSplitted[k + 1]);
                    List<Integer> dependencies = graph.get(updateValue);
                    if (dependencies == null || !dependencies.contains(nextValue)) {
                        isUpdateValid = false;
                        break;
                    }
                }
                if (isUpdateValid) {
                    result.add(updates.get(i));
                }
            }
            System.out.println(result);

            for(String s : result){
                String[] split = s.split(",");
                intlist.add(Integer.parseInt(split[split.length / 2]));
            }

            System.out.println(intlist.stream().mapToInt(i -> i).sum());
        }

        //5091
        return intlist.stream().mapToInt(i -> i).sum();
    }
}
