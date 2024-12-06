package day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Guard {

    public List<Integer> findGuardPosition(List<List<Character>> map) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (map.get(i).get(j) == '^' || map.get(i).get(j) == '<' || map.get(i).get(j) == '>' || map.get(i).get(j) == 'v') {
                    result.add(i);
                    result.add(j);
                }
            }
        }
        return result;
    }

    private Character guardDirection = '^';

    public Integer moveGuard(List<List<Character>> map, Integer startingRow, Integer startingColumn) {
        String currentPosition = startingRow + "," + startingColumn;
        Set<String> positions = new HashSet<>();
        positions.add(currentPosition);

        while (true) {
            try {
                switch (guardDirection) {
                    case '^':
                        if (map.get(startingRow - 1).get(startingColumn) != '#') {
                            startingRow--;
                            currentPosition = startingRow + "," + startingColumn;
                        } else {
                            guardDirection = '>';
                            continue;
                        }
                        break;

                    case '>':
                        if (map.get(startingRow).get(startingColumn + 1) != '#') {
                            startingColumn++;
                            currentPosition = startingRow + "," + startingColumn;
                        } else {
                            guardDirection = 'v';
                            continue;
                        }
                        break;

                    case 'v':
                        if (map.get(startingRow + 1).get(startingColumn) != '#') {
                            startingRow++;
                            currentPosition = startingRow + "," + startingColumn;
                        } else {
                            guardDirection = '<';
                            continue;
                        }
                        break;

                    case '<':
                        if (map.get(startingRow).get(startingColumn - 1) != '#') {
                            startingColumn--;
                            currentPosition = startingRow + "," + startingColumn;
                        } else {
                            guardDirection = '^';
                            continue;
                        }
                        break;

                    default:
                        throw new IllegalStateException("");
                }

                if (!positions.contains(currentPosition)) {
                    positions.add(currentPosition);
                }

            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        return positions.size();
    }
}
