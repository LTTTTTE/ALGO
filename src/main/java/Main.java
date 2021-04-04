import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        solution(6);
    }

    public static int[] solution(int size) {
        int[][] snail = new int[size][];
        int number = 0;
        int topBound = 0;
        int leftBound = 0;
        int bottomBound = size - 1;
        int rightBound = 0;
        for (int i = 0; i < size; i++) {
            snail[i] = new int[i + 1];
        }

        while (containZero(snail)) {



            for (int i = topBound; i <= bottomBound; i++) {
                snail[i][leftBound] = ++number;
            }
            leftBound++;
            topBound++;



            for (int i = leftBound; i < snail[bottomBound].length - rightBound; i++) {
                snail[bottomBound][i] = ++number;
            }
            bottomBound--;



            for (int i = bottomBound; i >= topBound; i--) {
                snail[i][snail[i].length - rightBound - 1] = ++number;
            }
            rightBound++;
            topBound++;


        }
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail[i].length; j++) {
                answer.add(snail[i][j]);
            }
        }
        return answer.stream().mapToInt(x -> x).toArray();
    }

    public static boolean containZero(int[][] snail) {
        for (int i = 0; i < snail.length; i++) {
            for (int j = 0; j < snail[i].length; j++) {
                if (snail[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
