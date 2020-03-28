from collections import namedtuple
from Chapter02GraphDFS.python.adjSet import adjSet as Graph


class Edge(namedtuple('Edge', ['v', 'w'])):

    def __str__(self):
        return '{}-{}'.format(self.v, self.w)

    def __repr__(self):
        return self.__str__()


class FindBridges:
    def __init__(self, G):
        self.graph = G
        self._visited = [False] * G.V
        # _ord记录每个点的访问顺序（根据_cnt的值，而且_cnt的值每访问完一个点自加1）
        self._ord = [-1] * G.V
        # _low记录每个点在当前所有已经访问过的点中（即_ord值比当前的点的_ord低）
        # 能够到达的最低的_ord值
        self._low = [-1] * G.V

        self._cnt = 0
        self._res = []
        # 遍历所有的点，相当于遍历图中所有可能存在的联通块
        for v in range(G.V):
            if not self._visited[v]:
                self._dfs(v, v)

    def _dfs(self, v, parent):
        self._visited[v] = True
        self._ord[v] = self._cnt
        self._low[v] = self._cnt
        self._cnt += 1
        for w in self.graph.adj(v):
            if not self._visited[w]:
                self._dfs(w, v)
                self._low[v] = min(self._low[v], self._low[w])
                # v->w是桥
                if self._low[w] > self._ord[v]:
                    self._res.append(Edge(v, w))

            elif w != parent:
                self._low[v] = min(self._low[v], self._low[w])

    @property
    def result(self):
        return self._res


if __name__ == '__main__':
    filename = '../g.txt'
    g = Graph(filename)
    find_bridegs = FindBridges(g)
    print(find_bridegs.result)

    filename = '../g2.txt'
    g = Graph(filename)
    find_bridegs = FindBridges(g)
    print(find_bridegs.result)

    filename = '../g3.txt'
    g = Graph(filename)
    find_bridegs = FindBridges(g)
    print(find_bridegs.result)

    filename = '../tree.txt'
    g = Graph(filename)
    find_bridegs = FindBridges(g)
    print(find_bridegs.result)
