import sys
from WeightedGraph import WeightedGraph

# O(V*E)
class BellmanFord:
    def __init__(self, G, s):
        self._G = G
        self._G._validate_vertex(s)
        self._s = s
        self._dis = [sys.maxsize] * self._G.V
        self._dis[self._s] = 0
        self._pre = [-1] * self._G.V
        self._pre[self._s] = self._s
        self._has_negative_circle = False

        for i in range(1, G.V):
            for v in range(G.V):
                for w in self._G.adj(v):
                    if self._dis[v] == sys.maxsize:
                        continue
                    if self._dis[w] > self._dis[v] + self._G.get_weight(v, w):
                        self._dis[w] = self._dis[v] + self._G.get_weight(v, w)
                        self._pre[w] = v
        # 检查是否有负权环
        for v in range(self._G.V):
            for w in self._G.adj(v):
                if self._dis[v] == sys.maxsize:
                    continue
                new_dis = self._dis[v] + self._G.get_weight(v, w)
                if new_dis < self._dis[w]:
                    self._has_negative_circle = True
                    break

    def has_neg(self):
        return self._has_negative_circle

    def is_connected_to(self, v):
        self._G._validate_vertex(v)
        return self._dis[v] != sys.maxsize

    def dist_to(self, v):
        self._G._validate_vertex(v)
        if self._has_negative_circle:
            raise ValueError('Exist negative circle.')
        return self._dis[v]

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
    filename = '../g.txt'
    g = WeightedGraph(filename)
    bf = BellmanFord(g, s=0)

    if not bf.has_neg():
        strings = []
        for v in range(g.V):
            strings.append(bf.dist_to(v))
            print(bf.path(v))
        print(' '.join(str(i) for i in strings))
    else:
        print('exist negative circle')


    filename = '../g2.txt'
    g = WeightedGraph(filename)
    bf = BellmanFord(g, s=0)

    if not bf.has_neg():
        strings = []
        for v in range(g.V):
            strings.append(bf.dist_to(v))
            print(bf.path(v))
        print(' '.join(str(i) for i in strings))
    else:
        print('exist negative circle')
