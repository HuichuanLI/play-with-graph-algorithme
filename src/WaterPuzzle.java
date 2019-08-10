import java.util.*;

public class WaterPuzzle {

    private int[] pre = new int[100];
    private int end = -1;

    public WaterPuzzle() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visted = new boolean[100];

        queue.add(0);
        visted[0] = true;
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            int a = cur / 10;
            int b = cur % 10;

            // max a = 5
            // max b =3;
            ArrayList<Integer> nexts = new ArrayList<>();

            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);

            nexts.add(b);
            nexts.add(a * 10);


            int x = Math.min(a, 3 - b);
            nexts.add((a - x) * 10 + b + x);
            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + b - y);


            for (int next : nexts) {
                if (!visted[next]) {
                    visted[next] = true;
                    pre[next] = cur;
                    queue.add(next);
                    if (next / 10 == 4 || next % 10 == 4) {
                        end = next;
                        return;
                    }
                }
            }
        }
    }

    public Iterable<Integer> result() {
        ArrayList<Integer> res = new ArrayList<>();

        if (end == -1)
            return res;
        int cur = end;
        while (cur != 0) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println((new WaterPuzzle()).result());
    }
}
