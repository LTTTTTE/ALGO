import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        solution(new String[][]{{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}});
    }

    public static int[] solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String[][] network = new String[5][5];
            for (int j = 0; j < 5; j++) {
                network[j] = places[i][j].split("");
            }
            Point point = findPoint(network, new Point(0, 0));
            boolean bfs = false;
            while (point != null) {
                bfs = bfs(network, point);
                if (bfs) {
                    answer.add(0);
                    break;
                }
                point = findPoint(network, point);
            }
            if (!bfs) {
                answer.add(1);
            }
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }

    public static boolean bfs(String[][] network, Point startPoint) {
        int[][] distance = new int[5][5];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);
        distance[startPoint.getX()][startPoint.getY()] = 0;
        while (!queue.isEmpty()) {
            Point now = queue.remove();
            if (distance[now.getX()][now.getY()] == 2) {
                continue;
            }
            for (int i = 0; i < dx.length; i++) {
                int nextX = now.getX() + dx[i];
                int nextY = now.getY() + dy[i];
                if (nextX == startPoint.getX() && nextY == startPoint.getY()) {
                    continue;
                }
                if (nextX < 0 || nextY < 0 || nextX >= 5 || nextY >= 5) {
                    continue;
                }
                if (network[nextX][nextY].equals("X")) {
                    continue;
                }
                if (network[nextX][nextY].equals("O") && distance[nextX][nextY] == 0) {
                    queue.add(new Point(nextX, nextY));
                    distance[nextX][nextY] = distance[now.getX()][now.getY()] + 1;
                }
                if (network[nextX][nextY].equals("P")) {
                    return true;
                }
            }
        }

        return false;
    }

    public static Point findPoint(String[][] network, Point beforePoint) {
        int nowX = beforePoint.getX();
        int nextY = beforePoint.getY() + 1;
        for (int i = nowX; i < 5; i++) {
            for (int j = nextY; j < 5; j++) {
                if (network[i][j].equals("P")) {
                    return new Point(i, j);
                }
            }
            nextY = 0;
        }
        return null;
    }
}

class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}