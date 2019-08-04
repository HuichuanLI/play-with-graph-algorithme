import java.util.HashSet;

public class leetcode695_3 {
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int[][] grid;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;

        if (R == 0)
            return 0;
        C = grid[0].length;
        this.grid = grid;

        int res = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j));
                }
            }

        return res;
    }

    private int dfs(int x, int y) {
        grid[x][y] = 0;
        int res = 1;

        for (int d = 0; d < 4; d++) {
            int newx = x + dirs[d][0];
            int newy = y + dirs[d][1];
            if (inArea(newx, newy)  && grid[newx][newy] == 1)
                res += dfs(newx, newy);
        }
        return res;
    }


    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;

    }
}
