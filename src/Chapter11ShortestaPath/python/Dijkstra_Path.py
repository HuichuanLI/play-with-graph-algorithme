from WeightedGraph import WeightedGraph
import heapq
import sys


# O(ElogE)
class Dijkstra:

    def __init__(self, G, s):
        self._G = G
        self._G._validate_vertex(s)
        self._s = s
        self.pre = [-1] * self._G.V
        self._dis = [sys.maxsize] * self._G.V
        self._dis[s] = 0
        self.pre[s] = s
        # 是否已经确定了点是全局最小距离
        self._visited = [False] * self._G.V
        self.pq = []
        heapq.heappush(self.pq, [0, self._s])
        while len(self.pq) > 0:
            curdis, cur = heapq.heappop(self.pq)
            if self._visited[cur]:
                continue

            self._visited[cur] = True
            for w in self._G.adj(cur):
                if not self._visited[w]:
                    if self._dis[cur] + self._G.get_weight(cur, w) < self._dis[w]:
                        self._dis[w] = self._dis[cur] + self._G.get_weight(cur, w)
                        self.pre[w] = cur
                        heapq.heappush(self.pq, [self._dis[w], w])

    def is_connected_to(self, v):
        self._G._validate_vertex(v)
        return self._visited[v]

    def dist_to(self, v):
        self._G._validate_vertex(v)
        return self._dis[v]

    def Path(self, t):
        res = []
        if not self.is_connected_to(t):
            return res
        while t != self._s:
            res.append(t)
            t = self.pre[t]
        res.append(self._s)
        return res[::-1]


if __name__ == '__main__':
    filename = '../g.txt'
    g = WeightedGraph(filename)
    dijkstra = Dijkstra(g, s=0)

    strings = []
    for v in range(g.V):
        strings.append(str(dijkstra.dist_to(v)))

    print(' '.join(strings))

    print(dijkstra.Path(1))
