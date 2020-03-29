import sys
from WeightedGraph import WeightedGraph


class Floyd:

    def __init__(self, G):
        self._G = G
        self._has_negative_cycle = False

        self._dis = [[sys.maxsize] * self._G.V for _ in range(self._G.V)]

        for v in range(self._G.V):
            self._dis[v][v] = 0
            for w in self._G.adj(v):
                self._dis[v][w] = self._G.get_weight(v, w)

        for t in range(self._G.V):
            for u in range(self._G.V):
                for w in range(self._G.V):
                    # 正型溢出
                    if self._dis[v][t] == sys.maxsize or self._dis[t][w] == sys.maxsize:
                        continue
                    if self._dis[u][w] > self._dis[u][t] + self._dis[t][w]:
                        self._dis[u][w] = self._dis[u][t] + self._dis[t][w]

        for v in range(self._G.V):
            if self._dis[v][v] < 0:
                self._has_negative_cycle = True
                break

    def has_neg_cycle(self):
        return self._has_negative_cycle

    def is_connected_to(self, v, w):
        self._G._validate_vertex(v)
        self._G._validate_vertex(w)
        return self._dis[v][w] < sys.maxsize

    def dist_to(self, v, w):
        self._G._validate_vertex(v)
        self._G._validate_vertex(w)
        return self._dis[v][w]


if __name__ == '__main__':
    filename = '../g.txt'
    g = WeightedGraph(filename)
    floyd = Floyd(g)

    if not floyd.has_neg_cycle():
        for v in range(g.V):
            strings = []
            for w in range(g.V):
                strings.append(str(floyd.dist_to(v, w)))
            print(' '.join(strings))
    else:
        print('exist negative cycle')