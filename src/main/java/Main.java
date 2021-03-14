import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = reader.readLine().split(" ");
        int species = Integer.parseInt(inputs[0]);
        int charge = Integer.parseInt(inputs[1]);
        int[] coins = new int[species];
        for (int i = 0; i < species; i++) {
            coins[i] = Integer.parseInt(reader.readLine());
        }
        int count = 0;
        while (charge != 0) {
            for (int i = species - 1; i >= 0; i--) {
                while(coins[i] <= charge) {
                    charge -= coins[i];
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
