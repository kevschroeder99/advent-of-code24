import day01.DistanceTracker;
import day01.SimilarityScore;

public class Main {

    public static void main(String[] args) throws Exception {

        // Day 1
        DistanceTracker distanceTracker = new DistanceTracker();
        System.out.println("Day 1#1: " + distanceTracker.getDistance());
        SimilarityScore similarityScore = new SimilarityScore();
        System.out.println("Day 1#2: " + similarityScore.determineSimilarity());

    }
}
