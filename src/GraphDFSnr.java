import java.util.ArrayList;
import java.util.Stack;

public class GraphDFSnr {

    private GraphInterface G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();

    public GraphDFSnr(GraphInterface G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        visited[v] = true;
        while(!stack.empty()){
            int cur = stack.pop();
            pre.add(cur);
            for(int w: G.adj(v))
                if(!visited[w]){
                    stack.push(w);
                    visited[w] = true;
                }
        }
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public static void main(String[] args){

        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        GraphDFSnr graphDFSnr = new GraphDFSnr(g);
        System.out.println(graphDFSnr.pre());
    }
}