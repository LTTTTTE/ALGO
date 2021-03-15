import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int classes = Integer.parseInt(reader.readLine());
        int[] classMates = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] inputs = reader.readLine().split(" ");
        int masterCan = Integer.parseInt(inputs[0]);
        int secondCan = Integer.parseInt(inputs[1]);
        long sum = Arrays.stream(classMates)
            .map(x -> x - masterCan)
            .map(x -> Math.max(x, 0))
            .map(x -> (int) Math.ceil((double) x / secondCan))
            .mapToLong(x -> x)
            .sum();
        System.out.println(sum + (long) classes);
    }
}
