package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PrintQueue {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day05_test.txt";
    List<String> invalidList = new ArrayList<>();
    Map<Integer, Set<Integer>> graph = new HashMap<>();


    public Integer sortInvalidList() {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> sortedUpdateList = new ArrayList<>();

        for (String s : invalidList) {
            String[] splitted = s.split(",");
            Integer[] ints = new Integer[splitted.length];
            //Convert String into ints
            for (int i = 0; i < splitted.length; i++) {
                int value = Integer.parseInt(splitted[i]);
                ints[i] = value;
            }
            //For Each Integer get graph key and reachable nods and check:
            List<Integer> temp = new ArrayList<>();
            int j = 0;
            while (j < ints.length - 1) {
                //TODO: TestCase 3 funktioniert noch nicht. Nochmal schauen, ob die Order mit der vorherigen Zahl passt. Sonst nochmal tauschn.
                if (graph.get(ints[j]) == null || !graph.get(ints[j]).contains(ints[j + 1])) {
                    System.out.println("Ich bin falsch sortiert: " + ints[j] + " - " + ints[j + 1]);
                    //Sortieralgorithmus
                    int tempValue = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = tempValue;
                } else {
                    temp.add(ints[j]);
                    j++;
                }
            }

            if(j == ints.length - 1){
                temp.add(ints[j]);
            }
            System.out.println("Temp List: " + temp);


            //if isNextIntegerValue in reachable Nodes -> Add currentValue to newUpdateList
            sortedUpdateList.add(temp);
        }

        for (List<Integer> list : sortedUpdateList) {
            result.add(list.get(list.size() / 2));
        }

        System.out.println("Invalid List: " + invalidList);
        System.out.println("Graph: " + graph);

        return result.stream().mapToInt(Integer::intValue).sum();
    }


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
            } else {
                invalidList.add(update);
            }
        }
        return result;
    }

    private Map<Integer, Set<Integer>> getRules(List<String> rules) {

        for (String rule : rules) {
            String[] values = rule.split("\\|");
            int left = Integer.parseInt(values[0]);
            int right = Integer.parseInt(values[1]);
            graph.computeIfAbsent(left, k -> new HashSet<>()).add(right);
        }
        return graph;
    }
}
