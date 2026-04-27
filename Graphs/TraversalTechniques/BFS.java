import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.Set;

public class BFS{
    private Map<Integer, List<Integer>> adjList;

    public BFS() {
        this.adjList = new HashMap<>();
    }

    public void addEdge(int src, int dest){
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(dest);
        //adjList.computeIfAbsent(dest, k -> new ArrayList<>()).add(src);
    }
    public void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        queue.add(start);
        while(!queue.isEmpty()){
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");
            List<Integer> neighbours = adjList.getOrDefault(currentNode, new ArrayList<>());
            for(int neighbour : neighbours){
                if(!visited.contains(neighbour)){
                    visited.add(neighbour);
                    queue.add(neighbour);
                
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        // #region agent log
        debugLog("pre-fix", "H1", "BFS.java:main", "Program started", "{}");
        // #endregion
        BFS graph = new BFS();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);

        graph.bfs(0);
    }
}