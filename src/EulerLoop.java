import java.util.ArrayList;
import java.util.Stack;

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

    public ArrayList<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();
        if (!hasEulerLoop()) return res;
        Graph g = (Graph) G.clone();
        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while (!stack.empty()) {
            if (g.degree(curv) != 0) {
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            } else {
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }
    public static void main(String[] args){

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        EulerLoop el = new EulerLoop(g);
        System.out.println(el.result());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g4.txt");
        EulerLoop el2 = new EulerLoop(g2);
        System.out.println(el2.result());
    }
}
