import java.lang.reflect.Array;
import java.util.Arrays;

public class leetcode980Optimsation {
    private int[][] grid;
    private int R, C;
    private int visited;
    private int start, end;
    private int[][] memo;

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        visited = 0;

        int left = R * C;
        memo = new int[1 << left][left];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo, -1);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 1) {
                    start = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) {
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) {
                    left--;
                }

            }
        }
        return dfs(start, left);
    }

    private int dfs(int v, int left) {
        if (memo[visited][v] != -1) return memo[visited][v];

        int x = v / C, y = v % C;
        visited += (1 << v);
        left--;
        if (left == 0 && v == end) {
            visited -= (1 << v);
            memo[v][visited] = 1;
            return 1;
        }

        int res = 0;
        for (int d = 0; d < 4; d++) {
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];

            int next = nextx * C + nexty;

            if (inArea(nextx, nexty) && grid[nextx][nexty] == 0 && (visited & (1 << next)) == 0)
                res += dfs(nextx * C + nexty, left);
        }
        visited -= (1 << v);
        return res;

    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
