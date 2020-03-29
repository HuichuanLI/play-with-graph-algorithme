class WeightedGraph:
    def __init__(self, filename, directed=False):
        self._filename = filename
        self._directed = directed

        lines = None
        with open(filename, 'r') as f:
            lines = f.readlines()
        if not lines:
            raise ValueError('Expected something from input file!')

        # lines[0] -> V E
        self._V, self._E = (int(i) for i in lines[0].split())

        if self._V < 0:
            raise ValueError('V must be non-negative')

        if self._E < 0:
            raise ValueError('E must be non-negative')
        self._adj = [dict() for _ in range(self._V)]

        for each_line in lines[1:]:
            a, b, weighted = (int(i) for i in each_line.split())
            self._validate_vertex(a)
            self._validate_vertex(b)

            if a == b:
                raise ValueError('Self-Loop is detected!')

            if b in self._adj[a]:
                raise ValueError('Paralles edges are detected!')

            self._adj[a][b] = weighted

            if not self._directed:
                self._adj[b][a] = weighted

    def is_directed(self):
        return self._directed

    @property
    def V(self):
        return self._V

    @property
    def E(self):
        return self._E

    def has_edge(self, v, w):
        self._validate_vertex(v)
        self._validate_vertex(w)
        return w in self._adj[v]

    def adj(self, v):
        self._validate_vertex(v)
        return self._adj[v]

    def degree(self, v):
        return len(self.adj(v))

    def _validate_vertex(self, v):
        if v < 0 or v >= self._V:
            raise ValueError('vertex ' + v + ' is invalid')

    def __str__(self):
        res = ['V = {}, E = {}, directed = {}'.format(self._V, self._E, self._directed)]
        for v in range(self._V):
            res.append(
                '{}: {}'.format(
                    v,
                    ' '.join('({}:{})'.format(w, self._adj[v][w]) for w in self._adj[v]),
                ),
            )
        return '\n'.join(res)

    def __repr__(self):
        return self.__str__()

    def get_weight(self, v, w):
        if self.has_edge(v, w):
            return self._adj[v][w]
        raise ValueError('No edge {}-{}'.format(v, w))

    def __copy__(self):
        return WeightedGraph(self._filename)

    def remove_edge(self, v, w):
        self._validate_vertex(v)
        self._validate_vertex(w)
        if not self._directed:
            if w in self._adj[v]:
                self._adj[v].pop(w)
        if v in self._adj[w]:
            self._adj[w].pop(v)


if __name__ == '__main__':
    filename = '../wg.txt'
    adj_list = WeightedGraph(filename,False)
    print(adj_list)
    print(adj_list.V)
    print(adj_list.E)
    print(adj_list.adj(1))
    print(adj_list.degree(1))
    print(adj_list.get_weight(1, 0))



    filename = '../wg.txt'
    adj_list = WeightedGraph(filename,True)
    print(adj_list)
    print(adj_list.V)
    print(adj_list.E)
    print(adj_list.adj(1))
    # print(adj_list.degree(1))
    # print(adj_list.get_weight(1, 0))
