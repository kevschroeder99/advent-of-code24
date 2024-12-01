package day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Distance Tracker ist Part 1 of Day 1.
 */
public class DistanceTracker {

    List<Integer> distanceList = new ArrayList<>();

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day01.txt";

    public Integer getDistance() throws IOException {
        LocationList locations1 = new LocationList();
        LocationList locations2 = new LocationList();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\\s+");
                locations1.add(Integer.parseInt(splitted[0]));
                locations2.add(Integer.parseInt(splitted[1]));
            }
        }

        getDistanceFromLocations(locations1, locations2);
        return distanceList.stream().mapToInt(Integer::intValue).sum();
    }

    private void getDistanceFromLocations(LocationList locations1, LocationList locations2) {
        while (!locations1.locationIds.isEmpty() && !locations2.locationIds.isEmpty()) {
            int locationId1 = locations1.getSmallestLocationId();
            int locationId2 = locations2.getSmallestLocationId();
            if (locationId1 > locationId2) {
                distanceList.add(locationId1 - locationId2);
            } else {
                distanceList.add(locationId2 - locationId1);
            }
            locations1.remove(locations1.getSmallestLocationId());
            locations2.remove(locations2.getSmallestLocationId());
        }
    }
}
