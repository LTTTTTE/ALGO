import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        int doubleBound = (int) Math.sqrt(number);
        boolean[] cache = new boolean[(int) (number * 1.7)];
        int count = 0;

        for (int i = 2; i <= doubleBound; i++) {
            for (int j = 1; i * i * j <= number; j++) {
                cache[i * i * j] = true;
            }
        }
        for (int i = 1; i < cache.length; i++) {
            if (!cache[i]) {
                count++;
            }
            if (count == number) {
                System.out.println(i);
                break;
            }
        }
    }
}
