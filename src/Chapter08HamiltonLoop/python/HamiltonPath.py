from Chapter02GraphDFS.python.adjSet import adjSet as Graph


class HamiltonPath:

    def __init__(self, G, s):
        self._G = G
        self._visited = [False] * G.V
        self.s = s
        self._pre = [0] * G.V
        self._end = -1
        self._dfs(s, s, G.V)

    def _dfs(self, v, parent, left):
        self._visited[v] = True
        self._pre[v] = parent
        left -= 1
        if left == 0:
            self._end = v
            return True

        for w in self._G.adj(v):
            if not self._visited[w]:
                if self._dfs(w, v, left):
                    return True

        self._visited[v] = False

        return False

    def result(self):
        res = []
        if self._end == -1:
            return res

        curr = self._end
        while curr != self.s:
            res.append(curr)
            curr = self._pre[curr]
        res.append(self.s)

        return res[::-1]


class HamiltonPathV2:

    def __init__(self, G, s):
        self._G = G
        self._visited = 0
        self.s = s
        self._pre = [0] * G.V
        self._end = -1
        self._dfs(s, s, G.V)

    def _dfs(self, v, parent, left):
        self._visited += (1 << v)
        self._pre[v] = parent
        left -= 1
        if left == 0:
            self._end = v
            return True

        for w in self._G.adj(v):

            if not (self._visited & (1 << w)):
                if self._dfs(w, v, left):
                    return True

        self._visited -= (1 << v)

        return False

    def result(self):
        res = []
        if self._end == -1:
            return res

        curr = self._end
        while curr != self.s:
            res.append(curr)
            curr = self._pre[curr]
        res.append(self.s)

        return res[::-1]


if __name__ == '__main__':
    filename = '../g.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPath(graph, 1)
    print(hamilton_loop.result())

    filename = '../g2.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPath(graph, 1)
    print(hamilton_loop.result())

    filename = '../g3.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPath(graph, 1)
    print(hamilton_loop.result())

    filename = '../g.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPathV2(graph, 1)
    print(hamilton_loop.result())

    filename = '../g2.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPathV2(graph, 1)
    print(hamilton_loop.result())

    filename = '../g3.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonPathV2(graph, 1)
    print(hamilton_loop.result())
