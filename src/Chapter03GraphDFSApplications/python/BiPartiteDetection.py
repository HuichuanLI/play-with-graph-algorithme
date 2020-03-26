from adjSet import adjSet as Graph


class BiPartiteDetection:

    def __init__(self, G, recursive=True):
        self.colors = [-1] * G.V
        self._G = G
        self._visited = [False] * G.V
        self.bicycle = False
        # 遍历所有的点，相当于遍历图中所有可能存在的联通块
        for v in range(G.V):
            if not self._visited[v]:
                if recursive:
                    if self._dfs_recursive(v, 0):
                        self.bicycle = True
                else:
                    if self._dfs_iteration(v, 0):
                        self.bicycle = True

    def _dfs_recursive(self, v, color):
        self._visited[v] = True
        self.colors[v] = color
        for w in self._G.adj(v):
            if not self._visited[w]:
                if not self._dfs_recursive(w, 1 - color):
                    return False
            elif self.colors[w] == color:
                return False
        return True

    def _dfs_iteration(self, v, color):
        """For pre-order that's straight-forward by using one stack,
        but for post-order we need an augmented stack2
        """
        stack1 = [v]
        self._visited[v] = True
        self.colors[v] = color
        while stack1:
            curr = stack1.pop()
            for w in self._G.adj(curr):
                if not self._visited[w]:
                    stack1.append(w)
                    self._visited[w] = True
                    self.colors[w] = 1 - self.colors[curr]
                elif self.colors[w] == self.colors[curr]:
                    return False
        return True

    @property
    def Hasbicycle(self):
        return self.bicycle


if __name__ == '__main__':
    print('For one block, recursive:')
    filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g.txt'
    g = Graph(filename)
    graph_dfs = BiPartiteDetection(g)
    print(graph_dfs.Hasbicycle)

    print('*' * 40)

    # print('For two blocks, recursive:')
    # filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g2.txt'
    # g = Graph(filename)
    # graph_dfs = BiPartiteDetection(g)
    # print(graph_dfs.bicycle)
    #
    # print('*' * 40)
    #
    # print('For one block, iteration:')
    # filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g.txt'
    # g = Graph(filename)
    # graph_dfs = BiPartiteDetection(g, recursive=False)
    # print(graph_dfs.pre_order)
    # print(graph_dfs.bicycle)
    #
    # print('*' * 40)
    #
    # print('For two blocks, iteration:')
    # filename = '/Users/hui/Desktop/java/play-with-graph-algorithme/src/Chapter02GraphDFS/g2.txt'
    # g = Graph(filename)
    # graph_dfs = GraphDFS(g, recursive=False)
    # print(graph_dfs.pre_order)
    # print(graph_dfs.post_order)
