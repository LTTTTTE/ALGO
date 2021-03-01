import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = reader.readLine().split(" ");
        int x = Integer.parseInt(s[0]);
        int y = Integer.parseInt(s[1]);

        int[][] network = new int[x + 1][y + 1];
        int[][] visited = new int[x + 1][y + 1];

        for (int i = 1; i < x + 1; i++) {
            String netWorkInput = reader.readLine();
            String[] split = netWorkInput.split("");
            for (int j = 0; j < split.length; j++) {
                network[i][j + 1] = Integer.parseInt(split[j]);
            }
        }

        Queue<Point> queue = new LinkedList<>();
        int count = bfs(network, visited, queue, x, y);
        System.out.println(count);
    }


    private static int bfs(int[][] network, int[][] visited, Queue<Point> queue, int endX, int endY) {
        int[] directionX = {1, 0, -1, 0};
        int[] directionY = {0, 1, 0, -1};
        visited[1][1] = 1;
        queue.add(new Point(1, 1));
        while (!queue.isEmpty()) {
            Point now = queue.remove();
            if (new Point(endX, endY).equals(now)) {
                return visited[endX][endY];
            }
            for (int i = 0; i < directionX.length; i++) {
                int nextX = now.getX() + directionX[i];
                int nextY = now.getY() + directionY[i];

                if (nextX < 1 || nextY < 1 || nextX > endX || nextY > endY) {
                    continue;
                }
                if (visited[nextX][nextY] != 0) {
                    continue;
                }
                if (network[nextX][nextY] == 0) {
                    continue;
                }
                visited[nextX][nextY] = visited[now.getX()][now.getY()] + 1;
                queue.add(new Point(nextX, nextY));
            }
        }
        return 0;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}