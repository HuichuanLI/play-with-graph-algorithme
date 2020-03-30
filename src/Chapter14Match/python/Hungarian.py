from Chapter04GraphBFS.python.BipartitionDetection import BiPartitionDetection
from collections import deque
from Chapter12DirectedGraph.python.Graph import Graph


# O(V*E)
class Hungarian:
    def __init__(self, G, use_dfs=False):
        bd = BiPartitionDetection(G)
        if bd.is_bi_partition_graph() is False:
            raise ValueError('Hungarian only works for bipartite graph')

        self._G = G
        self._maxmatching = 0
        self._matching = [-1] * self._G.V

        colors = bd.colors()
        for v in range(G.V):
            if colors[v] == 0 and self._matching[v] == -1:
                if use_dfs:
                    self._visited = [False] * self._G.V
                    find_aug_path = self.dfs(v)
                else:
                    find_aug_path = self.bfs(v)
                if find_aug_path:
                    self._maxmatching += 1

    def dfs(self, v):
        self._visited[v] = True
        for u in self._G.adj(v):
            if not self._visited[u]:
                self._visited[u] = True
                # if self._matching[u] == -1:
                #     self._matching[u] = v
                #     self._matching[v] = u
                #     return True
                # else:
                #     if self.dfs(self._matching[u]):
                #         self._matching[u] = v
                #         self._matching[v] = u
                #         return True
                if self._matching[u] == -1 or self.dfs(self._matching[u]):
                    self._matching[v] = u
                    self._matching[u] = v
                    return True
        return False

    def bfs(self, v):
        q = deque()
        pre = [-1] * self._G.V
        q.append(v)
        pre[v] = v

        while len(q) > 0:
            cur = q.popleft()
            for w in self._G.adj(cur):
                if pre[w] == -1:
                    if self._matching[w] != -1:
                        pre[w] = cur
                        pre[self._matching[w]] = w
                        q.append(self._matching[w])
                    else:
                        pre[w] = cur
                        augPath = self._get_aug_path(pre, v, w)
                        for i in range(0, len(augPath), 2):
                            self._matching[augPath[i]] = augPath[i + 1]
                            self._matching[augPath[i + 1]] = augPath[i]
                        return True
        return False

    def _get_aug_path(self, pre, start, end):
        res = []
        cur = end
        while cur != start:
            res.append(cur)
            cur = pre[cur]
        res.append(start)
        return res

    def maxmatching(self):
        return self._maxmatching


if __name__ == "__main__":
    filename = '../g.txt'
    g = Graph(filename)
    bm = Hungarian(g)
    print(bm.maxmatching())

    filename = '../g2.txt'
    g = Graph(filename)
    bm = Hungarian(g)
    print(bm.maxmatching())

    print('dfs:')
    filename = '../g.txt'
    g = Graph(filename)
    hungarian = Hungarian(g, use_dfs=True)
    print(hungarian.maxmatching())

    filename = '../g2.txt'
    g = Graph(filename)
    hungarian = Hungarian(g, use_dfs=True)
    print(hungarian.maxmatching())
