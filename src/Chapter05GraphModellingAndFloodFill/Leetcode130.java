package Chapter05GraphModellingAndFloodFill;

/**
 * Author : lihuichuan
 * Time   : 2019/12/22
 */

/// Floodfill
/// Time Complexity: O(n * m)
/// Space Complexity: O(n * m)
public class Leetcode130 {
    private int d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;
    private boolean visited[][];

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return ;
        m = board.length;
        n = board[0].length;

        visited = new boolean[m][n];
        for(int i = 0; i < m; i ++){
            if(board[i][0] == 'O') dfs(board, i, 0);
            if(board[i][n - 1] == 'O') dfs(board, i, n - 1);
        }
        for(int j = 0; j < n; j ++){
            if(board[0][j] == 'O') dfs(board, 0, j);
            if(board[m - 1][j] == 'O') dfs(board, m - 1, j);
        }
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
               if(board[i][j] == 'O'){
                   board[i][j] = 'X';
               }else if(board[i][j] == '+'){
                   board[i][j] = 'O';
               }
    }

    public void dfs(char[][] grid, int i, int j) {

        grid[i][j] = '+';
        visited[i][j] = true;
        for (int index = 0; index < d.length; index++) {
            int newx = i + d[index][0];
            int newy = j + d[index][1];
            if (inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == 'O') {
                dfs(grid, newx, newy);
            }
        }
        return;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
