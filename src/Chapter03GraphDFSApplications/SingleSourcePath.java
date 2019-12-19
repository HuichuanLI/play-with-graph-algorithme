package Chapter03GraphDFSApplications;


import java.util.ArrayList;
import java.util.Collections;

public class SingleSourcePath {
    private Graph G;
    private boolean[] visited;
    private int s;
    private int[] pre;

    public SingleSourcePath(Graph G, int s) {
        G.validateVertex(s);
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);

    }

    public boolean isConnectedTo(int t) {
        G.validateVertex(t);
        return visited[t];
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

    private void dfs(int v, int parent) {
        pre[v] = parent;
        visited[v] = true;
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w, v);

    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter03GraphDFSApplications/g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0 -> 6 : " + sspath.path(6));
        System.out.println("0 -> 5 : " + sspath.path(5));
    }

}
