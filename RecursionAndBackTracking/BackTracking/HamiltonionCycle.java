import java.util.*;

public class HamiltonionCycle {

    static int V;
    static int[][] graph;
    static int[] path;
    static int total = 0;

    public static void main(String[] args) {

        graph = new int[][]{
                {0,1,1,0},
                {1,0,1,1},
                {1,1,0,1},
                {0,1,1,0}
        };

        V = graph.length;
        path = new int[V];

        path[0] = 0;
        for (int i = 1; i < V; i++) path[i] = -1;

        System.out.println("Unique Hamiltonian Cycles:");
        backtrack(1);

        System.out.println("\nTotal Unique Cycles = " + total);
    }

    static void backtrack(int pos) {

        if (pos == V) {
            if (graph[path[pos - 1]][path[0]] == 1) {

                // symmetry check
                if (path[1] < path[V - 1]) {
                    printCycle();
                    total++;
                }
            }
            return;
        }

        for (int v = 1; v < V; v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;
                backtrack(pos + 1);
                path[pos] = -1;
            }
        }
    }

    static boolean isSafe(int v, int pos) {
        if (graph[path[pos - 1]][v] == 0) return false;
        for (int i = 0; i < pos; i++)
            if (path[i] == v) return false;
        return true;
    }

    static void printCycle() {
        for (int i = 0; i < V; i++)
            System.out.print(path[i] + " ");
        System.out.println(path[0]);
    }
}
