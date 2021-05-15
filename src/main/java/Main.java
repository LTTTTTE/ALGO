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
        String[] inits = reader.readLine().split(" ");
        int nodeSize = Integer.parseInt(inits[0]);
        int lineSize = Integer.parseInt(inits[1]);
        int start = Integer.parseInt(inits[2]);
        Map<Integer, List<Line>> map = new HashMap<>();
        Map<Integer, List<Line>> reverse = new HashMap<>();
        int[] distance = new int[nodeSize + 1];
        int[] reverseDistance = new int[nodeSize + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(reverseDistance, Integer.MAX_VALUE);

        for (int i = 0; i < lineSize; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!map.containsKey(inputs[0])) {
                map.put(inputs[0], new ArrayList<>());
            }
            map.get(inputs[0]).add(new Line(inputs[1], inputs[2]));

            if (!reverse.containsKey(inputs[1])) {
                reverse.put(inputs[1], new ArrayList<>());
            }
            reverse.get(inputs[1]).add(new Line(inputs[0], inputs[2]));
        }

        d(start, map, distance);
        d(start, reverse, reverseDistance);

        int answer = 0;
        for (int i = 0; i < distance.length; i++) {
            answer = Integer.max(answer, distance[i] + reverseDistance[i]);
        }

        System.out.println(answer);
    }

    private static void d(int start, Map<Integer, List<Line>> map, int[] distance) {
        distance[start] = 0;
        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));
        queue.add(new Line(start, 0));

        while (!queue.isEmpty()) {
            Line now = queue.remove();
            List<Line> lines = Optional.ofNullable(map.get(now.getEnd())).orElse(new ArrayList<>());

            for (Line line : lines) {
                if (distance[line.getEnd()] > line.getWeight() + now.getWeight()) {
                    distance[line.getEnd()] = line.getWeight() + now.getWeight();
                    queue.add(new Line(line.getEnd(), distance[line.getEnd()]));
                }
            }
        }
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