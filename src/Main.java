import _202308._31_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _31_Solution_1 s = new _31_Solution_1();
//        System.out.println(Arrays.toString(s.solution(21)));
//        System.out.println(Arrays.toString(s.solution(58)));
        System.out.println(s.solution(8, new int[][] {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {5, 6}, {5, 7}, {5, 8}}));
        System.out.println(s.solution(10, new int[][] {{4, 1}, {5, 1}, {5, 6}, {7, 6}, {1, 2}, {1, 3}, {6, 8}, {2, 9}, {9, 10}}));
    }
}
