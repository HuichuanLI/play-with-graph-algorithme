import java.util.HashSet;

public class FindCutPoints {


    private GraphInterface G;

    private boolean[] visited;
    private int[] order;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        order = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new HashSet<>();
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;
        int child = 0;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);

                if (v != parent && low[w] >= order[v]) {
                    // v-w 是桥
                    res.add(v);
                }
                child++;
                if (v == parent && child > 1) {
                    res.add(v);
                }
            } else {
                if (w != parent) {
                    low[v] = Math.min(low[v], low[w]);
                }
            }
        }
    }

    public HashSet<Integer> result() {
        return res;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g3.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println(fc.result());

    }
}
