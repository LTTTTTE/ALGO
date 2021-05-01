import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {0, 3, 3}, {0, 4, 4}, {1, 3, 1}}));
    }

    public static int solution(int size, int[][] costs) {
        int answer = 0;
        boolean[] visited = new boolean[size + 1];
        int[] distance = new int[size + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Map<Integer, List<Line>> map = new HashMap<>();

        int start = 0;
        for (int i = 0; i < costs.length; i++) {
            int[] input = costs[i];
            if (!map.containsKey(input[0])) {
                map.put(input[0], new ArrayList<>());
            }
            map.get(input[0]).add(new Line(input[1], input[2]));
            if (!map.containsKey(input[1])) {
                map.put(input[1], new ArrayList<>());
            }
            map.get(input[1]).add(new Line(input[0], input[2]));
            start = input[0];
        }

        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));
        queue.add(new Line(start, 0));
        while (!queue.isEmpty()) {
            Line now = queue.remove();
            if (visited[now.getEnd()]) {
                continue;
            }
            visited[now.getEnd()] = true;
            for (Line next : map.get(now.getEnd())) {
                if (!visited[next.getEnd()] && distance[next.getEnd()] > next.getWeight()) {
                    distance[next.getEnd()] = next.getWeight();
                    queue.add(next);
                }
            }
        }
        distance[start] = 0;

        for (int i = 0; i < distance.length; i++) {
            if (visited[i]) {
                answer += distance[i];
            }
        }

        return answer;
    }
}

class Line {

    private final int end;
    private final int weight;

    public Line(int end, int weight) {
        this.weight = weight;
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public int getEnd() {
        return end;
    }
}