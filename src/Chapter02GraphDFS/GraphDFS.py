from adjSet import adjSet


class GraphDFS:
    def __init__(self, G):
        self._pre_order = []
        self._post_order = []
        self._G = G
        self._visited = [False] * G.V
        self.dfs()

    def dfs(self):
        for i in range(self._G.V):
            if not self._visited[i]:
                self._visited[i] = True
                self._dfs_iteration(i)

    def _dfs(self, root):
        self._visited[root] = True
        self._pre_order.append(root)
        for w in self._G.adj(root):
            if not self._visited[w]:
                self._dfs(w)
        self._post_order.append(root)

    def _dfs_iteration(self, root):
        stack = [root]
        stack2 = []
        while len(stack) > 0:
            cur = stack.pop()
            stack2.append(cur)
            self._pre_order.append(cur)
            for w in self._G.adj(cur):
                if not self._visited[w]:
                    stack.append(w)
                    self._visited[w] = True
        self._post_order += stack2[::-1]

    @property
    def pre_order(self):
        return self._pre_order

    @property
    def post_order(self):
        return self._post_order


if __name__ == '__main__':
    # print('For one block, recursive:')
    filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g.txt'
    g = adjSet(filename)
    graph_dfs = GraphDFS(g)
    print(graph_dfs.pre_order)
    print(graph_dfs.post_order)

    print('*' * 40)

    # print('For two blocks, recursive:')
    filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g2.txt'
    g = adjSet(filename)
    graph_dfs = GraphDFS(g)
    print(graph_dfs.pre_order)
    print(graph_dfs.post_order)
