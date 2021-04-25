import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        int[] parents = new int[size + 1];
        boolean[] visited = new boolean[size + 1];

        Map<Integer, List<Integer>> tree = new HashMap<>();

        for (int i = 1; i < size; i++) {
            String[] inputs = reader.readLine().split(" ");
            int firstInput = Integer.parseInt(inputs[0]);
            int secondInput = Integer.parseInt(inputs[1]);

            if (!tree.containsKey(firstInput)) {
                tree.put(firstInput, new ArrayList<>());
            }
            tree.get(firstInput).add(secondInput);

            if (!tree.containsKey(secondInput)) {
                tree.put(secondInput, new ArrayList<>());
            }
            tree.get(secondInput).add(firstInput);
        }
        int start = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int nowParent = queue.remove();
            tree.get(nowParent).forEach(child -> {
                if (!visited[child]) {
                    parents[child] = nowParent;
                    visited[child] = true;
                    queue.add(child);
                }
            });
        }

        for (int i = 2; i < parents.length; i++) {
            System.out.println(parents[i]);
        }
    }
}
