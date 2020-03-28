from Chapter02GraphDFS.python.adjSet import adjSet as Graph
from Chapter04GraphBFS.python.CC import CC
from copy import copy


class EulerLoop:
    def __init__(self, G):
        self._G = G

    def has_euler_loop(self):
        cc = CC(self._G)
        if cc.ccount > 1:
            return False

        for v in range(self._G.V()):
            if self._G.degree(v) % 2 != 0:
                return False

        return True

    def result(self):
        res = []
        if not self.has_euler_loop:
            return res
        stack = []
        currv = 0
        stack.append(1)
        newg = copy(self._G)
        while len(stack) > 0:
            if newg.degree(currv) > 0:
                stack.append(currv)
                w = list(newg.adj(currv))[0]
                newg.remove_edge(currv, w)
                currv = w
            else:
                res.append(currv)
                currv = stack.pop()
        return res



if __name__ == '__main__':
    filename = '../g.txt'
    g = Graph(filename)
    eluer_loop = EulerLoop(g)
    print(eluer_loop.result())

    filename = '../g2.txt'
    g = Graph(filename)
    eluer_loop = EulerLoop(g)
    print(eluer_loop.result())