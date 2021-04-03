import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int pointCount = Integer.parseInt(reader.readLine());
        int lineCount = Integer.parseInt(reader.readLine());

        Map<Integer, List<Line>> map = new HashMap<>();
        int[] distance = new int[pointCount + 1];
        int[] path = new int[pointCount + 1];
        for (int i = 1; i <= pointCount; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < lineCount; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (!map.containsKey(inputs[0])) {
                map.put(inputs[0], new ArrayList<>());
            }
            map.get(inputs[0]).add(new Line(inputs[1], inputs[2]));
        }

        String[] locationInput = reader.readLine().split(" ");
        int startPoint = Integer.parseInt(locationInput[0]);
        int endPoint = Integer.parseInt(locationInput[1]);

        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));
        queue.add(new Line(startPoint, 0));

        while (!queue.isEmpty()) {
            Line now = queue.remove();

            if (distance[now.getEnd()] < now.getWeight()) {
                continue;
            }

            List<Line> nextPoints = Optional.ofNullable(map.get(now.getEnd())).orElse(new ArrayList<>());

            nextPoints.forEach(next -> {
                if (distance[next.getEnd()] > now.getWeight() + next.getWeight()) {
                    distance[next.getEnd()] = now.getWeight() + next.getWeight();
                    queue.add(new Line(next.getEnd(), distance[next.getEnd()]));
                    path[next.getEnd()] = now.getEnd();
                }
            });
        }
        distance[1] = 0;
        int minDistance = distance[endPoint];
        List<Integer> answer = new ArrayList<>();
        int point = endPoint;
        while (point != startPoint) {
            answer.add(point);
            point = path[point];
        }
        answer.add(startPoint);
        Collections.reverse(answer);
        System.out.println(minDistance);
        System.out.println(answer.size());
        System.out.println(answer.stream().map(String::valueOf).collect(Collectors.joining(" ")));
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
