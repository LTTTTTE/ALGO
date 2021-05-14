import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inits = reader.readLine().split(" ");
        int nodeSize = Integer.parseInt(inits[0]);
        int wantLength = Integer.parseInt(inits[1]);
        int lineSize = Integer.parseInt(inits[2]);
        int[] items = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] distance = new int[nodeSize][nodeSize];

        for (int[] i : distance) {
            Arrays.fill(i, Integer.MAX_VALUE / 2);
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
            distance[inputs[1] - 1][inputs[0] - 1] = Integer.min(distance[inputs[1] - 1][inputs[0] - 1], inputs[2]);
        }

        for (int mid = 0; mid < nodeSize; mid++) {
            for (int start = 0; start < nodeSize; start++) {
                for (int end = 0; end < nodeSize; end++) {
                    distance[start][end] = Integer.min(distance[start][mid] + distance[mid][end], distance[start][end]);
                }
            }
        }
        int[] answer = new int[distance.length];
        for (int i = 0; i < distance.length; i++) {
            int sum = 0;
            for (int j = 0; j < distance[i].length; j++) {
                if (distance[i][j] > wantLength) {
                    continue;
                }
                sum += items[j];
            }
            answer[i] = sum;
        }
        System.out.println(Arrays.stream(answer).max().getAsInt());
    }
}
