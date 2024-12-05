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

    private boolean isBeginning = true;

    // Nur die berücksichtigen, die vorher ein "do()" haben.
    // Wenn vorher ein "dont()" ist, dann nicht berücksichten
    // 48 (2*4 + 8*5)
    public Integer determineMulPart2() throws Exception {
        String input = "src/main/resources/inputs/input_day03.txt";
        List<String> substringList = new ArrayList<>();
        List<Integer> resultList = new ArrayList<>();

        String regexMul = "mul\\(\\d{1,3},\\d{1,3}\\)";
        String regexDo = "do\\(\\)";
        String regexDont = "don't\\(\\)";

        Pattern patternMul = Pattern.compile(regexMul);
        Pattern patternDo = Pattern.compile(regexDo);
        Pattern patternDont = Pattern.compile(regexDont);

        boolean isActive = true;

        try (BufferedReader br = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = br.readLine()) != null) {

                Matcher matcherMul = patternMul.matcher(line);
                Matcher matcherDo = patternDo.matcher(line);
                Matcher matcherDont = patternDont.matcher(line);

                int lastCheckedPosition = 0;

                while (matcherMul.find()) {
                    // Check for "don't()" between the last checked position and current "mul" match
                    if (matcherDont.find(lastCheckedPosition) && matcherDont.start() < matcherMul.start()) {
                        isActive = false;
                    }

                    // Check for "do()" between the last checked position and current "mul" match
                    if (matcherDo.find(lastCheckedPosition) && matcherDo.start() < matcherMul.start()) {
                        isActive = true;
                    }

                    // Process the current "mul()" only if active
                    if (isActive) {
                        String extractedSubstring = matcherMul.group();
                        //System.out.println(extractedSubstring);
                        substringList.add(extractedSubstring);
                    }

                    // Update the last checked position
                    lastCheckedPosition = matcherMul.end();
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
