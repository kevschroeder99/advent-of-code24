package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalibrationResultChecker {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day07.txt";

    private final static List<String> operandsPartOne = Arrays.asList("+", "*");
    private final static List<String> operandsPartTwo = Arrays.asList("+", "*", "|");


    public long checkCalibrationResults(boolean isPartOne) throws IOException {
        List<Long> resultList = new ArrayList<>();
        List<String> expressions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Long> valueList = new ArrayList<>();
                String[] equations = line.split(":");
                //[0] -> Result

                String[] values = equations[1].trim().split(" ");

                for (String value : values) {
                    valueList.add(Long.parseLong(value));
                }

                if (isPartOne) {
                    expressions = generateExpressions(valueList, operandsPartOne);
                } else {
                    expressions = generateExpressions(valueList, operandsPartTwo);
                }
                List<Long> longResults = createLongResults(expressions);

                //Prüfen ob in der Liste enthalten ist
                if (longResults.contains(Long.parseLong(equations[0]))) {
                    resultList.add(Long.parseLong(equations[0]));
                }

            }
        }
        return resultList.stream().mapToLong(Long::longValue).sum();
    }

    private List<Long> createLongResults(List<String> expressions) {
        List<Long> results = new ArrayList<>();

        for (String expression : expressions) {
            StringBuilder sb = new StringBuilder();
            long value = 0;
            char lastOperator = '+';
            boolean isConcatMode = false;

            for (int i = 0; i < expression.length(); i++) {
                Character c = expression.charAt(i);
                if (Character.isDigit(c)) {
                    sb.append(c);
                } else {
                    long number = Long.parseLong(sb.toString());
                    sb = new StringBuilder();
                    if (isConcatMode) {
                        value = Long.parseLong(value + String.valueOf(number));
                    } else {
                        if (lastOperator == '+') {
                            value += number;
                        } else if (lastOperator == '*') {
                            value *= number;
                        }
                    }

                    if (c == '|') {
                        isConcatMode = true;
                    } else {
                        isConcatMode = false;
                        lastOperator = c;
                    }
                }
            }
            if (sb.length() > 0) {
                long number = Long.parseLong(sb.toString());
                if (isConcatMode) {
                    value = Long.parseLong(value + String.valueOf(number));
                } else if (lastOperator == '+') {
                    value += number;
                } else if (lastOperator == '*') {
                    value *= number;
                }
            }
            results.add(value);
        }
        return results;
    }

    public static List<String> generateExpressions(List<Long> values, List<String> operators) {
        List<String> result = new ArrayList<>();
        buildExpressions(result, new StringBuilder(), values, operators, 0);
        return result;
    }

    private static void buildExpressions(
            List<String> result, StringBuilder currentExpression,
            List<Long> values, List<String> operators, int index) {

        if (index == values.size() - 1) {
            // Base case: append the last value and store the result
            currentExpression.append(values.get(index));
            result.add(currentExpression.toString());
            currentExpression.delete(currentExpression.length() - String.valueOf(values.get(index)).length(), currentExpression.length());
            return;
        }

        // Append the current value
        currentExpression.append(values.get(index));

        // Try all operators for the next position
        for (String operator : operators) {
            currentExpression.append(operator);
            buildExpressions(result, currentExpression, values, operators, index + 1);
            // Backtrack: remove the operator
            currentExpression.deleteCharAt(currentExpression.length() - 1);
        }

        // Backtrack: remove the current value
        currentExpression.delete(currentExpression.length() - String.valueOf(values.get(index)).length(), currentExpression.length());
    }


}


