import day01.DistanceTracker;
import day01.SimilarityScore;
import day02.SafeReportChecker;
import day03.MulScanner;
import day04.WordSearcher;

public class Main {

    public static void main(String[] args) throws Exception {

        // Day 1
        DistanceTracker distanceTracker = new DistanceTracker();
        System.out.println("Day 1#1: " + distanceTracker.getDistance());
        SimilarityScore similarityScore = new SimilarityScore();
        System.out.println("Day 1#2: " + similarityScore.determineSimilarity());

        // Day 2
        SafeReportChecker safeReportChecker = new SafeReportChecker();
        System.out.println("Day 2#1: " + safeReportChecker.dertmineSafeReports(false));
        System.out.println("Day 2#2: " + safeReportChecker.dertmineSafeReports(true));

        // Day3
        MulScanner mulScanner = new MulScanner();
        System.out.println("Day 3#1: " + mulScanner.determineMul());
        MulScanner mulScanner2 = new MulScanner();
        System.out.println("Day 3#2: " + mulScanner2.determineMulPart2());

        // Day4
        WordSearcher wordSearcher = new WordSearcher();
        System.out.println("Day 4#1: " + wordSearcher.searchWordsPart1());
        WordSearcher wordSearcher2 = new WordSearcher();
        System.out.println("Day 4#2: " + wordSearcher2.searchWordsPart2());

    }
}
