import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = reader.readLine().split("((?<=(\\+|-)|(?=(\\+|-))))");
        int answer = Integer.parseInt(split[0]);

        for (int i = 1; i < split.length; i++) {
            if (split[i].equals("-")) {
                for (int j = i + 1; j < split.length; j = j + 2) {
                    answer -= Integer.parseInt(split[j]);
                }
                break;
            }
            if (split[i].equals("+")) {
                answer += Integer.parseInt(split[i + 1]);
                i++;
            }
        }
        System.out.println(answer);
    }
}