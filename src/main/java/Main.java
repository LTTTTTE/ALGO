import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] numbers = Arrays.stream(reader.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        List<Integer> answers = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            if (i == 0) {
                answers.add(numbers[i]);
                continue;
            }
            if (answers.get(i - 1) > 0) {
                answers.add(answers.get(i - 1) + numbers[i]);
            } else {
                answers.add(numbers[i]);
            }
        }
        System.out.println(answers.stream().mapToInt(x -> x).max().getAsInt());
    }
}

