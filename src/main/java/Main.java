import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;

public class Main {

    static int IMPORTANT_1;
    static int IMPORTANT_2;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] countInputs = reader.readLine().split(" ");
        int pointCount = Integer.parseInt(countInputs[0]);
        int lineCount = Integer.parseInt(countInputs[1]);
        int[] distance = new int[pointCount + 1];
        Map<Integer, List<Line>> map = new HashMap<>();
        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));

        for (int i = 0; i < lineCount; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!map.containsKey(inputs[0])) {
                map.put(inputs[0], new ArrayList<>());
            }
            if (!map.containsKey(inputs[1])) {
                map.put(inputs[1], new ArrayList<>());
            }
            map.get(inputs[0]).add(new Line(inputs[1], inputs[2]));
            map.get(inputs[1]).add(new Line(inputs[0], inputs[2]));
        }
        String[] importantPoints = reader.readLine().split(" ");
        IMPORTANT_1 = Integer.parseInt(importantPoints[0]);
        IMPORTANT_2 = Integer.parseInt(importantPoints[1]);

        int answer = Integer.MAX_VALUE;
        int total = 0;
        int[][] points = new int[][]{{1, IMPORTANT_1, IMPORTANT_2, pointCount},
            {1, IMPORTANT_2, IMPORTANT_1, pointCount}};

        for (int p = 0; p < points.length; p++) {
            for (int q = 0; q < points[0].length - 1; q++) {
                for (int i = 1; i <= pointCount; i++) {
                    distance[i] = Integer.MAX_VALUE;
                }
                if (points[p][q] == points[p][q + 1]) { // 중요
                    continue;
                }
                queue.add(new Line(points[p][q], 0));
                while (!queue.isEmpty()) {
                    Line now = queue.remove();
                    if (now.getWeight() > distance[now.getEnd()]) {
                        continue;
                    }
                    List<Line> value = map.get(now.getEnd());
                    Optional.ofNullable(value).orElse(new ArrayList<>())
                        .forEach(x -> {
                            if (x.getWeight() + now.getWeight() < distance[x.getEnd()]) {
                                distance[x.getEnd()] = x.getWeight() + now.getWeight();
                                queue.add(new Line(x.getEnd(), distance[x.getEnd()]));
                            }
                        });
                }
                total += distance[points[p][q + 1]];
                if (total == Integer.MAX_VALUE) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }

            answer = Integer.min(answer, total);
            total = 0;
        }
        System.out.println(answer);
    }
}

class Line {

    private final int end;
    private final int weight;

    public Line(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}
