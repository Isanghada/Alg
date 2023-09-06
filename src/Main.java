import _202309._06_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _06_Solution_1 s = new _06_Solution_1();
//        System.out.println(Arrays.toString(s.solution(21)));
//        System.out.println(Arrays.toString(s.solution(58)));
        System.out.println(s.solution(3, 3, new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}));
        System.out.println(s.solution(3, 6, new int[][] {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
    }
}
