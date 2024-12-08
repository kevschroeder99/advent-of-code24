package day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalibrationResultChecker {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day07_test.txt";

    private final static List<String> operands = Arrays.asList("+", "*");

    public long checkCalibrationResults() throws IOException {
        List<Long> resultList = new ArrayList<>();
        List<List<Long>> inputList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                List<Long> valueList = new ArrayList<>();
                System.out.println(line);
                String[] equations = line.split(":");
                //[0] -> Result
                System.out.println(equations[0]);

                String[] values = equations[1].trim().split(" ");

                for (String value : values) {
                    valueList.add(Long.parseLong(value));
                }

                List<String> expressions = generateExpressions(valueList, operands);
                List<Long> longExpressions = createLongExpressions(expressions);

                System.out.println(expressions);
                //Prüfen ob equations[0] in der Liste enthalten ist
//                if (longExpressions.contains(Long.parseLong(equations[0]))) {
//                    resultList.add(Long.parseLong(equations[0]));
//                }

            }
        }
        return resultList.stream().mapToLong(Long::longValue).sum();
    }

    private List<Long> createLongExpressions(List<String> expressions) {
        List<Long> results = new ArrayList<>();

        for(String expression : expressions) {
            //Split String by nonDigits
            //Put non Digit into tempList
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


