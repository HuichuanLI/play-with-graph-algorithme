import java.util.ArrayList;
import java.util.Collections;

public class HamiltionLoopOptimsation {
    private GraphInterface G;
    private int visited;
    private int[] pre;
    private int end;

    public HamiltionLoopOptimsation(GraphInterface G) {

        this.G = G;
        visited = 0;
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0, G.V());

    }

    private boolean dfs(int v, int parent, int left) {
        left--;
        pre[v] = parent;
        visited += (1 << v);
        for (int w : G.adj(v)) {
            if ((visited & (1 << w)) == 0) {
                if (dfs(w, v, left)) return true;
            } else if (w == 0 && left == 0) {
                end = v;
                return true;
            }
        }
        visited -= (1 << v);
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

//    private boolean allVisited() {
//        for (int v = 0; v < G.V(); v++)
//            if (!visited[v]) return false;
//        return true;
//    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        HamiltionLoopOptimsation hl = new HamiltionLoopOptimsation(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        HamiltionLoopOptimsation h2 = new HamiltionLoopOptimsation(g2);
        System.out.println(h2.result());
    }
}
