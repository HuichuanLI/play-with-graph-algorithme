import java.util.ArrayList;

public class CC {


    private GraphInterface G;
    private boolean[] visited;
    private int ccount = 0;

    public CC(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v]){
                dfs(v);
                ccount++;
            }

    }

    public int getCcount() {
        return ccount;
    }

    private void dfs(int v) {
        visited[v] = true;

        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(w);
        }


    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        CC cc = new CC(g);
        System.out.println(cc.getCcount());
    }
}
