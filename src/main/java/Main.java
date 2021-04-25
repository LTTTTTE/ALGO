import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Node head = null;
        String input;
        while ((input = reader.readLine()) != null) {
            Node node = new Node(Integer.parseInt(input));
            if (head == null) {
                head = node;
            } else {
                head.add(node);
            }
        }
        dfs(head);
    }

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        dfs(node.getLeft());
        dfs(node.getRight());
        System.out.println(node.getData());
    }
}

class Node {

    private final int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data = data;
    }

    public void add(Node node) {
        if (this.data > node.getData()) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }
        if (this.data < node.getData()) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
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
}
