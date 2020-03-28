class UF:
    def __init__(self, n):
        self.parent = [i for i in range(n)]
        self.sz = [1 for _ in range(n)]

    def find(self, p):
        while p != self.parent[p]:
            p = self.parent[p]
        return p

    def union(self, p, q):
        proot = self.find(p)
        qroot = self.find(q)
        if proot == qroot:
            return
        if self.sz[proot] < self.sz[qroot]:
            self.parent[proot] = qroot
            self.sz[proot] += self.sz[qroot]
        else:
            self.parent[qroot] = proot
            self.sz[qroot] += self.sz[proot]

    def isConnected(self, p, q):
        return self.find(q) == self.find(p)
