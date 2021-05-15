import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inits = reader.readLine().split(" ");
        int nodeSize = Integer.parseInt(inits[0]);
        int lineSize = Integer.parseInt(inits[1]);
        int start = 1;
        List<Line> lines = new ArrayList<>();
        long[] distance = new long[nodeSize + 1];
        for (int i = 0; i < lineSize; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            lines.add(new Line(inputs[0], inputs[1], inputs[2]));
        }

        Arrays.fill(distance, Long.MAX_VALUE);
        distance[start] = 0;

        for (int i = 0; i < nodeSize; i++) {
            for (Line line : lines) {
                if (distance[line.getStart()] == Long.MAX_VALUE) {
                    continue;
                }
                if (distance[line.getEnd()] > distance[line.getStart()] + line.getWeight()) {
                    if (i == nodeSize - 1) {
                        System.out.println(-1);
                        return;
                    }
                    distance[line.getEnd()] = distance[line.getStart()] + line.getWeight();
                }
            }
        }
        Arrays.stream(distance).skip(2).boxed().map(x -> {
            if (x == Long.MAX_VALUE) {
                return -1;
            }
            return x;
        }).forEach(System.out::println);
    }
}

class Line {

    private final int start;
    private final int end;
    private final int weight;

    public Line(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }
}