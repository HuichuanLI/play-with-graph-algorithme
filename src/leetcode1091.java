import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class leetcode1091 {

    private int[][] grid;

    private boolean[][] visited;


    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {-1, -1}, {1, -1}, {0, -1}};


    public int shortestPathBinaryMatrix(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];
        int[][] dis = new int[grid.length][grid[0].length];
        if (grid[0][0] == 1) return -1;
        if (grid.length == 1 && grid[0].length == 1)
            return 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;

        dis[0][0] = 1;


        while (!queue.isEmpty()) {
            int v = queue.remove();


            int orignx = v / grid.length;
            int origny = v % grid[0].length;

            for (int x = 0; x < dirs.length; x++) {
                int newx = orignx + grid[x][0];
                int newy = origny + grid[x][1];
                if (newx > 0 && newx < grid.length && newy > 0 && newy < grid[0].length) {
                    if (grid[newx][newy] == 0 && visited[newx][newy] == false) {
                        visited[newx][newy] = true;
                        queue.add(newx * grid[0].length + origny);
                        dis[newx][newy] = dis[orignx][origny] + 1;
                        if (newx == grid.length - 1 && newy == grid[0].length - 1) {
                            return dis[newx][newy];
                        }
                    }
                }

            }
        }

        return -1;
    }
}
