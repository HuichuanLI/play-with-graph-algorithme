package Chapter03GraphDFSApplications;

public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph g) {
        G = g;
        visited = new boolean[g.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    public boolean dfs(int s, int parent) {
        visited[s] = true;
        for (int w : G.adj(s)) {
            if (!visited[w]) {
                if (dfs(w, s))
                    return true;
            } else {
                if (w != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter03GraphDFSApplications/g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter03GraphDFSApplications/g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
