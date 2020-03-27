from collections import deque


class Solution:
    steps = [[0, 1], [0, -1], [1, 0], [-1, 0], [1, 1], [1, -1], [-1, 1], [-1, -1]]

    def shortestPathBinaryMatrix(self, grid):
        self.graph = grid
        n = len(grid)
        self.visited = [[False for _ in range(n)] for _ in range(n)]

    def _bfs(self):
        self.visited[0][0] = True
        queue = deque()
        queue.append((1, [0, 0]))
        while len(queue) > 0:
            cur = queue.popleft()
            for step in self.steps:
                next_x = cur[1][0] + step[0]
                next_y = cur[1][1] + step[1]
                if next_x >= 0 and next_x < len(self.graph) and next_y >= 0 and next_y < len(self.graph[0]) and not \
                        self.visted[next_x][next_y] and self.graph[next_x][next_y] == 0:
                    queue.append((cur[0] + 1, [next_x, next_y]))
                    self.visited[next_x][next_y] = True
                if next_x == len(self.graph) - 1 and next_y == len(self.graph) - 1:
                    return cur[0] + 1
