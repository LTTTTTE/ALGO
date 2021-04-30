import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{-20, 15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }

    public static int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, Comparator.comparingInt(x -> x[0]));
        int cover = Integer.MIN_VALUE;
        for (int i = 0; i < routes.length; i++) {
            int start = routes[i][0];
            int end = routes[i][1];
            if (start > cover || end < cover) {
                if(start > cover) {
                    answer++;
                }
                cover = end;
            }
        }
        return answer;
    }
}