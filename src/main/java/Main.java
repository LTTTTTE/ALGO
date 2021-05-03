import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        int[] origin = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int inputSize = Integer.parseInt(reader.readLine());
        int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < inputs.length; i++) {
            int start = 0;
            int end = origin.length - 1;
            int nowIndex = 0;
            boolean exist = false;

            while (start <= end) {
                nowIndex = (end + start) / 2;
                if (inputs[i] == origin[nowIndex]) {
                    exist = true;
                    break;
                } else {
                    if (inputs[i] < origin[nowIndex]) {
                        end = nowIndex - 1;
                    } else {
                        start = nowIndex + 1;
                    }
                }
            }
            if (exist) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}