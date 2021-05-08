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
        int pointCount = Integer.parseInt(reader.readLine());
        int lineCount = Integer.parseInt(reader.readLine());

        Map<Integer, List<Line>> map = new HashMap<>();
        for (int i = 0; i < lineCount; i++) {
            String[] inputs = reader.readLine().split(" ");
            int start = Integer.parseInt(inputs[0]);
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(new Line(inputs[1], inputs[2]));
//
//            start = Integer.parseInt(inputs[1]);
//            if (!map.containsKey(start)) {
//                map.put(start, new ArrayList<>());
//            }
//            map.get(start).add(new Line(inputs[0], inputs[2]));
        }
        String[] inputs = reader.readLine().split(" ");
        int startPoint = Integer.parseInt(inputs[0]);
        int endPoint = Integer.parseInt(inputs[1]);

        int[] distance = new int[pointCount + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startPoint] = 0;
        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));
        queue.add(new Line(startPoint, 0));
        while (!queue.isEmpty()) {
            Line now = queue.remove();
            List<Line> lines = Optional.ofNullable(map.get(now.getEnd())).orElse(new ArrayList<>());
            for (Line line : lines) {
                if (distance[line.getEnd()] > line.getWeight() + distance[now.getEnd()]) {
                    distance[line.getEnd()] = line.getWeight() + distance[now.getEnd()];
                    queue.add(line);
                }
            }
        }
        System.out.println(distance[endPoint]);
    }
}

class Line {

    private final int end;
    private final int weight;

    public Line(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    public Line(String end, String weight) {
        this(Integer.parseInt(end), Integer.parseInt(weight));
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}