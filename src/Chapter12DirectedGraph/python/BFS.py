from Graph import Graph
from Chapter04GraphBFS.python.GraphBFS import GraphBFS


if __name__ == '__main__':
    filename = '../ug.txt'
    g = Graph(filename, directed=True)
    graph_bfs = GraphBFS(g)
    print('BFS order : {}'.format(graph_bfs.order()))
