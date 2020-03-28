from WeightedEdge import WeightedEdge
from WeightedGraph import WeightedGraph
from UF import UF
from Chapter04GraphBFS.python.CC import CC
import sys


# O((V-1)*(V+E)) = O(VE)
class Prim:
    def __init__(self, G):
        self._G = G
        self._mst = []

        cc = CC(G)
        if (cc.ccount > 1):
            return
        visited = [False] * self._G.V
        visited[0] = True
        for i in range(1, G.V):
            minEdge = WeightedEdge(-1, -1, sys.maxsize)

            for v in range(G.V):
                if visited[v]:
                    for w in self._G.adj(v):
                        if not visited[w]:
                            if self._G.get_weight(v, w) < minEdge.weight:
                                minEdge = WeightedEdge(v, w, self._G.get_weight(v, w))
            self._mst.append(minEdge)
            visited[minEdge.w] = True
            visited[minEdge.v] = True

    def result(self):
        return self._mst


if __name__ == '__main__':
    filename = '../g.txt'
    g = WeightedGraph(filename)
    kruskal = Prim(g)
    print(kruskal.result())
