from collections import defaultdict


class Solution:
    def isBipartite(self, graph):
        self.graph = graph
        self.visited = [False] * len(graph)
        self.hascycle = False
        self.colors = [-1] * len(graph)
        for i in range(len(graph)):
            if not self.visited[i]:
                if not self.dfs(i, 0):
                    return False

        return True
    def dfs(self, root, color):
        self.visited[root] = True
        self.colors[root] = color
        for w in self.graph[root]:
            if not self.visited[w]:
                if not self.dfs(w, 1 - color):
                    return False
            elif self.colors[w] == color:
                return False
        return True
