import java.util.ArrayList;
import java.util.Collections;

public class HamiltionLoop {


    private GraphInterface G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltionLoop(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0);


    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (w == 0 && allVisited()) {
                end = v;
                return true;
            }
        }
        visited[v] = false;
        return false;

    }

    public ArrayList<Integer> result() {

        ArrayList<Integer> res = new ArrayList<>();

        if (end == -1) return res;

        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    private boolean allVisited() {
        for (int v = 0; v < G.V(); v++)
            if (!visited[v]) return false;
        return true;
    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        HamiltionLoop hl = new HamiltionLoop(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        HamiltionLoop h2 = new HamiltionLoop(g2);
        System.out.println(h2.result());
    }
}
