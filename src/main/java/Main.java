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

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] countInputs = reader.readLine().split(" ");
        int pointCount = Integer.parseInt(countInputs[0]);
        int lineCount = Integer.parseInt(countInputs[1]);
        int startPoint = Integer.parseInt(reader.readLine());
        int[] distance = new int[pointCount + 1];
        for (int i = 1; i <= pointCount; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        Map<Integer, List<Line>> map = new HashMap<>();
        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));

        for (int i = 0; i < lineCount; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!map.containsKey(inputs[0])) {
                map.put(inputs[0], new ArrayList<>());
            }
            map.get(inputs[0]).add(new Line(inputs[1], inputs[2]));
        }
        queue.add(new Line(startPoint, 0));
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
        distance[startPoint] = 0;

        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }
}

class Line {

    private final int end;
    private int weight;

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

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
