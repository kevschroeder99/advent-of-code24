package day02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class SafeReportChecker {
    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day02.txt";

    public Integer dertmineSafeReports() throws IOException {
        int counter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" ");
                Integer[] reportList = Arrays.stream(splitted)
                        .map(Integer::valueOf).toArray(Integer[]::new);

                Report report = new Report(reportList);
                if (report.isReportSafe()) {
                    counter++;
                }
            }
            return counter;
        }
    }
}
