package Chapter04GraphBFS;

import Chapter04GraphBFS.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Path {
    private Graph G;
    private int s, t;

    private int[] pre;
    private boolean[] visited;

    public Path(Graph g, int s, int t) {


        g.validateVertex(s);
        g.validateVertex(t);
        this.G = g;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }

        bfs();
        for (boolean e : visited)
            System.out.print(e + " ");
        System.out.println();
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        if (s == t)
            return;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!visited[w]) {
                    visited[w] = true;
                    queue.add(w);
                    pre[w] = v;
                    if (w == t)
                        return;
                }
            }
        }
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {

        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected()) return res;

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

        Chapter04GraphBFS.Graph g = new Chapter04GraphBFS.Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        Chapter04GraphBFS.Path path = new Chapter04GraphBFS.Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Chapter04GraphBFS.Path path2 = new Chapter04GraphBFS.Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Chapter04GraphBFS.Path path3 = new Chapter04GraphBFS.Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }
}
