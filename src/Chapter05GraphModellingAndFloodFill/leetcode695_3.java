package Chapter05GraphModellingAndFloodFill;

/// Leetcode 695
/// Using Union-Find

class UF {

    private int[] parent;
    private int[] sz;

    public UF(int n) {

        parent = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int p) {
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
        sz[qRoot] += sz[pRoot];
    }

    public int size(int p) {
        return sz[find(p)];
    }
}

public class leetcode695_3 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;

    public int maxAreaOfIsland(int[][] grid) {

        if (grid == null) return 0;

        R = grid.length;
        if (R == 0) return 0;

        C = grid[0].length;
        if (C == 0) return 0;
        UF uf = new UF(R * C);
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1)
                for (int d = 0; d < 4; d++) {
                    int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
                    if (inArea(nextx, nexty) && grid[nextx][nexty] == 1) {
                        int next = nextx * C + nexty;
                        uf.unionElements(v, next);
                    }
                }
        }

        int res = 0;
        for (int v = 0; v < R * C; v++) {
            int x = v / C, y = v % C;
            if (grid[x][y] == 1)
                res = Math.max(res, uf.size(v));
        }
        return res;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
