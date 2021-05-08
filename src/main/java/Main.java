import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] init = reader.readLine().split(" ");
        int pointCount = Integer.parseInt(init[0]);
        int lineCount = Integer.parseInt(init[1]);
        int[] parents = new int[pointCount + 1];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        PriorityQueue<Line> queue = new PriorityQueue<>(Comparator.comparingInt(Line::getWeight));
        for (int i = 0; i < lineCount; i++) {
            String[] inputs = reader.readLine().split(" ");
            queue.add(new Line(inputs[0], inputs[1], inputs[2]));
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            Line now = queue.remove();
            int startRoot = find(parents, now.getStart());
            int endRoot = find(parents, now.getEnd());
            if (startRoot != endRoot) {
                union(parents, now.getStart(), now.getEnd());
                answer += now.getWeight();
            }
        }
        System.out.println(answer);
    }

    public static int find(int[] parent, int child) {
        if (parent[child] == child) {
            return child;
        }
        parent[child] = find(parent, parent[child]);
        return parent[child];
    }

    public static void union(int[] parent, int first, int second) {
        int firstRoot = find(parent, first);
        int secondRoot = find(parent, second);
        if (firstRoot != secondRoot) {
            parent[secondRoot] = firstRoot;
        }
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

    public Line(String start, String end, String weight) {
        this(Integer.parseInt(start), Integer.parseInt(end), Integer.parseInt(weight));
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