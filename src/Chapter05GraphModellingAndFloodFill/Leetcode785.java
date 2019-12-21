package Chapter05GraphModellingAndFloodFill;

public class Leetcode785 {
    private int[][] graph;
    private int[] colors;
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];

        for (int v = 0; v < V; v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) return false;
            }
        }
        return true;
    }

    public boolean dfs(int start, int color) {
        visited[start] = true;
        colors[start] = color;
        for (int v : graph[start]) {
            if (!visited[v]) {
                if (!dfs(v, 1 - color)) {
                    return false;
                }
            } else if (colors[v] == colors[start]) {
                return false;

            }
        }
        return true;
    }
}