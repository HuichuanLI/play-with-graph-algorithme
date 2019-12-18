package Chapter02GraphDFS;


import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> order = new ArrayList<>();
    private Graph G;
    private boolean[] visited;

    public GraphDFS(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        dfs(0);
    }

    private void dfs(int v) {

        visited[v] = true;
        order.add(v);
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.order());
    }

}
