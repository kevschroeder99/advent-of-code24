package day02;

public class Report {

    public Integer[] levels;

    public Report(Integer[] levels) {
        this.levels = levels;
    }

    public boolean isReportSafe() {
        if ((isIncreasing() || isDecreasing()) && isDifferSmaller4()) {
            return true;
        }
        return false;
    }

    private boolean isDecreasing() {
        boolean isDecreasing = false;
        for (int i = 0; i < levels.length - 1; i++) {
            if (levels[i] > levels[i + 1]) {
                isDecreasing = true;
            } else {
                return false;
            }
        }
        return isDecreasing;
    }

    private boolean isIncreasing() {
        boolean isIncreasing = false;
        for (int i = 0; i < levels.length - 1; i++) {
            if (levels[i] < levels[i + 1]) {
                isIncreasing = true;
            } else {
                return false;
            }
        }
        return isIncreasing;
    }

    private boolean isDifferSmaller4() {
        //Unterschied zwischen Element i und i+1 Muss größer 0 sein und kleiner als 4.
        //Difference: 0 < i & i+1 < 4
        boolean isDifferSmaller = false;
        for (int i = 0; i < levels.length - 1; i++) {
            int num1 = levels[i];
            int num2 = levels[i + 1];
            if (Math.abs(num1 - num2) > 0 && Math.abs(num1 - num2) < 4) {
                isDifferSmaller = true;
            } else {
                return false;
            }
        }
        return isDifferSmaller;
    }


}
