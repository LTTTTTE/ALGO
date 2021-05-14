import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeSize = Integer.parseInt(reader.readLine());
        int lineSize = Integer.parseInt(reader.readLine());

        int[][] distance = new int[nodeSize][nodeSize];

        for (int i = 0; i < distance.length; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE / 2);
        }

        for (int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[i].length; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < lineSize; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            distance[inputs[0] - 1][inputs[1] - 1] = Integer.min(distance[inputs[0] - 1][inputs[1] - 1], inputs[2]);
        }

        for (int mid = 0; mid < nodeSize; mid++) {
            for (int start = 0; start < nodeSize; start++) {
                for (int end = 0; end < nodeSize; end++) {
                    int before = distance[start][mid] + distance[mid][end];
                    distance[start][end] = Integer.min(before, distance[start][end]);
                }
            }
        }

        for (int[] i : distance) {
            for (int j : i) {
                if (j == Integer.MAX_VALUE / 2) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
}