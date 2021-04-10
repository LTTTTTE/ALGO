import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static Point answer;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int sizeX = Integer.parseInt(inputs[0]);
        int sizeY = Integer.parseInt(inputs[1]);

        int[][] network = new int[sizeX][sizeY];
        int[][] visited = new int[sizeX][sizeY];

        for (int i = 0; i < sizeX; i++) {
            network[i] = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, 0));
        visited[0][0] = 1;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Point now = queue.remove();
            if (now.getX() == sizeX - 1 && now.getY() == sizeY - 1) {
                answer = now;
                break;
            }
            for (int i = 0; i < dx.length; i++) {
                int nextX = now.getX() + dx[i];
                int nextY = now.getY() + dy[i];
                if (nextX < 0 || nextY < 0 || nextX >= sizeX || nextY >= sizeY) {
                    continue;
                }
                if (now.getPower() >= visited[nextX][nextY]) {
                    continue;
                }
                if (now.getPower() == 0 && network[nextX][nextY] == 1) {
                    queue.add(new Point(nextX, nextY, now.getDistance() + 1, now.getPower() + 1));
                    visited[nextX][nextY] = now.getPower() + 1;
                    continue;
                }

                if (network[nextX][nextY] == 0) {
                    queue.add(new Point(nextX, nextY, now.getDistance() + 1, now.getPower()));
                    visited[nextX][nextY] = now.getPower();
                }
            }
        }
        if (answer == null) {
            System.out.println(-1);
        } else {
            System.out.println(answer.getDistance());
        }
    }
}

class Point {

    private final int x;
    private final int y;
    private final int distance;
    private final int power;

    public Point(int x, int y, int distance, int power) {
        this.x = x;
        this.y = y;
        this.distance = distance;
        this.power = power;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDistance() {
        return distance;
    }

    public int getPower() {
        return power;
    }
}