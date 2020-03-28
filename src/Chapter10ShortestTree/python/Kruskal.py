from WeightedEdge import WeightedEdge
from WeightedGraph import WeightedGraph
from UF import UF
from Chapter04GraphBFS.python.CC import CC

# O(ElogE)
class Kruskal:
    def __init__(self, G):
        self._G = G
        self._mst = []

        cc = CC(G)
        print(cc.ccount)
        if (cc.ccount > 1):
            return
        edges = []
        for v in range(G.V):
            for w in G.adj(v):
                if v < w:
                    edges.append(WeightedEdge(v, w, G.get_weight(v, w)))

        edges = sorted(edges, key=lambda key: key.weight)
        uf = UF(self._G.V)
        for edge in edges:
            v = edge.v
            w = edge.w
            if uf.isConnected(v, w):
                continue
            else:
                uf.union(v, w)
                self._mst.append(edge)

    def result(self):
        return self._mst


if __name__ == '__main__':
    filename = '../g.txt'
    g = WeightedGraph(filename)
    kruskal = Kruskal(g)
    print(kruskal.result())
