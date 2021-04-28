import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static int[] column;
    static int[] indexs;
    static int[] row;
    static int index = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        column = new int[size];
        indexs = new int[size + 1];
        row = new int[size];

        Map<Integer, Node> tree = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int input : inputs) {
                if (input != -1) {
                    tree.putIfAbsent(input, new Node(input));
                }
            }
            if (inputs[1] != -1) {
                tree.get(inputs[0]).setLeft(tree.get(inputs[1]));
                tree.get(inputs[1]).setParent(tree.get(inputs[0]));
            }
            if (inputs[2] != -1) {
                tree.get(inputs[0]).setRight(tree.get(inputs[2]));
                tree.get(inputs[2]).setParent(tree.get(inputs[0]));
            }
        }
        Node root = findRoot(tree.get(tree.keySet().stream().findFirst().get()));
        mid(root, 1);

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < row.length; i++) {
            if (!map.containsKey(row[i])) {
                map.put(row[i], new ArrayList<>());
            }
            map.get(row[i]).add(indexs[column[i]]);
        }

        int[] maxes = new int[map.size() + 1];
        int answer = 0;
        int answerLevel = 0;
        for (int i = 1; i < maxes.length; i++) {
            int width;
            if (map.get(i).size() < 2) {
                width = 1;
            } else {
                int min = map.get(i).stream().mapToInt(x -> x).min().orElse(map.get(i).get(0));
                int max = map.get(i).stream().mapToInt(x -> x).max().orElse(map.get(i).get(0));
                width = max - min + 1;
            }
            if (answer < width) {
                answer = width;
                answerLevel = i;
            }
        }
        System.out.println(answerLevel + " " + answer);
    }

    private static Node findRoot(Node node) {
        if (node.getParent() == null) {
            return node;
        }
        return findRoot(node.getParent());
    }

    public static void mid(Node node, int level) {
        if (node == null) {
            return;
        }
        mid(node.getLeft(), level + 1);
        column[index++] = node.getData();
        indexs[node.getData()] = index;
        row[index - 1] = level;
        mid(node.getRight(), level + 1);
    }
}

class Node {

    private final int data;
    private Node left;
    private Node right;
    private Node parent;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
