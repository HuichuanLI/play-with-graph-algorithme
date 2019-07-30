import java.util.ArrayList;

public class BipartitionDecetion {


    private GraphInterface G;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartie = true;

    public BipartitionDecetion(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            colors[i] = -1;
        }
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                if (!dfs(v, 0)) {
                    isBipartie = false;
                    break;
                }
        }
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v]) {
                return false;
            }
        }
        return true;

    }

    private boolean isBipartie() {
        return isBipartie;
    }

    private ArrayList<Integer>[] parition() {
        ArrayList<Integer>[] res = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            res[i] = new ArrayList<>();
        }

        if (this.isBipartie()) {
            for (int i = 0; i < colors.length; i++) {
//                System.out.println(i);
//                System.out.println(colors[i]);
                res[colors[i]].add(i);
            }
        } else {
            return res;
        }
        return res;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        BipartitionDecetion bipartitionDecetion = new BipartitionDecetion(g);
        System.out.println(bipartitionDecetion.isBipartie());

        ArrayList<Integer>[] res = bipartitionDecetion.parition();

        for (ArrayList<Integer> w : res) {
            System.out.println(w);
        }
        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        BipartitionDecetion bipartitionDecetion2 = new BipartitionDecetion(g2);
        System.out.println(bipartitionDecetion2.isBipartie());


    }
}
