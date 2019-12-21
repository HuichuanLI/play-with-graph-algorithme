package Chapter04GraphBFS;

import java.util.LinkedList;
import java.util.Queue;

public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            pre[i] = -1;
        }

        for (int i = 0; i < G.V(); i++) {
            if (!visited[i]) {
                if (bfs(i)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                } else {
                    if (w != pre[v]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
