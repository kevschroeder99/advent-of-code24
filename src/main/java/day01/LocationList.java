package day01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationList {

    public List<Integer> locationIds;

    public LocationList() {
        this.locationIds = new ArrayList<>();
    }

    public void add(int locationId) {
        this.locationIds.add(locationId);
    }

    public void remove(int locationId) {
        this.locationIds.remove(locationIds.indexOf(locationId));
    }

    public Integer getSmallestLocationId() {
        return Collections.min(this.locationIds);
    }

    public Integer getAppearanceInOtherList(Integer element, List<Integer> targetList) {
        int counter = 0;
        for (int i = 0; i < targetList.size(); i++) {
            int valueTargetList = targetList.get(i);
            if (valueTargetList == element) {
                counter++;
            }
        }
        return counter;
    }
}
