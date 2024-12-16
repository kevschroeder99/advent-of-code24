package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateChecker {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day05_test.txt";

    public Integer checkUpdates() throws IOException {
        List<String> orderingRules = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        List<Integer> results = new ArrayList<>();
        boolean isSecondList = false;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    isSecondList = true;
                    continue;
                }

                if (isSecondList) {
                    updates.add(line);
                } else {
                    orderingRules.add(line);
                }
            }


            for (int i = 0; i < updates.size(); i++) {
                // long starttime = System.currentTimeMillis();
                Update update = new Update(updates.get(i));
                //update.setOrderingRules(orderingRules);
                System.out.println("Update: " + updates.get(i));
                System.out.println("Ordering Rules: " + orderingRules);

                results.add(update.isUpdateCorrect(orderingRules));
                //long endtime = System.currentTimeMillis();
                // System.out.println("Execution time: " + (endtime - starttime) + "ms");
            }

        }
        return results.stream().mapToInt(Integer::intValue).sum();
    }

}
