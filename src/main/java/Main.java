import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution2(new int[]{30,20,80,70}, 100));
    }

    public static int solution2(int[] people, int limit) {
        int answer = 0;
        int now = people.length - 1;
        int next = 0;
        Arrays.sort(people);
        while (now >= next) {
            if (people[now] + people[next] <= limit) {
                next++;
            }
            now--;
            answer++;
        }
        return answer;
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(x -> (int) x).reversed());
        for (int weight : people) {
            queue.add(weight);
        }

        while (!queue.isEmpty()) {
            int now = queue.remove();
            if (now == limit || queue.isEmpty()) {
                answer++;
                continue;
            }
            List<Integer> keep = new ArrayList<>();
            while (!queue.isEmpty()) {
                int next = queue.remove();
                if (now + next <= limit) {
                    break;
                } else {
                    keep.add(next);
                }
            }
            answer++;
            queue.addAll(keep);
        }
        return answer;
    }
}