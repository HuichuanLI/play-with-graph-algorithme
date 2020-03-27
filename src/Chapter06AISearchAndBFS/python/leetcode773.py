from collections import deque, defaultdict
import copy


class Solution:
    steps = [[0, 1], [0, -1], [1, 0], [-1, 0]]

    def slidingPuzzle(self, board):
        target = [1, 2, 3, 4, 5, 0]
        temp_board = board[0] + board[1]
        if temp_board == target:
            return 0
        # bfs
        if 0 not in temp_board:
            return -1

        queue = deque()
        visited = defaultdict(bool)
        queue.append((0, temp_board))
        visited[tuple(temp_board)] = True
        while len(queue) > 0:
            curs = queue.popleft()
            temp_board = curs[1]
            index_i = 0
            for i in range(6):
                if temp_board[i] == 0:
                    index_i = i
                    break
            for step in self.steps:
                next_x = index_i // 3 + step[0]
                next_y = index_i % 3 + step[1]
                temp_board = copy.deepcopy(curs[1])

                if next_x >= 0 and next_x < 2 and next_y >= 0 and next_y < 3:
                    temp_board[next_x * 3 + next_y], temp_board[index_i] = temp_board[index_i], temp_board[
                        next_x * 3 + next_y]
                    if not visited[tuple(temp_board)]:
                        visited[tuple(temp_board)] = True

                        queue.append((curs[0] + 1, temp_board))
                        if temp_board == target:
                            return curs[0] + 1
        return -1
