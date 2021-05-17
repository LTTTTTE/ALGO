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
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inits = reader.readLine().split(" ");
        int nodeSize = Integer.parseInt(inits[0]);
        int lineSize = Integer.parseInt(inits[1]);
        int[] beforeSize = new int[nodeSize];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < lineSize; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).map(x -> x - 1)
                .toArray();
            if (!map.containsKey(inputs[0])) {
                map.put(inputs[0], new ArrayList<>());
            }
            map.get(inputs[0]).add(inputs[1]);
            beforeSize[inputs[1]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < beforeSize.length; i++) {
            if (beforeSize[i] == 0) {
                queue.add(i);
                answer.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int now = queue.remove();
            List<Integer> nextNodes = Optional.ofNullable(map.get(now)).orElse(new ArrayList<>());

            for (int next : nextNodes) {
                beforeSize[next]--;
                if (beforeSize[next] == 0) {
                    queue.add(next);
                    answer.add(next);
                }
            }
        }
        System.out.println(answer.stream().map(x -> x + 1).map(String::valueOf).collect(Collectors.joining(" ")));
    }
}