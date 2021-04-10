import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int size = Integer.parseInt(reader.readLine());

        PriorityQueue<Integer> topQueue = new PriorityQueue<>(Integer::compare);
        PriorityQueue<Integer> botQueue = new PriorityQueue<>(Collections.reverseOrder());

        int mid = 0;
        int[] number = new int[size];
        for (int i = 0; i < size; i++) {
            number[i] = Integer.parseInt(reader.readLine());
        }

        for (int i = 0; i < size; i++) {
            int input = number[i];
            if (i == 0) {
                mid = input;
                System.out.println(mid);
                continue;
            }
            if (input > mid) {
                topQueue.add(input);
            } else {
                botQueue.add(input);
            }
            if (botQueue.size() > topQueue.size()) {
                topQueue.add(mid);
                mid = botQueue.remove();
            } else if (botQueue.size() < topQueue.size() && i % 2 == 0) {
                botQueue.add(mid);
                mid = topQueue.remove();
            }
            writer.write(mid + "\n");
        }
        writer.flush();
        writer.close();
    }
}