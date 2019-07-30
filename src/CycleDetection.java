import java.util.ArrayList;

public class CycleDetection {


    private GraphInterface G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    private boolean dfs(int v, int parent) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v))
                    return hasCycle;
            } else if (w != parent) {
                hasCycle = true;
                return hasCycle;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        CycleDetection cycledetection = new CycleDetection(g);
        System.out.println(cycledetection.hasCycle());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        CycleDetection cycledetection2 = new CycleDetection(g2);
        System.out.println(cycledetection2.hasCycle());
    }
}
