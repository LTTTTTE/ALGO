import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int count = 0;

        int[] numbers = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        boolean[] cache = new boolean[1001];
        cache[0] = true;
        cache[1] = true;
        for (int i = 2; i < 1000; i++) {
            for (int j = 2; i * j <= 1000; j++) {
                cache[i * j] = true;
            }
        }
        for(int i : numbers) {
            if(!cache[i]) {
                count++;
            }
        }
        System.out.println(count);
    }
}
