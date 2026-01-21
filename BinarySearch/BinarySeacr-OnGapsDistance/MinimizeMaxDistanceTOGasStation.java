import java.util.Collections;
import java.util.PriorityQueue;

public class MinimizeMaxDistanceTOGasStation {

    // Brute-force approach
    public double minmaxGasDistBruteForce(int[] stations, int k) {
        int n = stations.length;
        int[] howMany = new int[n - 1]; // Stores number of stations placed between original stations

        for (int gasStations = 1; gasStations <= k; gasStations++) {
            double maxSection = -1.0;
            int maxIndex = -1;

            // Find the section with the largest distance between stations
            for (int i = 0; i < n - 1; i++) {
                double diff = stations[i + 1] - stations[i];
                double sectionLength = diff / (double) (howMany[i] + 1);
                if (sectionLength > maxSection) {
                    maxSection = sectionLength;
                    maxIndex = i;
                }
            }
            // Place a gas station in the section with the largest distance
            howMany[maxIndex]++;
        }

        double maxAns = -1.0;
        for (int i = 0; i < n - 1; i++) {
            double diff = stations[i + 1] - stations[i];
            double sectionLength = diff / (double) (howMany[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }

        return maxAns;
    }

    // Priority Queue approach
    public double minmaxGasDistPQ(int[] stations, int k) {
        int n = stations.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());

        // Initialize PQ with initial sections
        for (int i = 0; i < n - 1; i++) {
            pq.add(new Pair(stations[i+1] - stations[i], 1));
        }

        // Place k stations
        for (int i = 0; i < k; i++) {
            Pair current = pq.poll();
            current.stationsInBetween++;
            pq.add(current);
        }

        return pq.peek().getSectionLength();
    }

    // Helper class for Priority Queue
    static class Pair implements Comparable<Pair> {
        double initialDist;
        int stationsInBetween;

        Pair(double dist, int stations) {
            this.initialDist = dist;
            this.stationsInBetween = stations;
        }

        double getSectionLength() {
            return initialDist / (double) stationsInBetween;
        }

        @Override
        public int compareTo(Pair other) {
            return Double.compare(this.getSectionLength(), other.getSectionLength());
        }
    }

    // Binary Search approach (Corrected)
    private boolean canPlace(int[] stations, int k, double dist) {
        int requiredStations = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            double gap = stations[i + 1] - stations[i];
            requiredStations += Math.ceil(gap / dist) - 1;
        }
        return requiredStations <= k;
    }

    public double minmaxGasDistBinarySearch(int[] stations, int k) {
        double low = 0;
        double high = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            high = Math.max(high, stations[i + 1] - stations[i]);
        }

        while (high - low > 1e-7) {
            double mid = low + (high - low) / 2;
            if (canPlace(stations, k, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return high;
    }

    public static void main(String[] args) {
        MinimizeMaxDistanceTOGasStation obj = new MinimizeMaxDistanceTOGasStation();
        int[] stations = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 9;
        System.out.printf("Brute Force: The minimized maximum distance is: %.6f\n", obj.minmaxGasDistBruteForce(stations, k));
        System.out.printf("Priority Queue: The minimized maximum distance is: %.6f\n", obj.minmaxGasDistPQ(stations, k));
        System.out.printf("Binary Search: The minimized maximum distance is: %.6f\n", obj.minmaxGasDistBinarySearch(stations, k));
    }
}
