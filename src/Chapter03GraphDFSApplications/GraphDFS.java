package Chapter03GraphDFSApplications;


import Chapter02GraphDFS.AdjSet;
import Chapter02GraphDFS.Graph;

import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> order = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    private Graph G;
    private boolean[] visited;

    public GraphDFS(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(v);
        }
    }

    private void dfs(int v) {

        visited[v] = true;
        order.add(v);
        for (int w : G.adj(v))
            if (!visited[w])
                dfs(w);

        post.add(v);
    }



    public Iterable<Integer> pre() {
        return order;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {

        Graph g = new AdjSet("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println("DFS preOrder : " + graphDFS.pre());
        System.out.println("DFS postOrder : " + graphDFS.post());
    }

}
