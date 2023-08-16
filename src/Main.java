import _202308._16_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _16_Solution_1 s = new _16_Solution_1();
//        System.out.println(Arrays.toString(s.solution(3, new int[][]{{1, 2}, {2, 3}}, new int[] {2, 3}, 1)));
//        System.out.println(Arrays.toString(s.solution(5, new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5)));
        System.out.println(s.solution(new int[][] {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}}, new int[][] {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}}));
        System.out.println(s.solution(new int[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][] {{1, 0, 1}, {0, 0, 0}, {0, 0, 0}}));
    }
}
