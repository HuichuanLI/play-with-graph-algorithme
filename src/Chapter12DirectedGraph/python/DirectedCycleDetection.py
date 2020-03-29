from Graph import Graph as Graph


class DirectedCycleDetection:

    def __init__(self, G, recursive=True):
        self._pre = [-1] * G.V
        self._G = G
        self._visited = [False] * G.V
        self._onPtah = [False] * G.V
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
        self._onPtah[v] = True
        for w in self._G.adj(v):
            if not self._visited[w]:
                self._pre[w] = v
                if self._dfs_recursive(w):
                    return True

            # 不需要判断 pre 因为如果2->1 和 1->2 也是环
            elif self._onPtah[w]:
                return True
        self._onPtah[v] = False
        return False

    def _dfs_iteration(self, v):
        """For pre-order that's straight-forward by using one stack,
        but for post-order we need an augmented stack2
        """
        stack1 = [v]
        self._visited[v] = True
        self._onPtah[v] = False
        while stack1:
            curr = stack1.pop()
            for w in self._G.adj(curr):
                if not self._visited[w]:
                    stack1.append(w)
                    self._visited[w] = True
                    self._onPtah[w] = True
                    self._pre[w] = curr
                elif self._onPtah[w]:
                    self.cycle = True
            self._onPtah[curr] = True

    @property
    def hascycle(self):
        return self.cycle


if __name__ == '__main__':
    filename = '../ug.txt'
    g = Graph(filename, directed=True)
    directed_cycle_detection = DirectedCycleDetection(g)
    print(directed_cycle_detection.hascycle)
