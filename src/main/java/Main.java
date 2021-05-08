import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
    }

    public static String solution(int length, int now, String[] cmd) {
        Point head = null;
        Point nowPoint = null;
        Point beforePoint = null;
        for (int i = 0; i < length; i++) {
            Point point = new Point(i);
            point.setBefore(beforePoint);
            if (beforePoint == null) {
                head = point;
            }
            if (beforePoint != null) {
                beforePoint.setNext(point);
            }
            if (i == now) {
                nowPoint = point;
            }
            beforePoint = point;
        }

        Stack<Point> deleted = new Stack<>();

        for (int i = 0; i < cmd.length; i++) {
            if (cmd[i].startsWith("D")) {
                int value = Integer.parseInt(cmd[i].split(" ")[1]);
                nowPoint = nowPoint.findNext(value);
                continue;
            }
            if (cmd[i].startsWith("U")) {
                int value = Integer.parseInt(cmd[i].split(" ")[1]);
                nowPoint = nowPoint.findBefore(value);
                continue;
            }
            if (cmd[i].equals("C")) {
                Point before = nowPoint.getBefore();
                Point next = nowPoint.getNext();
                if (before != null) {
                    before.setNext(next);
                }
                if (next != null) {
                    next.setBefore(before);
                }
                deleted.push(nowPoint);
                if (next == null) {
                    if (before != null) {
                        nowPoint = before;
                    }
                }
                continue;
            }
            if (cmd[i].equals("Z")) {
                Point pop = deleted.pop();
                Point before = pop.getBefore();
                Point next = pop.getNext();
                if (before != null) {
                    before.setNext(pop);
                }
                if (next != null) {
                    next.setBefore(pop);
                }
            }
        }
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (head.getIndex() == i) {
                answer.add("O");
            } else {
                answer.add("X");
                continue;
            }
            head = head.getNext();
        }
        return String.join("", answer);
    }
    //    public static String solution2(int length, int now, String[] cmd) {
//        now = now + 1;
//        Map<Integer, Boolean> map = new HashMap<>();
//        for (int i = 1; i <= length; i++) {
//            map.put(i, true);
//        }
//        Stack<Integer> deleted = new Stack<>();
//        for (int i = 0; i < cmd.length; i++) {
//            if (cmd[i].startsWith("D")) {
//                int value = Integer.parseInt(cmd[i].split(" ")[1]);
//                for (int k = 1; k <= value; k++) {
//                    if (!map.get(now + k)) {
//                        value++;
//                    }
//                }
//                now += value;
//                continue;
//            }
//            if (cmd[i].startsWith("U")) {
//                int value = Integer.parseInt(cmd[i].split(" ")[1]);
//                for (int k = 1; k <= value; k++) {
//                    if (!map.get(now - k)) {
//                        value++;
//                    }
//                }
//                now -= value;
//                continue;
//            }
//            if (cmd[i].equals("C")) {
//                map.replace(now, false);
//                deleted.push(now);
//                int value = 1;
//                while (value + now <= length) {
//                    if (!map.get(now + value)) {
//                        value++;
//                    } else {
//                        break;
//                    }
//                }
//                if (now + value <= length) {
//                    now += value;
//                } else {
//                    value = 0;
//                    while (value < now) {
//                        if (!map.get(now - value)) {
//                            value++;
//                        } else {
//                            break;
//                        }
//                    }
//                    now -= value;
//                }
//                continue;
//            }
//            if (cmd[i].equals("Z")) {
//                int value = deleted.pop();
//                map.replace(value, true);
//            }
//        }
//        return map.values().stream().map(x -> x ? "O" : "X").collect(Collectors.joining());
//    }
}

class Point {

    private int index;
    private Point before;
    private Point next;

    public Point(int index) {
        this.index = index;
    }

    public Point findNext(int count) {
        if (count == 0) {
            return this;
        }
        return this.next.findNext(count - 1);
    }

    public Point findBefore(int count) {
        if (count == 0) {
            return this;
        }
        return this.before.findBefore(count - 1);
    }

    public int getIndex() {
        return index;
    }

    public Point getBefore() {
        return before;
    }

    public void setBefore(Point before) {
        this.before = before;
    }

    public Point getNext() {
        return next;
    }

    public void setNext(Point next) {
        this.next = next;
    }
}
