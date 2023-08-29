import _202308._29_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _29_Solution_1 s = new _29_Solution_1();
//        System.out.println(Arrays.toString(s.solution(21)));
//        System.out.println(Arrays.toString(s.solution(58)));
        System.out.println(s.solution(3, 5, new int[][] {{10, 60, 1}, {15, 100, 3}, {20, 30, 1}, {30, 50, 3}, {50, 40, 1}, {60, 30, 2}, {65, 30, 1}, {70, 100, 2}}));
        System.out.println(s.solution(2, 3, new int[][] {{5, 55, 2}, {10, 90, 2}, {20, 40, 2}, {50, 45, 2}, {100, 50, 2}}));
    }
}
