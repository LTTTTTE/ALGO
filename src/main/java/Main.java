import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[][] network = new int[size][size];
        int[][] distance = new int[size][size];
        boolean[][] visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            network[i] = Arrays.stream(reader.readLine().split("")).mapToInt(Main::mapChanger).toArray();
        }

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(Point::getWeight));
        queue.add(new Point(0, 0, 0));
        visited[0][0] = true;
        int[] directionX = {1, 0, -1, 0};
        int[] directionY = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            Point now = queue.remove();
            if (now.getX() == size - 1 && now.getY() == size - 1) {
                break;
            }

            if (distance[now.getX()][now.getY()] < now.getWeight()) {
                continue;
            }
            for (int i = 0; i < directionX.length; i++) {
                int nextX = now.getX() + directionX[i];
                int nextY = now.getY() + directionY[i];
                if (nextX >= size || nextY >= size || nextX < 0 || nextY < 0) {
                    continue;
                }
                if (distance[nextX][nextY] > network[nextX][nextY] && !visited[nextX][nextY]) {
                    distance[nextX][nextY] = now.getWeight() + network[nextX][nextY];
                    queue.add(new Point(nextX, nextY, distance[nextX][nextY]));
                    visited[nextX][nextY] = true;
                }
            }
        }
        System.out.println(distance[size - 1][size - 1]);
    }

    private static int mapChanger(String input) {
        return input.equals("1") ? 0 : 1;
    }
}

class Point {

    private final int x;
    private final int y;
    private final int weight;

    public Point(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWeight() {
        return weight;
    }
}
