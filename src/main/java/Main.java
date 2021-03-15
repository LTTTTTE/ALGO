import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int shopCount = Integer.parseInt(inputs[0]);
        int bagCount = Integer.parseInt(inputs[1]);

        List<Jewelry> jewelries = new ArrayList<>();
        Queue<Integer> bags = new PriorityQueue<>(Integer::compare);

        for (int i = 0; i < shopCount; i++) {
            jewelries.add(new Jewelry(reader.readLine()));
        }
        for (int i = 0; i < bagCount; i++) {
            bags.add(Integer.parseInt(reader.readLine()));
        }

        jewelries.sort(Comparator.comparingInt(Jewelry::getWeight));

        Queue<Jewelry> answer = new PriorityQueue<>(Comparator.comparingInt(Jewelry::getPrice).reversed());
        List<Jewelry> total = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < bagCount; i++) {
            int bag = bags.remove();
            for (; j < shopCount; j++) {
                Jewelry jewelry = jewelries.get(j);
                if (jewelry.getWeight() <= bag) {
                    answer.add(jewelries.get(j));
                } else {
                    break;
                }
            }

            if (!answer.isEmpty()) {
                total.add(answer.remove());
            }
        }
        System.out.println(total.stream().mapToLong(Jewelry::getPrice).sum());
    }
}

class Jewelry {

    private int weight;
    private int price;

    public Jewelry(String input) {
        String[] s = input.split(" ");
        this.weight = Integer.parseInt(s[0]);
        this.price = Integer.parseInt(s[1]);
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
