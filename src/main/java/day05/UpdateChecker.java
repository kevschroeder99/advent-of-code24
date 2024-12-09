package day05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateChecker {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day05.txt";

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

            //System.out.println(orderingRules);
            //System.out.println(updates);

            for (int i = 0; i < updates.size(); i++) {
                Update update = new Update(orderingRules, updates.get(i));
                results.add(update.isUpdateCorrect());
            }

        }
        return results.stream().mapToInt(Integer::intValue).sum();
    }

}
