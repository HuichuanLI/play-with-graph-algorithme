import java.util.ArrayList;

public class GraphDfs {


    private Graph G;
    private ArrayList<Integer> order = new ArrayList<>();
    private boolean [] visited;

    public GraphDfs(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        dfs(0);

    }
    private void dfs(int v){
        visited[v] = true;
        order.add(v);

        for(int w:G.adj(v)){
            if(!visited[w])
                dfs(w);
        }

    }

    public ArrayList<Integer> order() {
        return order;
    }
    
    public static void main(String [] args){
      Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
      GraphDfs graphdfs = new GraphDfs(g);
      System.out.println(graphdfs.order());
    }
}
