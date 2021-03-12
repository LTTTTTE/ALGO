import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = reader.readLine().split("");
        String[] line2 = reader.readLine().split("");
        int[][] cache = new int[line1.length][line2.length];

        if (line1[0].equals(line2[0])) {
            cache[0][0] = 0;
        } else {
            cache[0][0] = 1;
        }

        for (int i = 1; i < line2.length; i++) {
            if (line1[0].equals(line2[i])) {
                cache[0][i] = cache[0][i - 1];
            } else {
                cache[0][i] = cache[0][i - 1] + 1;
            }
        }

        for (int i = 1; i < line1.length; i++) {
            if (line1[i].equals(line2[0])) {
                cache[i][0] = cache[i - 1][0];
            } else {
                cache[i][0] = cache[i - 1][0] + 1;
            }
        }

        for (int i = 1; i < line1.length; i++) {
            for (int j = 1; j < line2.length; j++) {
                if (!line1[i].equals(line2[j])) {
                    cache[i][j] = Math.min(cache[i - 1][j - 1], Math.min(cache[i][j - 1], cache[i - 1][j])) + 1;
                } else {
                    cache[i][j] = cache[i - 1][j - 1];
                }
            }
        }
        System.out.println(cache[line1.length - 1][line2.length - 1]);
    }
}
