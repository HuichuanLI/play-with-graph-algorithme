import java.util.*;

public class leetcode752 {
    public int openLock(String[] deadends, String target) {

        HashSet<String> deadset = new HashSet<>();
        for (String s : deadends)
            deadset.add(s);

        if (deadset.contains(target))
            return -1;
        if (deadset.contains("0000"))
            return -1;
        if (target.equals("0000"))
            return 0;

        Queue<String> queue = new LinkedList<>();
        queue.add("0000");


        HashMap<String, Integer> pre = new HashMap<>();
        pre.put("0000", 0);
        while (!queue.isEmpty()) {
            String curs = queue.remove();
            char[] curarray = curs.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();

            for (int i = 0; i < 4; i++) {
                char o = curarray[i];
                curarray[i] = Character.forDigit((curarray[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curarray));

                curarray[i] = o;

                curarray[i] = Character.forDigit((curarray[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curarray));
                curarray[i] = o;
            }


            for (String next : nexts) {
                if (!deadset.contains(next) && !pre.containsKey(next)) {
                    queue.add(next);
                    pre.put(next, pre.get(curs) + 1);
                    if (next.equals(target))
                        return pre.get(target);
                }
            }
        }
        return -1;

    }

}
