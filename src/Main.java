import _202309._16_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _16_Solution_1 s = new _16_Solution_1();
//        System.out.println(Arrays.toString(s.solution()));
        System.out.println(s.solution(2, 2, 0, 0, new int[][] {{2, 1}, {0, 1}, {1, 1}, {0, 1}, {2, 1}}));
        System.out.println(s.solution(2, 5, 0, 1, new int[][] {{3, 1}, {2, 2}, {1, 1}, {2, 3}, {0, 1}, {2, 1}}));
    }
}
