package Chapter04GraphBFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

// BFS --> Unweighted Single Source Shortest Path
public class USSSPath {

    private Graph G;
    private int s;

    private boolean[] visited;
    private int[] pre;
    private int[] dis;


    public USSSPath(Graph g, int s) {
        this.G = g;
        this.s = s;
        visited = new boolean[this.G.V()];
        dis = new int[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            dis[i] = 0;
            pre[i] = -1;
        }
        bfs(s);
        for (int i = 0; i < G.V(); i++)
            System.out.print(dis[i] + " ");
        System.out.println();
    }

    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        dis[s] = 0;
        pre[s] = s;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                    visited[w] = true;
                }
            }
        }

    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
    }


    public int dis(int t) {
        G.validateVertex(t);
        return dis[t];
    }


    public Iterable<Integer> path(int t) {

        ArrayList<Integer> res = new ArrayList<Integer>();
        if (!isConnectedTo(t)) return res;

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        USSSPath ussspath = new USSSPath(g, 0);
        System.out.println("0 -> 6 : " + ussspath.path(6));
        System.out.println("0 -> 6 : " + ussspath.dis(6));
    }

}
