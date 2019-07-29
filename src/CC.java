import java.util.ArrayList;

public class CC {


    private GraphInterface G;
    private int[] visited;
    private int ccount = 0;

    public CC(GraphInterface G) {

        this.G = G;
        visited = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            visited[v] = -1;
        }
        for (int v = 0; v < G.V(); v++)
            if (visited[v] == -1) {
                dfs(v, ccount);
                ccount++;
            }

    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v > visited.length)
            throw new IllegalArgumentException("v is wrong");

        if (w < 0 || w > visited.length)
            throw new IllegalArgumentException("w is wrong");
        return visited[v] == visited[w];
    }


    public int getCcount() {
        for (int e : visited)
            System.out.print(e + " ");
        System.out.println();
        return ccount;
    }

    private void dfs(int v, int ccount) {
        visited[v] = ccount;

        for (int w : G.adj(v)) {
            if (visited[w] == -1)
                dfs(w, ccount);
        }
    }

    public ArrayList<Integer>[] components() {
        ArrayList<Integer>[] res = new ArrayList[ccount];
        for (int i = 0; i < ccount; i++) {
            res[i] = new ArrayList<>();
        }

        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        CC cc = new CC(g);
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
