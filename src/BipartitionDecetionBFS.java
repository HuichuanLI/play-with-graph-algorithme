import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartitionDecetionBFS {

    private Graph G;

    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;


    public BipartitionDecetionBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];


        for (int i = 0; i < G.V(); i++)
            colors[i] = -1;

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                if (!bfs(v)) {
                    isBipartite = false;
                    break;
                }
        }
    }

    private boolean bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int g : G.adj(v)) {
                if (!visited[g]) {
                    colors[g] = 1 - colors[v];
                    queue.add(g);
                    visited[g] = true;
                } else {
                    if (colors[g] == colors[v])
                        return false;
                }
            }
        }
        // 如果在上面的循环中，都没有发现矛盾，这次 bfs 可以返回 true
        return true;
    }
    public boolean isBipartite(){
        return isBipartite;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        BipartitionDecetionBFS bipartitionDecetion = new BipartitionDecetionBFS(g);
        System.out.println(bipartitionDecetion.isBipartite());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        BipartitionDecetionBFS bipartitionDecetion2 = new BipartitionDecetionBFS(g2);
        System.out.println(bipartitionDecetion2.isBipartite());


    }
}
