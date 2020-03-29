from Graph import Graph
from DFS import GraphDFS


class Kosaraju:

    def __init__(self, G, recursive=True):
        self._G = G
        self._visited = [-1] * G.V
        self._sccount = 0

        if not self._G.is_directed:
            raise ValueError('CC only works in directed graph')

        dfs = GraphDFS(self._G.reverse_graph())
        order = dfs.post_order[::-1]
        # 遍历所有的点，相当于遍历图中所有可能存在的联通块
        for v in order:
            if self._visited[v] == -1:
                if recursive:
                    self._dfs_recursive(v, self._sccount)
                else:
                    self._dfs_iteration(v, self._sccount)
                self._sccount += 1

    def _dfs_recursive(self, v, ccid):
        self._visited[v] = ccid
        for w in self._G.adj(v):
            if self._visited[w] == -1:
                self._dfs_recursive(w, ccid)

    def _dfs_iteration(self, v, ccid):
        """For preorder that's straight-forward by using one stack,
        but for postorder we need a augmented stack2
        """
        stack = [v]
        # within one func call, all visited nodes should be the same ccid
        self._visited[v] = ccid
        while stack:
            curr = stack.pop()
            for w in self._G.adj(curr):
                if self._visited[w] == -1:
                    stack.append(w)
                    # the same ccid
                    self._visited[w] = ccid

    def is_Stronglyconnected(self, v, w):
        self._G._validate_vertex(v)
        self._G._validate_vertex(w)
        return self._visited[v] == self._visited[w]

    @property
    def sccount(self):
        return self._sccount

    @property
    def groups(self):
        res = [[] for _ in range(self._sccount)]
        for v in range(self._G.V):
            res[self._visited[v]].append(v)
        return res


if __name__ == '__main__':
    filename = '../ug4.txt'
    g = Graph(filename, directed=True)
    scc = Kosaraju(g)

    print(scc.sccount)
    comp = scc.groups
    for sccid in range(len(comp)):
        temp = ['{} : '.format(sccid)]
        for w in comp[sccid]:
            temp.append('{} '.format(w))
        print(''.join(temp))
