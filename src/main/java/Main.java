import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> time = new HashMap<>();
        Map<String, Integer> beforeSize = new HashMap<>();
        Map<String, Integer> total = new HashMap<>();
        Set<String> noLeaf = new HashSet<>();
        do {
            String[] inputs = input.split(" ");
            time.put(inputs[0], Integer.parseInt(inputs[1]));
            if (inputs.length > 2) {
                String[] beforeInputs = inputs[2].split("");
                beforeSize.put(inputs[0], beforeInputs.length);
                for (String before : beforeInputs) {
                    noLeaf.add(before);
                    if (!map.containsKey(before)) {
                        map.put(before, new ArrayList<>());
                    }
                    map.get(before).add(inputs[0]);
                }
            } else {
                beforeSize.put(inputs[0], 0);
            }
        } while ((input = reader.readLine()) != null);
        Set<String> leaf = new HashSet<>(time.keySet());
        leaf.removeAll(noLeaf);

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(time::get));
        for (String each : beforeSize.keySet()) {
            if (beforeSize.get(each).equals(0)) {
                queue.add(each);
            }
            total.put(each, time.get(each));
        }

        while (!queue.isEmpty()) {
            String now = queue.remove();
            List<String> nextNodes = Optional.ofNullable(map.get(now)).orElse(new ArrayList<>());
            for (String next : nextNodes) {
                int cost = Integer.max(total.get(next), total.get(now) + time.get(next));
                total.put(next, cost);
                beforeSize.put(next, beforeSize.get(next) - 1);

                if (beforeSize.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for (String each : leaf) {
            answer = Integer.max(total.get(each), answer);
        }
        System.out.println(answer);
    }
}