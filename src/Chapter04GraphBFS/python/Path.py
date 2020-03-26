from Chapter02GraphDFS.adjSet import adjSet as Graph
from collections import deque


class SingleSourcePath:
    def __init__(self, G, s, t):
        self._G = G
        self.t = t
        self._visited = [False] * G.V
        self._pre = [-1] * G.V
        self.s = s

        self._bfs(s)

    def _bfs(self, s):
        queue = deque()
        queue.append(s)
        self._pre[s] = s
        self._visited[s] = True

        while queue:
            v = queue.popleft()
            for w in self._G.adj(v):
                if not self._visited[w]:
                    queue.append(w)
                    self._visited[w] = True
                    self._pre[w] = v
                    if w == self.t:
                        return

    def path(self):
        if not self.is_connected_to() :
            return []
        w = self.t
        res = [w]
        while self._pre[w] != w:
            res.append(self._pre[w])
            w = self._pre[w]

        return list(reversed(res))

    def is_connected_to(self):
        self._G._validate_vertex(self.t)
        # if t is visited for current self._s
        # that implies t is in the path of self._s
        return self._visited[self.t]


if __name__ == "__main__":
    filename = "/Chapter03GraphDFSApplications/g.txt"
    g = Graph(filename)

    sspath = SingleSourcePath(g, 0, 6)
    print(sspath.path())
    print(sspath.is_connected_to())
