import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class leetcode773 {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();

        HashMap<String, Integer> visted = new HashMap();

        String initalState = boardToString(board);
        if (initalState.equals("123450")) return 0;
        queue.add(initalState);


        visted.put(initalState, 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();
            ArrayList<String> nexts = getNexts(cur);

            for (String next : nexts) {
                if (!visted.containsKey(next)) {
                    queue.add(next);
                    visted.put(next, visted.get(cur) + 1);
                    System.out.println(next);
                    if (next.equals("123450")) {
                        return visted.get("123450");
                    }
                }
            }

        }
        return -1;
    }


    private ArrayList<String> getNexts(String s) {

        int[][] cur = stringToBoard(s);
        int zero;
        ArrayList<String> res = new ArrayList<>();
        for (zero = 0; zero < 6; zero++) {
            if (cur[zero / 3][zero % 3] == 0) {
                break;
            }
        }
        int zx = zero / 3, zy = zero % 3;
        for (int d = 0; d < 4; d++) {
            int nextx = zx + dirs[d][0], nexty = zy + dirs[d][1];

            if (nextx >= 0 && nextx < 2 && nexty >= 0 && nexty < 3) {
                cur[zx][zy] = cur[nextx][nexty];
                cur[nextx][nexty] = 0;
                res.add(boardToString(cur));
                cur[nextx][nexty] = cur[zx][zy];
                cur[zx][zy] = 0;

            }
        }

        return res;


    }

    private int[][] stringToBoard(String s) {
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i++) {
            board[i / 3][i % 3] = s.charAt(i) - '0';
        }
        return board;
    }

    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        int[][] board = {{1, 2, 3}, {4, 0, 5}};
        System.out.println((new leetcode773()).slidingPuzzle(board));
    }
}


