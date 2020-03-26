from Chapter02GraphDFS.python.adjSet import adjSet as Graph
from collections import deque


class CycleDetection:
    def __init__(self, G):
        self._G = G
        self._visited = [False] * G.V
        self._pre = [-1] * G.V
        self.cycle = False
        for v in range(G.V):
            if not self._visited[v]:
                if self._bfs(v):
                    self.cycle = True

    def _bfs(self, s):
        queue = deque()
        queue.append(s)
        self._visited[s] = True
        self._pre[s] = s
        while queue:
            v = queue.popleft()
            for w in self._G.adj(v):
                if not self._visited[w]:
                    queue.append(w)
                    self._visited[w] = True
                    self._pre[w] = v
                elif w != self._pre[v]:
                    self.cycle = True
                    return True
        return False

    @property
    def cycled(self):
        return self.cycle


if __name__ == "__main__":
    filename = "/Chapter04GraphBFS/g.txt"
    g = Graph(filename)

    path = CycleDetection(g)
    print(path.cycled)

    filename = "/Chapter04GraphBFS/g2.txt"
    g = Graph(filename)

    path = CycleDetection(g)
    print(path.cycled)
