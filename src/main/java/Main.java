import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

        visited[1][1] = 1;
        int dfs = dfs(network, visited, 1, 1, x, y);

        System.out.println(dfs);
    }

    private static int dfs(int[][] network, int[][] visited, int nowX, int nowY, int endX, int endY) {
        int[] directionX = {1, 0, -1, 0};
        int[] directionY = {0, 1, 0, -1};
        for (int i = 0; i < directionX.length; i++) {
            int nextX = nowX + directionX[i];
            int nextY = nowY + directionY[i];
            if (nextX == endX && nextY == endY) {
                visited[nextX][nextY] = visited[nowX][nowY] + 1;
                return visited[endX][endY];
            }
            if (nextX < 1 || nextX > endX || nextY < 1 || nextY > endY) {
                continue;
            }
            if (network[nextX][nextY] == 1 && visited[nextX][nextY] == 0) {
                visited[nextX][nextY] = visited[nowX][nowY] + 1;
                dfs(network, visited, nextX, nextY, endX, endY);
            }
        }
        return visited[endX][endY];
    }
}