package Chapter05GraphModellingAndFloodFill;

import java.util.HashSet;

public class leetcode695_2 {
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int R, C;
    private int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        R = grid.length;
        if (R == 0) return 0;
        C = grid[0].length;
        if (C == 0) return 0;
        this.grid = grid;

        int res = 0;
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(i, j));
                }
            }
        }
        return res;
    }

    public int dfs(int i, int j) {
        visited[i][j] = true;
        int res = 0;
        for (int d = 0; d < 4; d++) {
            int nextx = i + dirs[d][0], nexty = j + dirs[d][1];
            if (inArea(nextx, nexty) && grid[nextx][nexty] == 1 && !visited[nextx][nexty])
                res += dfs(nextx, nexty);
        }
        return res + 1;

    }


    public boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
