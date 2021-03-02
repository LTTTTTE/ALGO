import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int sizeX = Integer.parseInt(input[0]);
        int sizeY = Integer.parseInt(input[1]);
        int[][] network = new int[sizeX][sizeY];
        int[][] visited = new int[sizeX][sizeY];
        int[][] die = new int[sizeX][sizeY];
        int[][] hole = new int[sizeX][sizeY];
        int count = 0;
        int lastDieCount = 0;
        boolean exist = true;

        for (int i = 0; i < sizeX; i++) {
            String netWorkInput = reader.readLine();
            String[] split = netWorkInput.split(" ");
            for (int j = 0; j < split.length; j++) {
                network[i][j] = Integer.parseInt(split[j]);
            }
        }
        Point point = new Point(1, 0);
        while (exist) {
            die = new int[sizeX][sizeY];
            visited = new int[sizeX][sizeY];
            findHole(network, visited, hole, sizeX, sizeY);
            visited = new int[sizeX][sizeY];
            while (point != null) {
                point = findPoint(network, visited, point);
                if (point != null) {
                    bfs(network, visited, die, hole, point, sizeX - 1, sizeY - 1);
                }
            }
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    if (die[i][j] == 1) {
                        network[i][j] = 0;
                    }
                }
            }
            count++;
            point = new Point(1, 0);
            hole = new int[sizeX][sizeY];
//            for (int i = 0; i < sizeX; i++) {
//                for (int j = 0; j < sizeY; j++) {
//                    System.out.print(network[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            exist = exist(network, sizeX, sizeY);
        }

        System.out.println(count);
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (die[i][j] == 1) {
                    lastDieCount++;
                }
            }
        }
        System.out.println(lastDieCount);
    }

    private static void findHole(int[][] network, int[][] visited, int[][] hole, int boundX, int boundY) {
        Queue<Point> points = new LinkedList<>();
        points.add(new Point(0, 0));
        visited[0][0] = 1;
        int[] directionX = {1, -1, 0, 0};
        int[] directionY = {0, 0, 1, -1};
        while (!points.isEmpty()) {
            Point now = points.remove();
            for (int i = 0; i < directionX.length; i++) {
                Point next = new Point(now.getX() + directionX[i], now.getY() + directionY[i]);
                if (next.getX() < 0 || next.getY() < 0 || next.getX() >= boundX || next.getY() >= boundY) {
                    continue;
                }
                if (network[next.getX()][next.getY()] != 0) {
                    continue;
                }
                if (visited[next.getX()][next.getY()] == 1) {
                    continue;
                }
                hole[next.getX()][next.getY()] = 1;
                visited[next.getX()][next.getY()] = 1;
                points.add(next);
            }
        }
    }

    private static void bfs(int[][] network, int[][] visited, int[][] die, int[][] hole, Point point, int boundX,
        int boundY) {
        Queue<Point> points = new LinkedList<>();
        points.add(point);
        visited[point.getX()][point.getY()] = 1;
        int[] directionX = {1, -1, 0, 0};
        int[] directionY = {0, 0, 1, -1};
        while (!points.isEmpty()) {
            Point now = points.remove();
            for (int i = 0; i < directionX.length; i++) {
                Point next = new Point(now.getX() + directionX[i], now.getY() + directionY[i]);
                if (network[next.getX()][next.getY()] == 0 && hole[next.getX()][next.getY()] == 1) {
                    die[now.getX()][now.getY()] = 1;
                    continue;
                }
                if (next.getX() < 1 || next.getY() < 1 || next.getX() >= boundX || next.getY() >= boundY) {
                    continue;
                }
                if (visited[next.getX()][next.getY()] == 1) {
                    continue;
                }

                visited[next.getX()][next.getY()] = 1;
                points.add(next);
            }
        }
    }


    private static Point findPoint(int[][] network, int[][] visited, Point beforePoint) {
        int beforeX = beforePoint.getX();
        int beforeY = beforePoint.getY() + 1;
        for (int i = beforeX; i < network.length; i++) {
            for (int j = beforeY; j < network[i].length; j++) {
                if (network[i][j] == 1 && visited[i][j] == 0) {
                    return new Point(i, j);
                }
            }
            beforeY = 0;
        }
        return null;
    }

    private static boolean exist(int[][] network, int sizeX, int sizeY) {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                if (network[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
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
