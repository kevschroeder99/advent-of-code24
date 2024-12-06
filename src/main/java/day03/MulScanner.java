package day03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
        String input = "src/main/resources/inputs/input_day03.txt";
        List<Integer> resultList = new ArrayList<>();

        String regexMul = "mul\\((\\d{1,3}),(\\d{1,3})\\)";
        String regexDo = "do\\(\\)";
        String regexDont = "don't\\(\\)";

        Pattern patternMul = Pattern.compile(regexMul);
        Pattern patternDo = Pattern.compile(regexDo);
        Pattern patternDont = Pattern.compile(regexDont);

        boolean isActive = true;

        Scanner scanner = new Scanner(new File(input));
        scanner.useDelimiter("\r\n");

        while (scanner.hasNext()) {
            String line = scanner.next().replace("\n", "");

            Matcher matcherMul = patternMul.matcher(line);
            Matcher matcherDo = patternDo.matcher(line);
            Matcher matcherDont = patternDont.matcher(line);

            // Alle Positionen von Dos und Donts finden
            List<Integer> dontPositions = new ArrayList<>();
            List<Integer> doPositions = new ArrayList<>();

            while (matcherDont.find()) {
                dontPositions.add(matcherDont.start());
            }

            while (matcherDo.find()) {
                doPositions.add(matcherDo.start());
            }

            while (matcherMul.find()) {
                int mulPosition = matcherMul.start();

                for (int dontPos : dontPositions) {
                    if (mulPosition > dontPos) {
                        isActive = false;
                        // Find the next do() after this don't()
                        for (int doPos : doPositions) {
                            if (doPos > dontPos) {
                                isActive = (mulPosition > doPos);
                                break;
                            }
                        }
                    }
                }

                if (isActive) {
                    int num1 = Integer.parseInt(matcherMul.group(1));
                    int num2 = Integer.parseInt(matcherMul.group(2));
                    resultList.add(num1 * num2);
                }
            }
        }
        return resultList.stream().mapToInt(Integer::intValue).sum();
    }
}
