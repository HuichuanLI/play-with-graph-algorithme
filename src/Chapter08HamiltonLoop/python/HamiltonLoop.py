from Chapter02GraphDFS.python.adjSet import adjSet as Graph


class HamiltonLoop:

    def __init__(self, G):
        self._G = G
        self._visited = [False] * G.V
        self._pre = [0] * G.V
        self._end = -1
        self._dfs(0, 0, G.V)

    def _dfs(self, v, parent, left):
        self._visited[v] = True
        self._pre[v] = parent
        left -= 1
        if left == 0 and self._G.has_edge(v, 0):
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
        while curr != 0:
            res.append(curr)
            curr = self._pre[curr]
        res.append(0)

        return res[::-1]




if __name__ == '__main__':
    filename = '../g.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonLoop(graph)
    print(hamilton_loop.result())

    filename = '../g2.txt'
    graph = Graph(filename)
    hamilton_loop = HamiltonLoop(graph)
    print(hamilton_loop.result())

