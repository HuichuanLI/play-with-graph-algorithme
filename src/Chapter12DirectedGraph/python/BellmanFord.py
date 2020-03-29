from WeightedGraph import WeightedGraph
from Chapter11ShortestaPath.python.BellmanFord import BellmanFord


if __name__ == '__main__':
    filename = '../wg.txt'
    g = WeightedGraph(filename, directed=True)
    bf = BellmanFord(g, s=0)

    if not bf.has_neg():
        strings = []
        for v in range(g.V):
            strings.append(bf.dist_to(v))
        print(' '.join(str(i) for i in strings))
        print(bf.path(1))
    else:
        print('exist negative circle')


    filename = '../wg2.txt'
    g = WeightedGraph(filename, directed=True)
    bf = BellmanFord(g, s=0)

    if not bf.has_neg():
        strings = []
        for v in range(g.V):
            strings.append(bf.dist_to(v))
        print(' '.join(str(i) for i in strings))
        print(bf.path(1))
    else:
        print('exist negative circle')