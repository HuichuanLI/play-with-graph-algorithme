import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SingleSourcePath {


    private GraphInterface G;
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(GraphInterface G, int s) {
        this.s = s;
        this.G = G;
        pre = new int[G.V()];
        visited = new boolean[G.V()];
        for (int i = 0; i < pre.length; i++)
            pre[i] = -1;

        dfs(s, s);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;

        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(w, v);
        }
    }

    public boolean isConnected(int t) {
        return visited[t];
    }

    public Iterable<Integer> path(int t) {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected(t))
            return res;
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
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        SingleSourcePath sspath = new SingleSourcePath(g, 0);
        System.out.println("0->6:" + sspath.path(6));
        System.out.println("0->5:" + sspath.path(5));
    }
}
