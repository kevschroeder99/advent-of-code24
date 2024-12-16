package day05;

import java.util.*;

public class Update {

    private List<String> orderingRules;
    private String update;

    public Update(String update) {
        this.update = update;
    }

    public List<String> getOrderingRules() {
        return orderingRules;
    }

    public void setOrderingRules(List<String> orderingRules) {
        this.orderingRules = orderingRules;
    }

    public int isUpdateCorrect(List<String> orderingRules) {
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
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (String rule : orderingRules) {
            String[] parts = rule.split("\\|");
            int from = Integer.parseInt(parts[0]);
            int to = Integer.parseInt(parts[1]);
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
        }
        System.out.println("Graph: " + graph);

        // Compute transitive closure of the graph
        Map<Integer, Set<Integer>> transitiveClosure = computeTransitiveClosure(graph);
        System.out.println("Transitive Closure: " + transitiveClosure);

        // Check the update list order
        Set<Integer> seen = new HashSet<>();
        for (int current : updateList) {
            // Check if any previously seen node is required to come after the current node
            for (int previous : seen) {
                if (transitiveClosure.getOrDefault(current, Collections.emptySet()).contains(previous)) {
                    System.out.println("Violation detected: " + current + " should not follow " + previous);
                    return 0;
                }
            }
            seen.add(current);
        }
        return updateList.get(updateList.size() / 2);
    }

    //BFS-Approach is way faster than 3 nested Loops.
    //https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    private static Map<Integer, Set<Integer>> computeTransitiveClosure(Map<Integer, List<Integer>> graph) {
        Map<Integer, Set<Integer>> closure = new HashMap<>();

        for (int node : graph.keySet()) {
            Queue<Integer> queue = new LinkedList<>();
            Set<Integer> reachable = new HashSet<>();

            queue.add(node);
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (!reachable.contains(current)) {
                    reachable.add(current);
                    queue.addAll(graph.getOrDefault(current, Collections.emptyList()));
                }
            }
            closure.put(node, reachable);
            System.out.println("Node " + node + " reachable: " + reachable);
        }
        return closure;
    }

//    private static Map<Integer, Set<Integer>> computeTransitiveClosure(Map<Integer, Set<Integer>> graph) {
//        Map<Integer, Set<Integer>> closure = new HashMap<>();
//
//        // Initialize closure with the original graph
//        for (int node : graph.keySet()) {
//            closure.put(node, new HashSet<>(graph.get(node)));
//        }
//
//        // Add transitive dependencies
//        for (int k : graph.keySet()) {
//            for (int i : graph.keySet()) {
//                for (int j : graph.keySet()) {
//                    if (closure.getOrDefault(i, Collections.emptySet()).contains(k) &&
//                            closure.getOrDefault(k, Collections.emptySet()).contains(j)) {
//                        closure.computeIfAbsent(i, x -> new HashSet<>()).add(j);
//                    }
//                }
//            }
//        }
//        return closure;
//    }
}
