public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph g) {
        this.G = g;
    }

    public boolean hasEulerLoop() {
        CC cc = new CC(G);
        if (cc.getCcount() > 1) return false;

        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 == 1) return false;

        }
        return true;
    }
}
