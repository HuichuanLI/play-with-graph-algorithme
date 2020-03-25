from collections import defaultdict


class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        self.graph = defaultdict(list)
        for i in edges:
            self.graph[i[0]].append(i[1])
            self.graph[i[1]].append(i[0])
        self.visited = [False] * n
        self.hascycle = False

        if not self.dfs(0, 0) and all(self.visited):
            return True
        else:
            return False

    def dfs(self, root, parent):
        self.visited[root] = True
        for w in self.graph[root]:
            if not self.visited[w]:
                if self.dfs(w, root):
                    return True
            elif parent != w:
                self.hascycle = True
                return True
        return False
