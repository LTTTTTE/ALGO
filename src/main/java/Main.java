import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] init = reader.readLine().split(" ");
        int size = Integer.parseInt(init[0]);
        int stateSize = Integer.parseInt(init[1]);

        parent = new int[size + 1];
        for (int i = 0; i <= size; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < stateSize; i++) {
            String[] inputs = reader.readLine().split(" ");
            if (inputs[0].equals("0")) {
                union(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
            } else {
                if (find(Integer.parseInt(inputs[1])) == find(Integer.parseInt(inputs[2]))) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
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
