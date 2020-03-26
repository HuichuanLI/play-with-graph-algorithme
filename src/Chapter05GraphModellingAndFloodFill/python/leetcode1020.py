class Solution:
    steps = [[0, 1], [1, 0], [0, -1], [-1, 0]]

    def numEnclaves(self, A) -> int:
        self.graph = A
        self.visitd = [[False for _ in range(len(A[i]))] for i in range(len(A))]
        for i in range(len(A)):
            if self.graph[i][0] == 1:
                self.dfs(i, 0)
            if self.graph[i][len(A[i]) - 1] == 1:
                self.dfs(i, len(A[i]) - 1)
        for j in range(len(A[0])):
            if self.graph[0][j] == 1:
                self.dfs(0, j)

        for j in range(len(A[len(A) - 1])):
            if self.graph[len(A) - 1][j] == 1:
                self.dfs(len(A) - 1, j)
        res = 0
        for i in range(len(A)):
            res += sum(A[i])
        return res

    def dfs(self, i, j):
        self.visitd[i][j] = True
        self.graph[i][j] = 0
        res = 1
        for step in self.steps:
            next_x = i + step[0]
            next_y = j + step[1]
            if next_x >= 0 and next_x < len(self.graph) and next_y >= 0 and next_y < len(self.graph[0]) and not \
                    self.visitd[next_x][next_y] and self.graph[next_x][next_y] == 1:
                self.dfs(next_x, next_y)
        return res
