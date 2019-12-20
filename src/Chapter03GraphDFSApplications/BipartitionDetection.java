package Chapter03GraphDFSApplications;

import java.util.ArrayList;

public class BipartitionDetection {
    private Graph G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            colors[i] = -1;
        }

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (!dfs(v, 0)) {
                    isBipartite = false;
                    break;
                }
    }

    private boolean dfs(int v, int color) {

        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v))
            if (!visited[w])
                if (!dfs(w, 1 - color))
                    return false;
                else if (colors[v] == colors[w])
                    return false;
        return true;
    }

    public boolean isBipartite() {
        return isBipartite;
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[2];
        if (!isBipartite()) {
            return res;
        }

        for (int i = 0; i < 2; i++)
            res[i] = new ArrayList<Integer>();

        for (int v = 0; v < G.V(); v++)
            res[colors[v]].add(v);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter03GraphDFSApplications/g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter03GraphDFSApplications/g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);
        ArrayList<Integer>[] comp = bipartitionDetection.components();

        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }

    }

}
