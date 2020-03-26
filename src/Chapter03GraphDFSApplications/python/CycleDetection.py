from Chapter02GraphDFS.python.adjSet import adjSet as Graph


class CycleDetection:

    def __init__(self, G, recursive=True):
        self._pre = [-1] * G.V
        self._G = G
        self._visited = [False] * G.V
        self.cycle = False
        # 遍历所有的点，相当于遍历图中所有可能存在的联通块
        for v in range(G.V):
            if not self._visited[v]:
                if recursive:
                    self._pre[v] = v
                    if self._dfs_recursive(v):
                        self.hascycle = True
                        break

                else:
                    self._pre[v] = v
                    if self._dfs_iteration(v):
                        self.hascycle = True
                        break

    def _dfs_recursive(self, v):
        self._visited[v] = True
        for w in self._G.adj(v):
            if not self._visited[w]:
                self._pre[w] = v
                if self._dfs_recursive(w):
                    return True

            elif w != self._pre[v]:
                return True
        return False

    def _dfs_iteration(self, v):
        """For pre-order that's straight-forward by using one stack,
        but for post-order we need an augmented stack2
        """
        stack1 = [v]
        self._visited[v] = True
        while stack1:
            curr = stack1.pop()
            for w in self._G.adj(curr):
                if not self._visited[w]:
                    stack1.append(w)
                    self._visited[w] = True
                    self._pre[w] = curr
                elif self._pre[w] != curr:
                    self.cycle = True

    @property
    def hascycle(self):
        return self.cycle


if __name__ == '__main__':
    print('For one block, recursive:')
    filename = '/Chapter03GraphDFSApplications/g.txt'
    g = Graph(filename)
    graph_dfs = CycleDetection(g)
    print(graph_dfs.hascycle)

    print('*' * 40)

    print('For two blocks, recursive:')
    filename = '/Chapter03GraphDFSApplications/g2.txt'
    g = Graph(filename)
    graph_dfs = CycleDetection(g)
    print(graph_dfs.hascycle)

    print('*' * 40)

    print('For one block, iteration:')
    filename = '/Chapter03GraphDFSApplications/g.txt'
    g = Graph(filename)
    graph_dfs = CycleDetection(g, recursive=False)
    print(graph_dfs.hascycle)

    print('*' * 40)

    print('For two blocks, iteration:')
    filename = '/Chapter03GraphDFSApplications/g2.txt'
    g = Graph(filename)
    graph_dfs = CycleDetection(g, recursive=False)
    print(graph_dfs.hascycle)
