import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] network;
    static boolean[][] visited;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(reader.readLine());

        network = new char[size][size];
        visited = new boolean[size][size];
        int count = 0;
        int badCount = 0;

        for (int i = 0; i < size; i++) {
            network[i] = reader.readLine().toCharArray();
        }

        Point point = new Point(0, 0);
        while (point != null) {
            count += bfs(visited, point);
            point = findPoint(point);
        }

        point = new Point(0, 0);
        visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (network[i][j] == 'G') {
                    network[i][j] = 'R';
                }
            }
        }
        while (point != null) {
            badCount += bfs(visited, point);
            point = findPoint(point);
        }

        System.out.println(count + " " + badCount);
    }

    public static int bfs(boolean[][] visited, Point startPoint) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            Point now = queue.remove();
            for (int i = 0; i < dx.length; i++) {
                int nextX = now.getX() + dx[i];
                int nextY = now.getY() + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= size || nextY >= size) {
                    continue;
                }
                if (!visited[nextX][nextY] && network[nextX][nextY] == network[now.getX()][now.getY()]) {
                    visited[nextX][nextY] = true;
                    queue.add(new Point(nextX, nextY));
                }
            }
        }
        return 1;
    }

    public static Point findPoint(Point beforePoint) {
        int beforeX = beforePoint.getX();
        int beforeY = beforePoint.getY() + 1;
        for (int i = beforeX; i < size; i++) {
            for (int j = beforeY; j < size; j++) {
                if (!visited[i][j]) {
                    return new Point(i, j);
                }
            }
            beforeY = 0;
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