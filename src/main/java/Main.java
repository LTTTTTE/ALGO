import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int boundX = 0;
    static int boundY = 0;
    static int canCrash = 0;
    static int[][] network;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        boundX = Integer.parseInt(inputs[0]);
        boundY = Integer.parseInt(inputs[1]);
        canCrash = Integer.parseInt(inputs[2]);
        network = new int[boundX][boundY];
        visited = new boolean[boundX][boundY];
        for (int i = 0; i < boundX; i++) {
            network[i] = Arrays.stream(reader.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < boundX; i++) {
            for (int j = 0; j < boundY; j++) {
                if (network[i][j] == 1) {
                    network[i][j] = 2;
                    canCrash--;
                    crashWall(network, i, j);
                    network[i][j] = 1;
                    canCrash++;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void crashWall(int[][] network, int x, int y) {
        if (canCrash == 0) {
            visited = new boolean[boundX][boundY];
            visited[0][0] = true;
            int bfs = bfs(network, 0, 0);
            if (bfs != -1) {
                min = Integer.min(bfs, min);
            }
            return;
        }
        int nextY = y + 1;
        for (int i = x; i < boundX; i++) {
            for (int j = nextY; j < boundY; j++) {
                if (network[i][j] == 1) {
                    network[i][j] = 2;
                    canCrash--;
                    crashWall(network, x, y);
                    network[i][j] = 1;
                    canCrash++;
                }
            }
            nextY = 0;
        }
    }

    private static int bfs(int[][] network, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        int count = 1;
        queue.add(new Point(x, y));
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int k = 0; k < queueSize; k++) {
                Point now = queue.remove();
                for (int i = 0; i < dx.length; i++) {
                    if (now.getX() == boundX - 1 && now.getY() == boundY - 1) {
                        return count;
                    }
                    int nextX = now.getX() + dx[i];
                    int nextY = now.getY() + dy[i];
                    if (nextX < boundX && nextY < boundY && nextX >= 0 && nextY >= 0
                        && network[nextX][nextY] != 1 && !visited[nextX][nextY]) {
                        if (network[nextX][nextY] == 2 && count % 2 == 0) {
                            count++;
                        }
                        visited[nextX][nextY] = true;
                        queue.add(new Point(nextX, nextY));
                    }
                }
            }
            ++count;
        }
        return -1;
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
