package Chapter05GraphModellingAndFloodFill;

import java.util.HashSet;
/// Leetcode 695

public class Leetcode695 {
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[] visited;
    private int R, C;
    private int[][] grid;
    public HashSet<Integer>[] G;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) return 0;
        C = grid[0].length;
        if (C == 0) return 0;
        this.grid = grid;
        G = constructGraph();
        int res = 0;
        visited = new boolean[G.length];
        for (int i = 0; i < G.length; i++) {
            int x = i / C;
            int y = i % C;
            if (!visited[i] && grid[x][y] == 1) {
                res = Math.max(dfs(i), res);
            }

        }
        return res;
    }

    public int dfs(int s) {
        visited[s] = true;
        int res = 0;
        for (int w : G[s]) {
            if (!visited[w]) {
                res += dfs(w);
            }
        }
        return res + 1;

    }

    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] g = new HashSet[R * C];
        for (int i = 0; i < g.length; i++)
            g[i] = new HashSet<>();
        for (int v = 0; v < g.length; v++) {
            int x = v / C, y = v % C;
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

    public boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
