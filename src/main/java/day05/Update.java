package day05;

import java.util.*;

public class Update {

    private List<String> orderingRules;
    private String update;

    public Update(List<String> orderingRules, String update) {
        this.orderingRules = orderingRules;
        this.update = update;
    }

    public int isUpdateCorrect() {
        String[] updateSplitted = this.update.split(",");
        List<Integer> updateList = new ArrayList<>();
        //String[] orderingRules = this.orderingRules.toArray(String[]::new);
        //System.out.println("Ordering Rules: " + orderingRules);


        for (int i = 0; i < updateSplitted.length; i++) {
            updateList.add(Integer.parseInt(updateSplitted[i]));
            //orderingRules.
            //System.out.println("Current : " + current);
        }
       // System.out.println("UpdateList: " + updateList);

        // Build the graph from the ordering rules
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (String rule : orderingRules) {
            String[] parts = rule.split("\\|");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            graph.computeIfAbsent(from, k -> new HashSet<>()).add(to);
        }

        // Compute transitive closure of the graph
        Map<Integer, Set<Integer>> transitiveClosure = computeTransitiveClosure(graph);

        // Check the update list order
        Set<Integer> seen = new HashSet<>();
        for (int current : updateList) {
            // Check if any previously seen node is required to come after the current node
            for (int previous : seen) {
                if (transitiveClosure.getOrDefault(current, Collections.emptySet()).contains(previous)) {
                    return 0; // Violation found
                }
            }
            seen.add(current);
        }
        return updateList.get(updateList.size() / 2);
    }

    private static Map<Integer, Set<Integer>> computeTransitiveClosure(Map<Integer, Set<Integer>> graph) {
        Map<Integer, Set<Integer>> closure = new HashMap<>();

        // Initialize closure with the original graph
        for (int node : graph.keySet()) {
            closure.put(node, new HashSet<>(graph.get(node)));
        }

        // Add transitive dependencies
        for (int k : graph.keySet()) {
            for (int i : graph.keySet()) {
                for (int j : graph.keySet()) {
                    if (closure.getOrDefault(i, Collections.emptySet()).contains(k) &&
                            closure.getOrDefault(k, Collections.emptySet()).contains(j)) {
                        closure.computeIfAbsent(i, x -> new HashSet<>()).add(j);
                    }
                }
            }
        }
        return closure;
    }
}
