public class CycleDecetionBFS {

    private GraphInterface G;
    private boolean[] visited;
    // 对于使用 bfs 解决环检测问题，我们还需一个整型数组，记录每一个节点的父亲节点
    // 沿用之前的命名风格，我们管它叫做 pre
    private int[] pre;

    private boolean hasCycle = false;

    public CycleDecetionBFS(GraphInterface G) {

        this.G = G;


        pre = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            pre[i] = -1;



        visited = new boolean[G.V()];

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (bfs(v, v)) {
                    hasCycle = true;
                    break;
                }
    }


    public boolean hasCycle() {
        return hasCycle;
    }

    public boolean bfs(int cur,int par){
        pre[cur] = par;
        visited[cur] = true;

        for(int w:G.adj(cur)){
            if(!visited[w])
                bfs(w,cur);
            else if(visited[w] && pre[cur] != w)
                return true;
        }
        return false;

    }

    public static void main(String[] args) {
        Graph g = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g.txt");
        CycleDecetionBFS cycledetection = new CycleDecetionBFS(g);
        System.out.println(cycledetection.hasCycle());

        Graph g2 = new Graph("/Users/hui/Desktop/java/play-with-graph-algorithme/g2.txt");
        CycleDecetionBFS cycledetection2 = new CycleDecetionBFS(g2);
        System.out.println(cycledetection2.hasCycle());
    }

}
