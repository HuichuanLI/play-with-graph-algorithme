from WeightedGraph import WeightedGraph
from collections import deque


class MaxFlow:
    def __init__(self, network, s, t, empty_graph=False, directed=True, V=None):
        if not network.is_directed():
            raise ValueError('MaxFlow only works in directed graph!')

        if network.V < 2:
            raise ValueError('The network should have at least two points')

        network.validate_vertex(s)
        network.validate_vertex(t)

        if s == t:
            raise ValueError('s and t should be different')

        self._network = network
        self._s = s
        self._t = t

        self._max_flow = 0

        if not empty_graph:
            self._rG = self._network.generate_redisual_graph()
        else:
            # 生成残量图
            temp = []
            for v in range(self._network.V):
                for w in self._network.adj(v):
                    temp.append([w, v, 0])
            for each in temp:
                self._network.add_edge(*each)
            self._rG = self._network

        while True:
            aug_path = self._get_augmenting_path()
            if not aug_path:
                break
            f = 2 ** 31 - 1
            for i in range(1, len(aug_path)):
                v = aug_path[i - 1]
                w = aug_path[i]
                f = min(f, self._rG.get_weight(v, w))
            self._max_flow += f
            for i in range(1, len(aug_path)):
                v = aug_path[i - 1]
                w = aug_path[i]
                # 不管正向边还是反向边，更新的方式都是一样的

                # if self._network.has_edge(v, w):
                #     self._rG.set_weight(v, w, self._rG.get_weight(v, w) - f)
                #     self._rG.set_weight(w, v, self._rG.get_weight(w, v) + f)
                # else:
                #     self._rG.set_weight(w, v, self._rG.get_weight(w, v) + f)
                #     self._rG.set_weight(v, w, self._rG.get_weight(v, w) - f)
                self._rG.set_weight(w, v, self._rG.get_weight(w, v) + f)
                self._rG.set_weight(v, w, self._rG.get_weight(v, w) - f)

    def result(self):
        return self._max_flow

    def flow(self, v, w):
        if not self._network.has_edge(v, w):
            raise ValueError('No edge {}-{}'.format(v, w))
        return self._rG.get_weight(w, v)

    def _get_augmenting_path(self):
        queue = deque()
        pre = [-1] * self._network.V
        queue.append(self._s)
        pre[self._s] = self._s
        while len(queue) > 0:
            cur = queue.popleft()
            if cur == self._t:
                break
            for w in self._rG.adj(cur):
                if pre[w] == -1 and self._rG.get_weight(cur, w) > 0:
                    pre[w] = cur
                    queue.append(w)
        res = []
        if pre[self._t] == -1:
            return res

        cur = self._t
        while cur != self._s:
            res.append(cur)
            cur = pre[cur]
        res.append(self._s)
        return res[::-1]


if __name__ == "__main__":
    filename = '../network.txt'
    network = WeightedGraph(filename, directed=True)

    maxflow = MaxFlow(network, 0, 3)

    print(maxflow.result())

    for v in range(network.V):
        for w in network.adj(v):
            print('{}-{} : 流量：{} / 容量：{}'.format(v, w, maxflow.flow(v, w), network.get_weight(v, w)))

    print('=' * 30)
    filename = '../network2.txt'
    network = WeightedGraph(filename, directed=True)

    maxflow = MaxFlow(network, 0, 5)

    print(maxflow.result())

    for v in range(network.V):
        for w in network.adj(v):
            print('{}-{} : 流量：{} / 容量：{}'.format(v, w, maxflow.flow(v, w), network.get_weight(v, w)))

    print('=' * 30)
    filename = '../baseball.txt'
    network = WeightedGraph(filename, directed=True)

    maxflow = MaxFlow(network, 0, 10)

    print(maxflow.result())

    for v in range(network.V):
        for w in network.adj(v):
            print('{}-{} : 流量：{} / 容量：{}'.format(v, w, maxflow.flow(v, w), network.get_weight(v, w)))
