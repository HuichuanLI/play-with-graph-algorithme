package Chapter05GraphModellingAndFloodFill;

/**
 * Author : lihuichuan
 * Time   : 2019/12/22
 */
/// Floodfill
/// Time Complexity: O(n * m)
/// Space Complexity: O(n * m)
public class Leetcode1020 {
    private int d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    private boolean visited[][];

    public int numEnclaves(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int res = 0;
        for(int i = 0; i < m; i ++){
            if(grid[i][0] == 1) dfs(grid, i, 0);
            if(grid[i][n - 1] == 1) dfs(grid, i, n - 1);
        }
        for(int j = 0; j < n; j ++){
            if(grid[0][j] == 1) dfs(grid, 0, j);
            if(grid[m - 1][j] == 1) dfs(grid, m - 1, j);
        }

        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                res += grid[i][j];
        return res;
    }

    public void dfs(int[][] grid, int i, int j) {

        grid[i][j] = 0;
        visited[i][j] = true;
        for (int index = 0; index < d.length; index++) {
            int newx = i + d[index][0];
            int newy = j + d[index][1];
            if (inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == 1) {
                dfs(grid, newx, newy);
            }
        }
        return;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
