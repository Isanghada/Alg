import _202308._21_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _21_Solution_1 s = new _21_Solution_1();
//        System.out.println(Arrays.toString(s.solution(3, new int[][]{{1, 2}, {2, 3}}, new int[] {2, 3}, 1)));
//        System.out.println(Arrays.toString(s.solution(5, new int[][] {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}}, new int[]{1, 3, 5}, 5)));
        System.out.println(s.solution(new int[]{0,0,1,1,1,0,1,0,1,0,1,1}, new int[][] {{0,1}, {1,2}, {1,4}, {0,8}, {8,7}, {9,10}, {9,11}, {4,3}, {6,5}, {4,6}, {8,9}}));
        System.out.println(s.solution(new int[]{0,1,0,1,1,0,1,0,0,1,0}, new int[][] {{0,1}, {0,2}, {1,3}, {1,4}, {2,5}, {2,6}, {3,7}, {4,8}, {6,9}, {9,10}}));
    }
}
