from Chapter02GraphDFS.python.adjSet import adjSet as Graph


class SingleSourcePath:

    def __init__(self, G, s, recursive=True):
        self._G = G
        self._s = s
        self._visited = [False] * G.V
        self._pre = [-1] * G.V
        self._ccount = 0

        # if self._G.is_directed:
        #     raise ValueError('CC only works in undirected graph')

        # 遍历所有的点，相当于遍历图中所有可能存在的联通块

        self._pre[s] = s
        if recursive:
            self._dfs_recursive(s)
        else:
            self._dfs_iteration(s)

    def _dfs_recursive(self, v):
        self._visited[v] = True
        for w in self._G.adj(v):
            if not self._visited[w]:
                self._pre[w] = v
                self._dfs_recursive(w)

    def _dfs_iteration(self, v):
        """For preorder that's straight-forward by using one stack,
        but for postorder we need a augmented stack2
        """
        stack = [v]
        # within one func call, all visited nodes should be the same ccid
        self._visited[v] = True
        while stack:
            curr = stack.pop()
            for w in self._G.adj(curr):
                if not self._visited[w]:
                    self._pre[w] = v
                    stack.append(w)
                    # the same ccid
                    self._visited[w] = True

    def is_connected_to(self, t):
        """this funtion is called during the dfs
        so if current node t is visited (self._visited[t] == True)
        this means the current t is connected to the source node
        """
        self._G._validate_vertex(t)
        return self._visited[t]

    def path(self, t):
        res = []
        if not self.is_connected_to(t):
            return res
        curr = t
        while curr != self._s:
            res.append(curr)
            curr = self._pre[curr]
        res.append(self._s)
        return res[::-1]


if __name__ == '__main__':
    filename = "/Chapter03GraphDFSApplications/g.txt"
    g = Graph(filename)

    single_source_path = SingleSourcePath(g, 0)
    print('0 -> 6: ' + str(single_source_path.path(6)))

    single_source_path = SingleSourcePath(g, 0)
    print('0 -> 5: ' + str(single_source_path.path(5)))
