import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        Queue<int[]> meets = new PriorityQueue<>(Comparator.comparingInt(x -> ((int[]) x)[1])
            .thenComparingInt(x -> ((int[]) x)[0]));
        for (int i = 0; i < size; i++) {
            String[] inputs = reader.readLine().split(" ");
            meets.add(new int[]{Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])});
        }
        int count = 0;
        int endTime = 0;
        while (!meets.isEmpty()) {
            int[] meet = meets.remove();
            if (meet[0] >= endTime) {
                endTime = meet[1];
                count++;
                continue;
            }
            if (meet[1] < endTime) {
                meets.add(meet);
                endTime = meet[1];
            }
        }
        System.out.println(count);
    }
}
