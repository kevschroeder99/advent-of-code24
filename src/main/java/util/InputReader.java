package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {

    private InputReader() {
        throw new IllegalStateException("Utility class");
    }

    public static String readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        }
    }
}
