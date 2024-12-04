package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SafeReportChecker {
    //_edge contains some edge cases. they are all safe.
    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day02.txt";

    public Integer dertmineSafeReports(boolean isPartTwo) throws IOException {
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" ");
                List<Integer> intList = Arrays.stream(splitted)
                        .map(Integer::valueOf)
                        .collect(Collectors.toList());

                Report report = new Report();
                if (report.isReportSafe(intList, isPartTwo)) {
                    counter++;
                }
            }
            return counter;
        }
    }
}
