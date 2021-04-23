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

        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= stateSize; i++) {
            String[] inputs = reader.readLine().split(" ");
            int first = Integer.parseInt(inputs[0]);
            int second = Integer.parseInt(inputs[1]);

            int firstParent = find(first);
            int secondParent = find(second);
            if (firstParent == secondParent) {
                System.out.println(i);
                return;
            } else {
                parent[firstParent] = secondParent;
            }
        }
        System.out.println(0);
    }

    public static int find(int index) {
        if (parent[index] == index) {
            return index;
        } else {
            return parent[index] = find(parent[index]);
        }
    }
}
