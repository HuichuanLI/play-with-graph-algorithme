from collections import deque, defaultdict


class Solution:
    def openLock(self, deadends, target):
        if target in deadends:
            return -1
        if "0000" in deadends:
            return -1
        if target == "0000":
            return 0

        # BFS
        self.queue = deque()
        self.visited = defaultdict(bool)
        self.queue.append([0, "0000"])
        self.visited["0000"] = True
        while len(self.queue) > 0:
            curs = self.queue.popleft()
            nexts = []
            for i in range(4):
                int_ch = int(curs[i])
                new_int_ch1 = (int_ch + 1) % 10
                new_int_ch2 = (int_ch + 9) % 10
                nexts.append(curs[:i] + str(new_int_ch1) + curs[i + 1:])
                nexts.append(curs[:i] + str(new_int_ch2) + curs[i + 1:])
            for next in nexts:
                if next not in deadends and not not self.visited[next]:
                    if next == target:
                        return curs[0] + 1
                    self.visited[next] = True
                    self.queue.append((curs[0] + 1, next))
        return -1
