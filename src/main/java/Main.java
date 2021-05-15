import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int caseSize = Integer.parseInt(reader.readLine());
        boolean[] answer = new boolean[caseSize];
        for (int c = 0; c < caseSize; c++) {
            String[] inits = reader.readLine().split(" ");
            int nodeSize = Integer.parseInt(inits[0]);
            int lineSize = Integer.parseInt(inits[1]);
            int wormSize = Integer.parseInt(inits[2]);

            int start = 1;
            List<Line> lines = new ArrayList<>();
            long[] distance = new long[nodeSize + 1];
            for (int i = 0; i < lineSize; i++) {
                int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                lines.add(new Line(inputs[0], inputs[1], inputs[2]));
                lines.add(new Line(inputs[1], inputs[0], inputs[2]));
            }
            for (int i = 0; i < wormSize; i++) {
                int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                lines.add(new Line(inputs[0], inputs[1], -inputs[2]));
            }
            Arrays.fill(distance, Long.MAX_VALUE / 2);
            distance[start] = 0;
            answer[c] = bmf(nodeSize, distance, lines);
        }

        for (int i = 0; i < answer.length; i++) {
            if (answer[i]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean bmf(int nodeSize, long[] distance, List<Line> lines) {
        for (int i = 0; i < nodeSize; i++) {
            for (Line line : lines) {
                if (distance[line.getEnd()] > distance[line.getStart()] + line.getWeight()) {
                    if (i == nodeSize - 1) {
                        return true;
                    }
                    distance[line.getEnd()] = distance[line.getStart()] + line.getWeight();
                }
            }
        }
        return false;
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