import _202305._01_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _01_Solution_1 s = new _01_Solution_1();
        System.out.println(Arrays.toString(s.solution(new String[][] {{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}})));
        System.out.println(Arrays.toString(s.solution(new String[][] {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}})));
        System.out.println(Arrays.toString(s.solution(new String[][] {{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}})));
    }
}
