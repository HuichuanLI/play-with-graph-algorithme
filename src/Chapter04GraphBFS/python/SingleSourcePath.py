from Chapter02GraphDFS.python.adjSet import adjSet as Graph
from collections import deque


class SingleSourcePath:
    def __init__(self, G, s):
        self._G = G
        self._visited = [False] * G.V
        self._pre = [-1] * G.V
        self.s = s
        for v in range(G.V):
            if not self._visited[v]:
                self._bfs(v)

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

    def path(self, w):
        self._G._validate_vertex(w)
        res = [w]
        while self._pre[w] != w:
            res.append(self._pre[w])
            w = self._pre[w]

        return list(reversed(res))

    def is_connected_to(self, t):
        self._G._validate_vertex(t)
        # if t is visited for current self._s
        # that implies t is in the path of self._s
        return self._visited[t]


if __name__ == "__main__":
    filename = "/Chapter03GraphDFSApplications/g.txt"
    g = Graph(filename)

    sspath = SingleSourcePath(g, 0)
    print('0 -> 6 : {}'.format(sspath.path(6)))
