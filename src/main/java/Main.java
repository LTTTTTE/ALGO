import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sinHoCount = inputs[0];
        int wantCount = inputs[1];
        int brokenCount = inputs[2];
        int[] sinHo = new int[sinHoCount];
        for (int i = 0; i < brokenCount; i++) {
            sinHo[Integer.parseInt(reader.readLine()) - 1] = 1;
        }
        int start = 0;
        int end = wantCount - 1;
        int sum = 0;
        for (int i = 0; i <= end; i++) {
            sum += sinHo[i];
        }
        int answer = sum;
        while (end + 1 < sinHoCount) {
            int next = sum - sinHo[start] + sinHo[end + 1];
            answer = Integer.min(next , answer);
            sum = next;
            start++;
            end++;
        }
        System.out.println(answer);
    }
}