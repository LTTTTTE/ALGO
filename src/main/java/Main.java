import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        int[] cache = new int[size];
        cache[0] = 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    cache[i] = Math.max(cache[j] + 1, cache[i]);
                }
            }
            if (cache[i] == 0) {
                cache[i] = 1;
            }
        }
        System.out.println(Arrays.stream(cache).max().getAsInt());
    }
}

