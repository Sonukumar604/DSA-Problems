public class HamiltonianCycle {
    public static boolean hasHamiltonianCycle(int[][] graph) {
        int V = graph.length;
        int[] path = new int[V];
        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }
        path[0] = 0; // Start from the first vertex

        return hamiltonianCycleUtil(graph, path, 1);
    }
    private static boolean hamiltonianCycleUtil(int[][] graph, int[] path, int pos) {
        int V = graph.length;
        if (pos == V) {
            return graph[path[pos - 1]][path[0]] == 1;
        }
        for (int v = 1; v < V; v++) {
            if (isSafe(v, graph, path, pos)) {
                path[pos] = v;
                if (hamiltonianCycleUtil(graph, path, pos + 1)) {
                    return true;
                }
                path[pos] = -1; // Backtrack
            }
        }
        return false;
    }
    private static boolean isSafe(int v, int[][] graph, int[] path, int pos) {
        if (graph[path[pos - 1]][v] == 0) {
            return false;
        }
        for (int i = 0; i < pos; i++) {
            if (path[i] == v) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 0, 1},
            {1, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 0}
        };
        if (hasHamiltonianCycle(graph)) {
            System.out.println("Graph contains a Hamiltonian Cycle");
        } else {
            System.out.println("Graph does not contain a Hamiltonian Cycle");
        }
    }
}
