from Chapter02GraphDFS.python.adjSet import adjSet as Graph
from Chapter04GraphBFS.python.SingleSourcePath import SingleSourcePath


class AllPairsPath:

    def __init__(self, G):
        self._G = G
        self._paths = []

        for v in range(G.V):
            self._paths.append(SingleSourcePath(G, v))

    def is_connected_to(self, s, t):
        self._G._validate_vertex(s)
        return self._paths[s].is_connected_to(t)

    def path(self, s, t):
        self._G._validate_vertex(s)
        return self._paths[s].path(t)


if __name__ == '__main__':
    filename = '/Chapter03GraphDFSApplications/g.txt'
    g = Graph(filename)
    appath = AllPairsPath(g)
    print(appath.path(0, 6))
