package Chapter04GraphBFS;

import Chapter04GraphBFS.Graph;
import Chapter04GraphBFS.SingleSourcePath;

public class AllPairsPath {
    private Graph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph g) {
        G = g;
        paths = new SingleSourcePath[G.V()];
        for (int i = 0; i < g.V(); i++) {
            paths[i] = new SingleSourcePath(g, i);
        }
    }

    public boolean isConnectedTo(int s, int t) {
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    public Iterable<Integer> path(int s, int t) {
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
