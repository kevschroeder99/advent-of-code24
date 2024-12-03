package day03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulScanner {

    public Integer determineMul() throws Exception {
        String input = "src/main/resources/inputs/input_day03.txt";
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        Pattern pattern = Pattern.compile(regex);

        List<String> substringList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
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

    // Nur die berücksichtigen, die vorher ein "do()" haben.
    // Wenn vorher ein "dont()" ist, dann nicht berücksichten
    // 48 (2*4 + 8*5)
    public Integer determineMulPart2() throws Exception {
        String input = "src/main/resources/inputs/input_day03_part2_test.txt";

        List<String> substringList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();

        String regexOk = "do\\(.*?\\)?mul\\(\\d{1,3},\\d{1,3}\\)";
        String regexStart = "(?<!dont\\().*mul\\(\\d{1,3},\\d{1,3}\\)";

        Pattern patternOk = Pattern.compile(regexOk);
        Pattern patternStart = Pattern.compile(regexStart);

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {
                    System.out.println(line);

                    if(patternStart.matcher(line).find()){

                    }
                    Matcher matcher = patternOk.matcher(line);
                    while (matcher.find()) {
                        String extractedSubstring = matcher.group();
                        System.out.println(extractedSubstring);
                        substringList.add(extractedSubstring);
                    }
                    for (String substring : substringList) {
                        MulCalculation mulCalculation = new MulCalculation(substring);
                        mulCalculation.calculate();
                        resultList.add(mulCalculation.calculate());
                }
            }

        }
        return resultList.stream().mapToInt(Integer::intValue).sum();
    }
}
