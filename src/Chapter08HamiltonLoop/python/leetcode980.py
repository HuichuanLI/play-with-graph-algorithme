class Solution:
    steps = [[0, 1], [1, 0], [0, -1], [-1, 0]]

    def uniquePathsIII(self, grid):
        self.graph = grid
        self.visited = [[False for _ in range(len(grid[i]))] for i in range(len(grid))]
        start_i = -1
        start_j = -1
        for i in range(len(grid)):
            for j in range(len(grid[i])):
                if grid[i][j] == -1:
                    self.visited[i][j] = True
                elif grid[i][j] == 1:
                    start_i = i
                    start_j = j
        if start_i == -1:
            return 0
        return self.dfs(start_i, start_j)

    def dfs(self, i, j):
        self.visited[i][j] = True

        if self.graph[i][j] == 2 and all([all(self.visited[i]) for i in range(len(self.visited))]):
            self.visited[i][j] = False
            return 1
        res = 0
        for step in self.steps:
            next_x = i + step[0]
            next_y = j + step[1]
            if next_x >= 0 and next_x < len(self.graph) and next_y >= 0 and next_y < len(self.graph[next_x]) and not \
            self.visited[next_x][next_y]:
                res += self.dfs(next_x, next_y)
        self.visited[i][j] = False
        return res

