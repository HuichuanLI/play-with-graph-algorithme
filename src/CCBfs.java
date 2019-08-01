import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CCBfs {

    private Graph G;

    private int[] visited;
    private int cccount = 0;


    private ArrayList<Integer> order = new ArrayList<>();

    public CCBfs(Graph G) {
        this.G = G;
        visited = new int[G.V()];

        for (int v = 0; v < G.V(); v++)
            visited[v] = -1;

        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                bfs(v, cccount);
                cccount++;
            }

        }

    }

    public int getCcount() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return cccount;
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v > visited.length)
            throw new IllegalArgumentException("v is wrong");

        if (w < 0 || w > visited.length)
            throw new IllegalArgumentException("w is wrong");
        return visited[v] == visited[w];
    }


    private void bfs(int s, int cccount) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = cccount;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            order.add(v);
            for (int g : G.adj(v)) {
                if (visited[g] == -1) {
                    queue.add(g);
                    visited[g] = cccount;
                }
            }
        }

    }


    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }


    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        CCBfs cc = new CCBfs(g);
        System.out.println(cc.getCcount());


        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));


        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }

    }
}
