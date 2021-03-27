import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        boolean[] cache = new boolean[size];
        int[][] visited = new int[size][size];
        int[][] network = new int[size][size];
        int[] dx = {1, 0, 0, 1};
        int[] dy = {0, 1, -1, 0};
        for (int i = 0; i < size; i++) {
            network[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Main::parse).toArray();
        }

        for (int i = 0; i < network.length; i++) {
            boolean wall = false;
            int nowX = 0;
            int nowY = i;
            while (true) {
                int nextX = nowX + dx[network[nowX][nowY]];
                int nextY = nowY + dy[network[nowX][nowY]];
                if (nextX >= size) {
                    cache[i] = true;
                    break;
                }
                if (network[nextX][nextY] == 3) {
                    if (wall) {
                        cache[i] = false;
                        break;
                    } else {
                        wall = true;
                    }
                }
                if (visited[nextX][nextY] != 0) {
                    visited[nextX][nextY] = i;
                } else {
                    cache[i] = cache[visited[nextX][nextY]];
                }
                nowX = nextX;
                nowY = nextY;
            }
        }
        System.out.println(cache);
    }

    private static int parse(String point) {
        if (point.equals("#")) {
            return 0;
        }
        if (point.equals(">")) {
            return 1;
        }
        if (point.equals("<")) {
            return 2;
        }
        return 3;
    }
}
