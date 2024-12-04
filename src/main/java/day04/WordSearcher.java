package day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordSearcher {

    public static final String PATH_TO_INPUT = "src/main/resources/inputs/input_day04.txt";
    private static final String XMAS = "MAS";
    private int wordCounter = 0;

    public Integer searchWordsPart1() throws IOException {
        ArrayList<ArrayList<Character>> table = getTable();
        //Suchen String XMAS
        //Zeile
        for (int i = 0; i < table.size(); i++) {
            //Spalte
            for (int j = 0; j < table.get(i).size(); j++) {
                if (table.get(i).get(j) == 'X') {
                    if (j >= 3) {
                        checkString(table.get(i).get(j - 1), table.get(i).get(j - 2), table.get(i).get(j - 3));
                    }

                    if (j + 3 < table.get(i).size()) {
                        checkString(table.get(i).get(j + 1), table.get(i).get(j + 2), table.get(i).get(j + 3));
                    }

                    if (i >= 3) {
                        checkString(table.get(i - 1).get(j), table.get(i - 2).get(j), table.get(i - 3).get(j));
                    }

                    if (i + 3 < table.size()) {
                        checkString(table.get(i + 1).get(j), table.get(i + 2).get(j), table.get(i + 3).get(j));
                    }

                    //Ab hier diagonal:
                    if (i >= 3 && j >= 3) {
                        checkString(table.get(i - 1).get(j - 1), table.get(i - 2).get(j - 2), table.get(i - 3).get(j - 3));
                    }

                    if (i >= 3 && j + 3 < table.get(i).size()) {
                        checkString(table.get(i - 1).get(j + 1), table.get(i - 2).get(j + 2), table.get(i - 3).get(j + 3));
                    }

                    if (i + 3 < table.size() && j >= 3) {
                        checkString(table.get(i + 1).get(j - 1), table.get(i + 2).get(j - 2), table.get(i + 3).get(j - 3));
                    }

                    if (i + 3 < table.size() && j + 3 < table.get(i).size()) {
                        checkString(table.get(i + 1).get(j + 1), table.get(i + 2).get(j + 2), table.get(i + 3).get(j + 3));
                    }
                }
            }

        }
        return wordCounter;
    }

    //Part Two
    public Integer searchWordsPart2() throws IOException {
        ArrayList<ArrayList<Character>> table = getTable();
        for (int i = 0; i < table.size(); i++) {
            //Spalte
            for (int j = 0; j < table.get(i).size(); j++) {
                //Immer wenn ich ein "A" finde, dann (-1,-1) & (-1,1) & (+1,-1) & (+1,+1) = MAS
                if (table.get(i).get(j) == 'A') {
                    try {
                        checkStringXShape(table.get(i).get(j), table.get(i - 1).get(j - 1), table.get(i + 1).get(j + 1), table.get(i - 1).get(j + 1), table.get(i + 1).get(j - 1));
                    } catch (IndexOutOfBoundsException e) {
                        //Ignore Exception
                    }

                }
            }
        }
        return wordCounter;
    }

    private ArrayList<ArrayList<Character>> getTable() throws IOException {
        ArrayList<ArrayList<Character>> table = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_INPUT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\n");

                for (String s : splitted) {
                    ArrayList<Character> row = new ArrayList<>();
                    for (char c : s.toCharArray()) {
                        row.add(c);
                    }
                    table.add(row);
                }
            }
        }
        return table;
    }

    private void checkStringXShape(Character cA, Character cTopLeft, Character cBotRight, Character cTopRight, Character cBotLeft) {
        StringBuilder sb = new StringBuilder();
        sb.append(cTopLeft).append(cA).append(cBotRight);
        if (XMAS.contentEquals(sb) || XMAS.contentEquals(sb.reverse())) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cTopRight).append(cA).append(cBotLeft);
            if (XMAS.contentEquals(sb2) || XMAS.contentEquals(sb2.reverse())) {
                wordCounter++;
            }
        }
    }

    private void checkString(Character character, Character character1, Character character2) {
        StringBuilder sb = new StringBuilder();
        sb.append(character).append(character1).append(character2);
        if (XMAS.contentEquals(sb)) {
            wordCounter++;
        }
    }
}


