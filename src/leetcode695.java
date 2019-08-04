import java.util.HashSet;

public class leetcode695 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private HashSet<Integer>[] graph;
    private int[][] grid;
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;

        if (R == 0)
            return 0;
        C = grid[0].length;
        this.grid = grid;
        graph = construction();
        visited = new boolean[graph.length];
        int res = 0;
        for (int v = 0; v < graph.length; v++) {
            int x = v / C, y = v % C;
            if (!visited[v] && grid[x][y] == 1) {
                res = Math.max(res, dfs(v));
            }
        }

        return res;
    }

    private int dfs(int v) {
        visited[v] = true;
        int res = 1;
        for (int w : graph[v]) {
            if (!visited[w])
                res += dfs(w);
        }
        return res;
    }

    private HashSet<Integer>[] construction() {

        HashSet<Integer>[] g = new HashSet[R * C];
        for (int i = 0; i < g.length; i++) {
            g[i] = new HashSet<>();
        }
        for (int v = 0; v < g.length; v++) {
            int x = v / C;
            int y = v % C;
            if (grid[x][y] == 1) {
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0];
                    int nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        g[v].add(next);
                        g[next].add(v);
                    }
                }
            }

        }
        return g;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;

    }
}
