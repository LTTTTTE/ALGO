import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution("ABABAAAAAAABA"));
    }

    public static int solution(String name) {
        int answer = 0;
        int[] names = new int[name.length()];

        int index = 0;
        for (byte i : name.getBytes(StandardCharsets.US_ASCII)) {
            int number = i - 65;
            if (number > 13) {
                number = 26 - number;
            }
            names[index++] = number;
        }

        int now = 0;
        while (isNotEnd(names)) {
            answer += names[now];
            names[now] = 0;
            int nextIndex = minDistanceIndex(now, names);
            if (nextIndex == -1) {
                break;
            }
            int newDistance = Math.min(Math.abs(nextIndex - now), names.length - nextIndex + now);
            answer+= newDistance;
            now = nextIndex;
        }
        return answer;
    }

    public static boolean isNotEnd(int[] names) {
        return Arrays.stream(names).anyMatch(x -> x != 0);
    }

    public static int minDistanceIndex(int now, int[] names) {
        int distance = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < names.length; i++) {
            if (names[i] != 0) {
                int newDistance = Math.min(Math.abs(i - now), names.length - i + now);
                if (distance > newDistance) {
                    distance = newDistance;
                    index = i;
                }
            }
        }
        return index;
    }
}