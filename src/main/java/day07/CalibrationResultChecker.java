package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalibrationResultChecker {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day07_test.txt";

    //private final static List<String> operands = Arrays.asList("+", "*");
    private final static List<String> operands = Arrays.asList("+", "*", "|");


    public long checkCalibrationResults() throws IOException {
        List<Long> resultList = new ArrayList<>();

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

                List<String> expressions = generateExpressions(valueList, operands);
                List<Long> longExpressions = createLongExpressions(expressions);

                //Prüfen ob in der Liste enthalten ist
                if (longExpressions.contains(Long.parseLong(equations[0]))) {
                    resultList.add(Long.parseLong(equations[0]));
                }

            }
        }
        return resultList.stream().mapToLong(Long::longValue).sum();
    }

    private List<Long> createLongExpressions(List<String> expressions) {
        List<Long> results = new ArrayList<>();

        for (String expression : expressions) {
            StringBuilder sb = new StringBuilder();
            long result = 0;
            char operation = '+';

            for(char c : (expression + "+").toCharArray()) {
                if(Character.isDigit(c)) {
                    sb.append(c);
                } else if(c == '|'){
                    while (Character.isDigit(c)) {
                        sb.append(c);
                        //c = expression.charAt(++i);
                    }
                    continue;
                }
                else {
                    long number = Long.parseLong(sb.toString());
                    switch(operation) {
                        case '+':
                            result += number;
                            break;
                        case '*':
                            result *= number;
                            break;

                    }
                    operation = c;
                    sb = new StringBuilder();
                }
            }
            results.add(result);
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


