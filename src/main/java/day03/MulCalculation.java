package day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MulCalculation {

    public int value1;
    public int value2;

    public MulCalculation(String initializer) {
        setupCalculationAttributes(initializer);
    }

    private void setupCalculationAttributes(String initializer) {
        String regex = "\\d{1,3},\\d{1,3}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(initializer);
        if (matcher.find()) {
            String substring = matcher.group();
            String[] split = substring.split(",");
            this.value1 = Integer.parseInt(split[0]);
            this.value2 = Integer.parseInt(split[1]);

        }
    }

    public Integer calculate() {
        return value1 * value2;
    }

}
