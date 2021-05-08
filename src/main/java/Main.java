public class Main {

    public static void main(String[] args) {
        System.out.println(solution("2three45sixseven"));
    }

    public static int solution(String input) {
        String[] map = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < map.length; i++) {
            input = input.replaceAll(map[i], String.valueOf(i));
        }
        return Integer.parseInt(input);
    }
}