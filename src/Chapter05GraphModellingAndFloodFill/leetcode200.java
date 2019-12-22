package Chapter05GraphModellingAndFloodFill;

/**
 * Author : lihuichuan
 * Time   : 2019/12/22
 */

/// Floodfill - DFS
/// Recursion implementation
/// Time Complexity: O(n*m)
/// Space Complexity: O(n*m)
public class leetcode200 {
    private int d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    private boolean visited[][];

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(grid, i, j);
                    res++;
                }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        visited[i][j] = true;
        for (int index = 0; index < d.length; index++) {
            int newx = i + d[index][0];
            int newy = j + d[index][1];
            if (inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == '1')
                dfs(grid, newx, newy);
        }
        return;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
