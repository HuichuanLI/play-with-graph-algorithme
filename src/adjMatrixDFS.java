
import java.util.ArrayList;


public class adjMatrixDFS {
    private AdjMatrix G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    public adjMatrixDFS(AdjMatrix G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v);
    }

    private void dfs(int v){

        visited[v] = true;
        pre.add(v);
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){

        AdjMatrix g = new AdjMatrix("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        adjMatrixDFS graphDFS = new adjMatrixDFS(g);
        System.out.println("DFS preOrder : " + graphDFS.pre());
        System.out.println("DFS postOrder : " + graphDFS.post());
    }


}
