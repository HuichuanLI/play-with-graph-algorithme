package Chapter05GraphModellingAndFloodFill;

/**
 * Author : lihuichuan
 * Time   : 2019/12/22
 */

/// Floodfill
/// Time Complexity: O(n * m)
/// Space Complexity: O(n * m)

public class Leetcode733 {
    private int m, n;
    private int d[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 || image[0].length == 0)
            return image;
        m = image.length;
        n = image[0].length;

        int original = image[sr][sc];
        if(original == newColor){
            return image;
        }
        dfs(image, sr, sc, newColor, original);
        return image;
    }

    public void dfs(int[][] image, int sr, int sc, int newColor, int original) {
        image[sr][sc] = newColor;
        for (int i = 0; i < d.length; i++) {
            int newx = sr + d[i][0];
            int newy = sc + d[i][1];
            if (inArea(newx, newy) && image[newx][newy] == original) {
                dfs(image, newx, newy, newColor, original);
            }
        }
        return;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
