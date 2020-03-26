class GraphBase:
    def E(self):
        raise NotImplementedError

    def V(self):
        raise NotImplementedError

    def hasEdge(self, v, w):
        raise NotImplementedError

    def adj(self, v):
        raise NotImplementedError

    def degree(self, v):
        raise NotImplementedError
