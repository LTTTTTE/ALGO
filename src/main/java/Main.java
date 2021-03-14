import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] sizes = reader.readLine().split(" ");
        int boundX = Integer.parseInt(sizes[0]);
        int boundY = Integer.parseInt(sizes[1]);
        int[][] network = new int[boundX][boundY];
        for (int i = 0; i < boundX; i++) {
            network[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < boundX; i++) {
            for (int j = 0; j < boundY; j++) {
                if (network[i][j] == 0) {
                    network[i][j] = 1;
                }
            }
        }
    }
}
