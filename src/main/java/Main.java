import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[][] network = new int[21][21];
        for (int i = 1; i < 20; i++) {
            String netWorkInput = reader.readLine();
            String[] split = netWorkInput.split(" ");
            for (int j = 0; j < split.length; j++) {
                network[i][j + 1] = Integer.parseInt(split[j]);
            }
        }

        Point point = findPoint(network, new Point(1, 1));
        do {
            int[] directionX = {1, -1, 1, -1, -1, 1, 0, 0};
            int[] directionY = {1, -1, 0, 0, 1, -1, 1, -1};

            for (int i = 0; i < directionX.length; i = i + 2) {
                int count = dfs(network, directionX[i], directionY[i], point.getX(), point.getY())
                    + dfs(network, directionX[i + 1], directionY[i + 1], point.getX(), point.getY());
                if (count == 4) {
                    System.out.println(network[point.getX()][point.getY()]);
                    System.out.println(point.getX() + " " + point.getY());
                    return;
                }
            }
            point = findPoint(network, point);
        } while (point.getX() != 20 || point.getY() != 20);
        System.out.println(0);
    }

    private static int dfs(int[][] network, int directionX, int directionY, int nowX, int nowY) {
        int nextX = nowX + directionX;
        int nextY = nowY + directionY;
        if (nextX < 1 || nextX > 20 || nextY < 1 || nextY > 20) {
            return 0;
        }
        if (network[nextX][nextY] == 0 || network[nextX][nextY] != network[nowX][nowY]) {
            return 0;
        }
        return dfs(network, directionX, directionY, nextX, nextY) + 1;
    }


    private static Point findPoint(int[][] network, Point beforePoint) {
        int beforeX = beforePoint.getX();
        int beforeY = beforePoint.getY();
        for (int i = beforeX; i < network.length; i++) {
            for (int j = beforeY + 1; j < network.length; j++) {
                if (network[i][j] != 0) {
                    return new Point(i, j);
                }
            }
            beforeY = 0;
        }
        return new Point(20, 20);
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