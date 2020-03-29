from Graph import Graph
from Chapter02GraphDFS.python.GraphDFS import GraphDFS


if __name__ == '__main__':
    filename = '../ug.txt'
    g = Graph(filename, directed=True)
    graph_dfs = GraphDFS(g)

    print(g)
    print('DFS pre order: ', graph_dfs.pre_order)
    print('DFS post order: ', graph_dfs.post_order)