import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        int stateSize = Integer.parseInt(reader.readLine());

        parent = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < size; i++) {
            String[] inputs = reader.readLine().split(" ");
            for (int j = i; j < size; j++) {
                if (inputs[j].equals("1")) {
                    union(i, j);
                }
            }
        }
        String[] plan = reader.readLine().split(" ");
        for (int i = 0; i < plan.length - 1; i++) {
            if (find(Integer.parseInt(plan[i]) - 1) != find(Integer.parseInt(plan[i + 1]) - 1)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static int find(int index) {
        if (parent[index] == index) {
            return index;
        } else {
            return parent[index] = find(parent[index]);
        }
    }

    public static void union(int first, int second) {
        first = find(first);
        second = find(second);
        if (first != second) {
            parent[second] = first;
        }
    }
}
