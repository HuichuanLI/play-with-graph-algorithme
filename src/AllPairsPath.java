public class AllPairsPath {

    private Graph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph G){

        this.G = G;

        paths = new SingleSourcePath[G.V()];
        for(int v = 0; v < G.V(); v ++)
            paths[v] = new SingleSourcePath(G, v);
    }

    public boolean isConnectedTo(int s, int t){
        return paths[s].isConnected(t);
    }

    public Iterable<Integer> path(int s, int t){
        return paths[s].path(t);
    }


    public static void main(String [] args){
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        AllPairsPath apath = new AllPairsPath(g);
        System.out.println("6->4:" + apath.path(6,4));
        System.out.println("5->4:" + apath.path(5,4));

    }
}