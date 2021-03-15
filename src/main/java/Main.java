import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int maxCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = reader.readLine().split(" ");
        int boundX = Integer.parseInt(sizes[0]);
        int boundY = Integer.parseInt(sizes[1]);
        int[][] network = new int[boundX][boundY];
        int[][] safe = new int[boundX][boundY];
        for (int i = 0; i < boundX; i++) {
            network[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < boundX; i++) {
            for (int j = 0; j < boundY; j++) {
                if (network[i][j] == 0) {
                    network[i][j] = 1;
                    placeWall(network, safe, new Point(i, j), 2);
                    network[i][j] = 0;
                }
            }
        }
        System.out.println(maxCount);
    }

    private static void placeWall(int[][] network, int[][] safe, Point beforePoint, int leftCount) {
        if (leftCount == 0) {
            safe = new int[network.length][network[0].length];
            for (int i = 0; i < network.length; i++) {
                for (int j = 0; j < network[0].length; j++) {
                    if (network[i][j] == 2) {
                        safe[i][j] = 2;
                        spreadVirus(network, safe, i, j);
                    }
                }
            }
            int count = 0;
            for (int i = 0; i < safe.length; i++) {
                for (int j = 0; j < safe[0].length; j++) {
                    if (safe[i][j] == 0 && network[i][j] == 0) {
                        count++;
                    }
                }
            }
            maxCount = Math.max(count, maxCount);
            return;
        }
        int beforeX = beforePoint.getX();
        int beforeY = beforePoint.getY() + 1;
        for (int i = beforeX; i < network.length; i++) {
            for (int j = beforeY; j < network[0].length; j++) {
                if (network[i][j] == 0) {
                    network[i][j] = 1;
                    placeWall(network, safe, new Point(i, j), leftCount - 1);
                    network[i][j] = 0;
                }
            }
            beforeY = 0;
        }
    }

    private static void spreadVirus(int[][] network, int[][] safe, int x, int y) {
        int[] directionX = {1, -1, 0, 0};
        int[] directionY = {0, 0, 1, -1};
        for (int i = 0; i < directionX.length; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];
            if (nextX < network.length && nextY < network[0].length && nextX >= 0 && nextY >= 0
                && network[nextX][nextY] == 0 && safe[nextX][nextY] == 0) {
                safe[nextX][nextY] = 2;
                spreadVirus(network, safe, nextX, nextY);
            }
        }
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
