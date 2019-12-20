package Chapter04GraphBFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

// Time Complexity: O(V+E)
public class GraphBFS {

    private Graph g;
    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph g) {
        this.g = g;
        visited = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!visited[v])
                bfs(v);
        }
    }

    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);

            for (int w : g.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order : " + graphBFS.order());
    }
}
