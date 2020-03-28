from WeightedGraph import WeightedGraph

import sys

# O(V^2)
class Dijkstra:

    def __init__(self, G, s):
        self._G = G
        self._G._validate_vertex(s)
        self._s = s

        self._dis = [sys.maxsize] * self._G.V
        self._dis[0] = 0
        # 是否已经确定了点是全局最小距离
        self._visited = [False] * self._G.V

        while True:
            curdis = sys.maxsize
            cur = -1
            for i in range(self._G.V):
                if not self._visited[i] and self._dis[i] < curdis:
                    curdis = self._dis[i]
                    cur = i
            if cur == -1:
                break
            self._visited[cur] = True
            for w in self._G.adj(cur):
                if not self._visited[w]:
                    if self._dis[cur] + self._G.get_weight(cur, w) < self._dis[w]:
                        self._dis[w] = self._dis[cur] + self._G.get_weight(cur, w)

    def is_connected_to(self, v):
        self._G._validate_vertex(v)
        return self._visited[v]

    def dist_to(self, v):
        self._G._validate_vertex(v)
        return self._dis[v]


if __name__ == '__main__':
    filename = '../g.txt'
    g = WeightedGraph(filename)
    dijkstra = Dijkstra(g, s=0)

    strings = []
    for v in range(g.V):
        strings.append(str(dijkstra.dist_to(v)))

    print(' '.join(strings))
