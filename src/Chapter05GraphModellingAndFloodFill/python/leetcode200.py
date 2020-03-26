class Solution:
    postion = [[0, 1], [1, 0], [0, -1], [-1, 0]]

    def numIslands(self, grid):
        visited = [[False for _ in range(len(grid[i]))] for i in range(len(grid))]
        res = 0
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if not visited[i][j] and grid[i][j] == "1":
                    res += 1
                    self._visited(i, j, visited,grid)
        return res

    def _visited(self, i, j, visited, grid):
        visited[i][j] = True
        for next_step in self.postion:
            next_x = i + next_step[0]
            next_y = j + next_step[1]
            if next_x >= 0 and next_x < len(grid) and next_y >= 0 and next_y < len(grid[0]) and not visited[next_x][next_y] and grid[next_x][next_y] == "1":
                self._visited(next_x, next_y, visited, grid)
        return