import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodeSize = Integer.parseInt(reader.readLine());
        int lineSize = Integer.parseInt(reader.readLine());
        Map<Pair, Integer> weights = new HashMap<>();
        int[] beforeSize = new int[nodeSize];
        int[] totalWeight = new int[nodeSize];
        boolean[] visited = new boolean[nodeSize];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, List<Integer>> invertMap = new HashMap<>();
        for (int i = 0; i < lineSize; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = inputs[0] - 1;
            int end = inputs[1] - 1;
            int weight = inputs[2];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }
            map.get(start).add(end);
            weights.put(new Pair(start, end), weight);
            beforeSize[end]++;

            if (!invertMap.containsKey(end)) {
                invertMap.put(end, new ArrayList<>());
            }
            invertMap.get(end).add(start);
        }
        String[] inits = reader.readLine().split(" ");
        int start = Integer.parseInt(inits[0]) - 1;
        int end = Integer.parseInt(inits[1]) - 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int now = queue.remove();
            List<Integer> nextNodes = Optional.ofNullable(map.get(now)).orElse(new ArrayList<>());
            for (int next : nextNodes) {
                totalWeight[next] = Integer.max(totalWeight[next], totalWeight[now] + weights.get(new Pair(now, next)));
                beforeSize[next]--;

                if (beforeSize[next] == 0) {
                    queue.add(next);
                }
            }
        }
        System.out.println(totalWeight[end]);
        int answer = 0;
        queue.add(end);
        while (!queue.isEmpty()) {
            int now = queue.remove();
            List<Integer> nextNodes = Optional.ofNullable(invertMap.get(now)).orElse(new ArrayList<>());
            for (int next : nextNodes) {
                int nowWeight = totalWeight[now];
                int beforeTotalWeight = totalWeight[next];
                int roadWeight = weights.get(new Pair(next, now));
                if (beforeTotalWeight + roadWeight == nowWeight) {
                    answer++;
                    if (visited[next] && next != start) {
                        continue;
                    }
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        System.out.println(answer);
    }
}

class Pair {

    private final int start;
    private final int end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return start == pair.start && end == pair.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}