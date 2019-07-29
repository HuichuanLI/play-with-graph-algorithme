import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Path {


    private GraphInterface G;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int t;

    public Path(GraphInterface G, int s,int t) {
        this.s = s;
        this.G = G;
        this.t = t;
        pre = new int[G.V()];
        visited = new boolean[G.V()];
        for (int i = 0; i < pre.length; i++)
            pre[i] = -1;

        dfs(s, s);
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        if(v == t)
            return true;

        for (int w : G.adj(v)) {
            if (!visited[w])
                if(dfs(w, v))
                    return true;
        }
        return false;
    }

    public boolean isConnected() {
        return visited[t];
    }

    public Iterable<Integer> path() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!isConnected())
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
        Path path = new Path(g, 0,6);
        System.out.println("0->6:" + path.path());

        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());


    }
}
