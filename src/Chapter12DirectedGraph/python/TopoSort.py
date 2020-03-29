from collections import deque
from Graph import Graph

class TopoSort:

    def __init__(self, G):
        if not G.is_directed():
            raise ValueError('TopoSort only works in directed graph')
        self._G = G
        self._res = []
        self._has_cycle = False

        indegrees = [0] * self._G.V
        queue = deque()

        for v in range(self._G.V):
            indegrees[v] = self._G.indegree(v)
            if indegrees[v] == 0:
                queue.append(v)

        while len(queue) > 0:
            cur = queue.popleft()
            self._res.append(cur)
            for w in self._G.adj(cur):
                indegrees[w] -= 1
                if indegrees[w] == 0:
                    queue.append(w)
        if len(self._res) != self._G.V:
            self._has_cycle = True

    def has_cycle(self):
        return self._has_cycle

    def result(self):
        return self._res


if __name__ == '__main__':
    filename = '../ug.txt'
    g = Graph(filename, directed=True)

    topo_sort = TopoSort(g)
    print(topo_sort.result())
