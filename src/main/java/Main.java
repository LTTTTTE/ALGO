import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inits = reader.readLine().split(" ");
        int nodeSize = Integer.parseInt(inits[0]);
        int lineSize = Integer.parseInt(inits[1]);

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
            distance[inputs[0] - 1][inputs[1] - 1] = Integer.min(distance[inputs[0] - 1][inputs[1] - 1], 1);
            distance[inputs[1] - 1][inputs[0] - 1] = Integer.min(distance[inputs[1] - 1][inputs[0] - 1], 1);
        }

        for (int mid = 0; mid < nodeSize; mid++) {
            for (int start = 0; start < nodeSize; start++) {
                for (int end = 0; end < nodeSize; end++) {
                    int before = distance[start][mid] + distance[mid][end];
                    if (distance[start][end] > before) {
                        distance[start][end] = before;
                    }
                }
            }
        }

        int firstAnswer = 0;
        int secondAnswer = 0;
        int total = Integer.MAX_VALUE;

        for (int first = 0; first < nodeSize; first++) {
            for (int second = 0; second < nodeSize; second++) {
                int sum = 0;
                for (int house = 0; house < nodeSize; house++) {
                    sum += Integer.min(distance[first][house], distance[second][house]);
                }
                if (total > sum) {
                    firstAnswer = first;
                    secondAnswer = second;
                    total = sum;
                }
            }
        }
        System.out.println((firstAnswer + 1) + " " + (secondAnswer + 1) + " " + total * 2);
    }
}