import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());

        Map<String, Node> tree = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String[] inputs = reader.readLine().split(" ");
            for (String input : inputs) {
                if (!input.equals(".")) {
                    tree.putIfAbsent(input, new Node(input));
                }
            }
            if (!inputs[1].equals(".")) {
                tree.get(inputs[0]).setLeft(tree.get(inputs[1]));
            }
            if (!inputs[2].equals(".")) {
                tree.get(inputs[0]).setRight(tree.get(inputs[2]));
            }
        }

        first(tree.get("A"));
        System.out.println();
        mid(tree.get("A"));
        System.out.println();
        last(tree.get("A"));
    }

    public static void first(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData());
        first(node.getLeft());
        first(node.getRight());
    }

    public static void mid(Node node) {
        if (node == null) {
            return;
        }
        mid(node.getLeft());
        System.out.print(node.getData());
        mid(node.getRight());
    }

    public static void last(Node node) {
        if (node == null) {
            return;
        }
        last(node.getLeft());
        last(node.getRight());
        System.out.print(node.getData());
    }
}

class Node {

    private final String data;
    private Node left;
    private Node right;

    public Node(String data) {
        this.data = data;
    }

    public String getData() {
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
}
