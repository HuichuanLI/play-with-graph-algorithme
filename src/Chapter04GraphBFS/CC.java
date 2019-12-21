package Chapter04GraphBFS;

import Chapter04GraphBFS.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CC {
    private Graph G;
    private int[] visited;
    private int cccount = 0;


    public CC(Graph g) {
        G = g;
        visited = new int[G.V()];
        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;
        for (int i = 0; i < G.V(); i++) {
            if (visited[i] == -1) {
                bfs(i, cccount);
                cccount++;
            }
        }
    }


    public boolean isConnected(int v, int w) {
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public int count() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return cccount;
    }

    public void bfs(int s, int cccount) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = cccount;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (visited[w] == -1) {
                    queue.add(w);
                    visited[w] = cccount;
                }
            }
        }
    }

    public ArrayList<Integer>[] components() {

        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++)
            res[i] = new ArrayList<Integer>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter04GraphBFS/g.txt");
        CC cc = new CC(g);

        System.out.println(cc.count());

        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(5, 6));

        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + " : ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }

}
