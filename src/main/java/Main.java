import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>(Integer::compare);
        for (int i = 0; i < size; i++) {
            queue.add(Integer.valueOf(reader.readLine()));
        }

        int answer = 0;

        while (!queue.isEmpty()) {
            int first = queue.remove();
            if (queue.isEmpty()) {
                break;
            }
            int second = queue.remove();
            answer += (first + second);
            if (!queue.isEmpty()) {
                queue.add(first + second);
            }
        }

        System.out.println(answer);
    }
}