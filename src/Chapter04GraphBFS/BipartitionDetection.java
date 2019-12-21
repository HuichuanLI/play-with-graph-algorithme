package Chapter04GraphBFS;

import java.util.LinkedList;
import java.util.Queue;

public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;


    public BipartitionDetection(Graph g) {
        G = g;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            colors[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!bfs(v)) {
                    isBipartite = true;
                    break;
                }
            }
        }
    }

    public boolean bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        colors[v] = 0;
        while (!queue.isEmpty()) {
            int s = queue.remove();
            for (int i : G.adj(s)) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    colors[i] = 1 - colors[s];
                } else {
                    if (colors[i] == colors[s]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);
    }
}
