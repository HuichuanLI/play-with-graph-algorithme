class Solution:
    steps = [[0, 1], [1, 0], [-1, 0], [0, -1]]

    def floodFill(self, image, sr: int, sc: int, newColor: int):
        self.graph = image
        self.newColor = newColor
        self.orignalColor = self.graph[sr][sc]
        self.visted = [[False for _ in range(len(image[0]))] for _ in range(len(image))]
        self.dfs(sr, sc)
        return self.graph

    def dfs(self, i, j):
        self.visted[i][j] = True
        self.graph[i][j] = self.newColor
        for step in self.steps:
            next_x = i + step[0]
            next_y = j + step[1]
            if next_x >= 0 and next_x < len(self.graph) and next_y >= 0 and next_y < len(self.graph[0]) and not \
                    self.visted[next_x][next_y] and self.graph[next_x][next_y] == self.orignalColor:
                self.dfs(next_x, next_y)
