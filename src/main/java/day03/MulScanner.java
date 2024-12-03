package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulScanner {
    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day03.txt";

    public Integer determineMul() throws Exception {
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);

        List<String> substringList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    String extractedSubstring = matcher.group();
                    substringList.add(extractedSubstring);
                }
            }
            for (String substring : substringList) {
                MulCalculation mulCalculation = new MulCalculation(substring);
                mulCalculation.calculate();
                resultList.add(mulCalculation.calculate());
            }

            return resultList.stream().mapToInt(Integer::intValue).sum();
        }
    }
}
