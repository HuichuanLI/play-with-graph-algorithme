import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    private Graph G;

    private boolean[] visited;

    private ArrayList<Integer> order = new ArrayList<>();

    public GraphBFS(Graph G) {
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                bfs(v);
        }

    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while(!queue.isEmpty()){
            int v = queue.remove();
            order.add(v);
            for(int g:G.adj(v)){
                if(!visited[g]){
                    queue.add(g);
                    visited[g] = true;
                }
            }
        }

    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String [] args){
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        GraphBFS graphbfs = new GraphBFS(g);
        System.out.println("BFS oorder"+graphbfs.order());
    }
}
