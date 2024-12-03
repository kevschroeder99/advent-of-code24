import day01.DistanceTracker;
import day01.SimilarityScore;
import day02.SafeReportChecker;

public class Main {

    public static void main(String[] args) throws Exception {

        // Day 1
        DistanceTracker distanceTracker = new DistanceTracker();
        System.out.println("Day 1#1: " + distanceTracker.getDistance());
        SimilarityScore similarityScore = new SimilarityScore();
        System.out.println("Day 1#2: " + similarityScore.determineSimilarity());

        // Day 2
        SafeReportChecker safeReportChecker = new SafeReportChecker();
        //567 ist too high
        System.out.println("Day 2#1: " + safeReportChecker.dertmineSafeReports());

    }
}
