package day02;

import java.util.ArrayList;
import java.util.List;

public class Report {
    int tolerationCounter = 0;

    public boolean isReportSafe(List<Integer> intList, boolean isPartTwo) {
        boolean result = false;
        if (tolerationCounter > 1) {
            return false;
        }

        if ((isIncreasing(intList) || isDecreasing(intList)) && isDifferSmaller4(intList)) {
            return true;
        }

        if (isPartTwo) {
            // For Statement -> Part2
            for (int i = 0; i < intList.size(); i++) {
                List<Integer> tempList = new ArrayList<>(intList);
                tempList.remove(i);

                if ((isIncreasing(tempList) || isDecreasing(tempList)) && isDifferSmaller4(tempList)) {
                    tolerationCounter++;
                    intList.clear();
                    intList.addAll(tempList);
                    result = true;
                }
            }
        }

        return result;
    }

    public boolean isDecreasing(List<Integer> intList) {
        for (int i = 0; i < intList.size() - 1; i++) {
            if (intList.get(i) <= intList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isIncreasing(List<Integer> intList) {
        for (int i = 0; i < intList.size() - 1; i++) {
            if (intList.get(i) >= intList.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDifferSmaller4(List<Integer> intList) {
        //Difference: 0 < i & i+1 < 4
        for (int i = 0; i < intList.size() - 1; i++) {
            int diff = Math.abs(intList.get(i) - intList.get(i + 1));
            if (diff == 0 || diff >= 4) {
                return false;
            }
        }
        return true;
    }

}
