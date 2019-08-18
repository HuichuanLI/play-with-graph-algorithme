import java.util.ArrayList;

public class FindBridges {


    private GraphInterface G;

    private boolean[] visited;
    private int[] order;
    private int[] low;
    private int cnt;

    private  ArrayList<Edge> res;

    public FindBridges(GraphInterface G) {

        this.G = G;
        visited = new boolean[G.V()];
        order = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new ArrayList<>();
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(0, v);
    }

    private void dfs(int v, int parent) {
        visited[v] = true;
        order[v] = cnt;
        low[v] = cnt;
        cnt++;

        for (int w : G.adj(v)) {
            if (!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v],low[w]);
                if(low[w] > order[v]){
                    // v-w 是桥
                    res.add(new Edge(v,w));
                }
            }else{
                if(w != parent){
                    low[v] = Math.min(low[v],low[w]);
                }
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }


    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g3.txt");
        FindBridges fb = new FindBridges(g);
        System.out.println(fb.result());

    }
}
