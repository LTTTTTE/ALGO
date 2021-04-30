import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution("87654321", 3));
    }

    public static String solution(String number, int deleteCount) {
        if (number.length() == 1) {
            return number;
        }
        StringBuilder answer = new StringBuilder();
        int safeCount = number.length() - deleteCount;
        int[] numbers = Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
        int maxIndex = 0;
        while (safeCount != 0) {
            int max = 0;
            for (int i = maxIndex; i < numbers.length - safeCount + 1; i++) {
                if (numbers[i] > max) {
                    max = numbers[i];
                    maxIndex = i + 1;
                }
            }
            answer.append(max);
            safeCount--;
        }
        return answer.toString();
    }
}