import java.util.ArrayList;

public class GraphDfs {


    private Graph G;
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private boolean[] visited;

    public GraphDfs(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(0);
    }

    private void dfs(int v) {
        visited[v] = true;
        pre.add(v);

        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(w);
        }
        post.add(v);

    }

    public ArrayList<Integer> pre() {
        return pre;
    }

    public ArrayList<Integer> post() {
        return post;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        GraphDfs graphdfs = new GraphDfs(g);
        System.out.println(graphdfs.pre());
        System.out.println(graphdfs.post());
    }
}
