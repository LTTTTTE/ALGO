import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int want = Integer.parseInt(inputs[1]);
        int[] video = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = Arrays.stream(video).max().getAsInt();
        int end = Arrays.stream(video).sum();

        int now;
        while (start <= end) {
            now = (start + end) / 2;
            int sum = 0;
            int count = 0;

            for (int i = 0; i < video.length; i++) {
                if (sum + video[i] > now) {
                    sum = 0;
                    count++;
                }
                sum += video[i];
            }
            if (sum > 0) {
                count++;
            }
            if (count > want) {
                start = now + 1;
            } else {
                end = now - 1;
            }
        }
        System.out.println(start);
    }
}