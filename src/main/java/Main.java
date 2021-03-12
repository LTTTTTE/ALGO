import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = reader.readLine().split("");
        String[] line2 = reader.readLine().split("");
        int[][] cache = new int[line1.length + 1][line2.length + 1];

        for (int i = 1; i <= line1.length; i++) {
            for (int j = 1; j <= line2.length; j++) {
                if (line1[i - 1].equals(line2[j - 1])) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i][j - 1], cache[i - 1][j]);
                }
            }
        }
        System.out.println(cache[line1.length][line2.length]);
    }
}
