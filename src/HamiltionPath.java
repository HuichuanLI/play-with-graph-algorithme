import java.util.ArrayList;
import java.util.Collections;

public class HamiltionPath {


    private GraphInterface G;
    private boolean[] visited;
    private int[] pre;
    private int end;
    private int s;



    public HamiltionPath(GraphInterface G,int s) {

        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(s, s, G.V());

    }

    private boolean dfs(int v, int parent, int left) {
        left--;
        pre[v] = parent;
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) return true;
            } else if (left == 0) {
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

//    private boolean allVisited() {
//        for (int v = 0; v < G.V(); v++)
//            if (!visited[v]) return false;
//        return true;
//    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        HamiltionPath hl = new HamiltionPath(g,0);
        System.out.println(hl.result());


        HamiltionPath h2 = new HamiltionPath(g,1);
        System.out.println(h2.result());
    }
}
