import _202308._19_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _19_Solution_1 s = new _19_Solution_1();
//        System.out.println(Arrays.toString(s.solution(3, new int[][]{{1, 2}, {2, 3}}, new int[] {2, 3}, 1)));
//        System.out.println(Arrays.toString(s.solution(5, new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5)));
        System.out.println(s.solution(new int[][] {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}}, 1, 3, 7, 8));
        System.out.println(s.solution(new int[][] {{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}}, 9, 7, 6, 1));
        System.out.println(s.solution(new int[][] {{1, 1, 5, 7}}, 1, 1, 4, 7));
        System.out.println(s.solution(new int[][] {{2, 2, 5, 5}, {1, 3, 6, 4}, {3, 1, 4, 6}}, 1, 4, 6, 3));
    }
}
