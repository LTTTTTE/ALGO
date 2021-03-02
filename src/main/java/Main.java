import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[][] network = new int[size][size];
        int[][] visited = new int[size][size];

        for (int i = 0; i < size; i++) {
            String netWorkInput = reader.readLine();
            String[] split = netWorkInput.split("");
            for (int j = 0; j < split.length; j++) {
                network[i][j] = Integer.parseInt(split[j]);
            }
        }
        Point point = new Point(0, -1);
        List<Integer> answer = new ArrayList<>();

        while (point.getX() != size || point.getY() != size) {
            point = findPoint(network, visited, point);
            if (point.getX() == -1 && point.getY() == -1) {
                break;
            }
            answer.add(bfs(network, visited, point, size));
        }
        System.out.println(answer.size());
        answer.stream()
            .sorted()
            .forEach(System.out::println);
    }

    private static int bfs(int[][] network, int[][] visited, Point point, int size) {
        Queue<Point> points = new LinkedList<>();
        points.add(point);
        int[] directionX = {1, -1, 0, 0};
        int[] directionY = {0, 0, -1, 1};
        int count = 1;
        visited[point.getX()][point.getY()] = 1;

        while (!points.isEmpty()) {
            Point now = points.remove();
            for (int i = 0; i < directionX.length; i++) {
                Point nextPoint = new Point(now.getX() + directionX[i], now.getY() + directionY[i]);
                if (nextPoint.getX() < 0 || nextPoint.getX() >= size ||
                    nextPoint.getY() < 0 || nextPoint.getY() >= size) {
                    continue;
                }
                if (network[nextPoint.getX()][nextPoint.getY()] == 0) {
                    continue;
                }
                if (visited[nextPoint.getX()][nextPoint.getY()] != 0) {
                    continue;
                }
                count++;
                visited[nextPoint.getX()][nextPoint.getY()] = 1;
                points.add(nextPoint);
            }
        }
        return count;
    }

    private static Point findPoint(int[][] network, int[][] visited, Point beforePoint) {
        int beforeX = beforePoint.getX();
        int beforeY = beforePoint.getY() + 1;
        for (int i = beforeX; i < network.length; i++) {
            for (int j = beforeY; j < network.length; j++) {
                if (network[i][j] == 1 && visited[i][j] == 0) {
                    return new Point(i, j);
                }
            }
            beforeY = 0;
        }
        return new Point(-1, -1);
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
