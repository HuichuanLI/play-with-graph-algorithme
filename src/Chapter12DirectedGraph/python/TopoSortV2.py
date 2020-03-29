from collections import deque
from Graph import Graph
from DirectedCycleDetection import DirectedCycleDetection
from DFS import GraphDFS


class TopoSort:

    def __init__(self, G):
        if not G.is_directed():
            raise ValueError('TopoSort only works in directed graph')
        self._G = G
        self._res = []
        self._has_cycle = DirectedCycleDetection(self._G).hascycle
        if not self._has_cycle:
            dfs = GraphDFS(g)
            self._res = dfs.post_order[::-1]

    def has_cycle(self):
        return self._has_cycle

    def result(self):
        return self._res


if __name__ == '__main__':
    filename = '../ug.txt'
    g = Graph(filename, directed=True)

    topo_sort = TopoSort(g)
    print(topo_sort.result())
