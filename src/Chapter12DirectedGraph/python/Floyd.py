from WeightedGraph import WeightedGraph
from Chapter11ShortestaPath.python.Floyd import Floyd

if __name__ == '__main__':
    filename = '../wg.txt'
    g = WeightedGraph(filename, directed=True)
    floyd = Floyd(g)

    if not floyd.has_neg_cycle():
        for v in range(g.V):
            strings = []
            for w in range(g.V):
                strings.append(str(floyd.dist_to(v, w)))
            print(' '.join(strings))
    else:
        print('exist negative cycle')

    filename = '../wg2.txt'
    g = WeightedGraph(filename, directed=True)
    floyd = Floyd(g)

    if not floyd.has_neg_cycle():
        for v in range(g.V):
            strings = []
            for w in range(g.V):
                strings.append(str(floyd.dist_to(v, w)))
            print(' '.join(strings))
    else:
        print('exist negative cycle')
