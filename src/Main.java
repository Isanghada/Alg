import _202308._22_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _22_Solution_1 s = new _22_Solution_1();
//        System.out.println(Arrays.toString(s.solution(3, new int[][]{{1, 2}, {2, 3}}, new int[] {2, 3}, 1)));
//        System.out.println(Arrays.toString(s.solution(5, new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5)));
        System.out.println(s.solution(new int[] {14, 17, 15, 18, 19, 14, 13, 16, 28, 17}, new int[][] {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}}));
        System.out.println(s.solution(new int[] {5, 6, 5, 3, 4}, new int[][] {{2, 3}, {1, 4}, {2, 5}, {1, 2}}));
        System.out.println(s.solution(new int[] {5, 6, 5, 1, 4}, new int[][] {{2, 3}, {1, 4}, {2, 5}, {1, 2}}));
        System.out.println(s.solution(new int[] {10, 10, 1, 1}, new int[][] {{3, 2}, {4, 3}, {1, 4}}));
    }
}
