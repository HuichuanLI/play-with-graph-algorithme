class Solution:
    postion = [[0, 1], [1, 0], [0, -1], [-1, 0]]

    def solve(self, board):
        if not board:
            return board
        visited = [[False for _ in range(len(board[i]))] for i in range(len(board))]
        res = []
        m = len(board)
        n = len(board[0])
        for j in range(n):
            self._dfs(board, 0, j, visited)
            self._dfs(board, m - 1, j, visited)

        for i in range(m):
            self._dfs(board, i, 0, visited)
            self._dfs(board, i, n - 1, visited)

        for i in range(m):
            for j in range(n):
                if board[i][j] == "O":
                    board[i][j] = "X"
                elif board[i][j] == "+":
                    board[i][j] = "O"

    def _dfs(self, board, i, j, visited):
        visited[i][j] = True
        if board[i][j] == 'O':
            board[i][j] = '+'

            for next_step in self.postion:
                next_x = i + next_step[0]
                next_y = j + next_step[1]
                if next_x >= 0 and next_x < len(board) and next_y >= 0 and next_y < len(board[0]) and not \
                        visited[next_x][next_y]:
                    self._dfs(board,next_x, next_y, visited)