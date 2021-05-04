import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int studentsCount = Integer.parseInt(inputs[0]);
        int jewelryCount = Integer.parseInt(inputs[1]);
        int[] jewelry = new int[jewelryCount];

        for (int i = 0; i < jewelryCount; i++) {
            jewelry[i] = Integer.parseInt(reader.readLine());
        }
        int start = 1;
        int end = Arrays.stream(jewelry).max().getAsInt();
        int now;
        int answer = Integer.MAX_VALUE;
        while (start <= end) {
            int divideTo = 0;
            now = (start + end) / 2;
            for (int i = 0; i < jewelry.length; i++) {
                divideTo += jewelry[i] / now;
                if (jewelry[i] % now != 0) {
                    divideTo++;
                }
            }
            if (divideTo > studentsCount) {
                start = now + 1;
            } else {
                answer = Integer.min(answer, now);
                end = now - 1;
            }
        }
        System.out.println(answer);
    }
}