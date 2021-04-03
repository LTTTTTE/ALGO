import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int sizeX = Integer.parseInt(inputs[0]);
        int sizeY = Integer.parseInt(inputs[1]);
        int powerInput = Integer.parseInt(inputs[2]);

        String[][] network = new String[sizeX][sizeY];
        int[][] distance = new int[sizeX][sizeY];
        int[][] power = new int[sizeX][sizeY];
//        boolean[][] visited = new boolean[sizeX][sizeY];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < sizeX; i++) {
            network[i] = reader.readLine().split("");
        }

        PriorityQueue<Point> queue = new PriorityQueue<>(Comparator.comparingInt(Point::getWeight));
        queue.add(new Point(0, 0, 1));
//        visited[0][0] = true;
        power[0][0] = powerInput;
        while (!queue.isEmpty()) {
            Point now = queue.remove();

            for (int i = 0; i < dx.length; i++) {
                int nextX = now.getX() + dx[i];
                int nextY = now.getY() + dy[i];
                int nowPower = power[now.getX()][now.getY()];
                int nextPower = nowPower;
                if (nextX < 0 || nextY < 0 || nextX >= sizeX || nextY >= sizeY) {
                    continue;
                }
                if (network[nextX][nextY].equals("1")) {
                    if (nowPower > 0) {
                        nextPower = nowPower - 1;
                    } else {
                        continue;
                    }
                }
//                String weight = network[nextX][nextY];
                if (distance[nextX][nextY] > now.getWeight() + 1) {
                    distance[nextX][nextY] = now.getWeight() + 1;
                    queue.add(new Point(nextX, nextY, distance[nextX][nextY]));
//                    visited[nextX][nextY] = true;
                    power[nextX][nextY] = nextPower;
                }
            }
        }
        distance[0][0] = 1;
        if (distance[sizeX - 1][sizeY - 1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(distance[sizeX - 1][sizeY - 1]);
        }
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
