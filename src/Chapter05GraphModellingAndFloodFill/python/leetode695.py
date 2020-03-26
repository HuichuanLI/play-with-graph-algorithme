class Solution:
    steps = [[0, 1], [1, 0], [-1, 0], [0, -1]]

    def maxAreaOfIsland(self, grid):
        self.graph = grid
        self.visted = [[False for _ in range(len(grid[0]))] for i in range(len(grid))]
        self.maxRes = 0
        for i in range(len(grid)):
            for j in range(len(grid[0])):
                if grid[i][j] == 1 and not self.visted[i][j]:
                    self.maxRes = max(self.maxRes, self._dfs(i, j))
        return self.maxRes

    def _dfs(self, i, j):
        self.visted[i][j] = True
        res = 1
        for step in self.steps:
            next_x = i + step[0]
            next_y = j + step[1]
            if next_x >= 0 and next_x < len(self.graph) and next_y >= 0 and next_y < len(self.graph[0]) and not \
                    self.visted[next_x][next_y] and self.graph[next_x][next_y] == 1:
                res += self._dfs(next_x, next_y)
        return res