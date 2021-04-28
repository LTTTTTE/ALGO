import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        Map<Integer, List<Line>> map = new HashMap<>();
        int[] distance = new int[size + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 1; i < size; i++) {
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

        Queue<Line> queue = new LinkedList<>();
        queue.add(new Line(1, 0));
        distance[1] = 0;

        while (!queue.isEmpty()) {
            Line now = queue.remove();
            if (distance[now.getEnd()] < now.getWeight()) {
                continue;
            }
            Optional.ofNullable(map.get(now.getEnd())).orElse(new ArrayList<>())
                .forEach(x -> {
                    if (x.getWeight() + now.getWeight() < distance[x.getEnd()]) {
                        distance[x.getEnd()] = x.getWeight() + now.getWeight();
                        queue.add(new Line(x.getEnd(), distance[x.getEnd()]));
                    }
                });
        }
        distance[0] = 0;
        int max = Arrays.stream(distance).max().orElse(0);
        List<Integer> farIndex = new ArrayList<>();
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == max) {
                farIndex.add(i);
            }
        }
        if (farIndex.size() > 1) {
            System.out.println(max * 2);
            return;
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        queue.add(new Line(farIndex.get(0), 0));
        distance[farIndex.get(0)] = 0;
        while (!queue.isEmpty()) {
            Line now = queue.remove();
            if (distance[now.getEnd()] < now.getWeight()) {
                continue;
            }
            Optional.ofNullable(map.get(now.getEnd())).orElse(new ArrayList<>())
                .forEach(x -> {
                    if (x.getWeight() + now.getWeight() < distance[x.getEnd()]) {
                        distance[x.getEnd()] = x.getWeight() + now.getWeight();
                        queue.add(new Line(x.getEnd(), distance[x.getEnd()]));
                    }
                });
        }
        distance[0] = 0;
        System.out.println(Arrays.stream(distance).max().orElse(0));
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
