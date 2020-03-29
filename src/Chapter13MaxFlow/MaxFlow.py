class MaxFlow:
    def __init__(self, network, s, t,empty_graph=False, directed=True, V=None):
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
