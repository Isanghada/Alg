import _202309.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _26_Solution_1 s = new _26_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution(new int[]{1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6}, new int[] {4, 3, 1, 5, 6})));
        System.out.println(s.solution(28, 18, 26, 10, 8, new int[] {0, 0, 1, 1, 1, 1, 1}));
        System.out.println(s.solution(-10, -5, 5, 5, 1, new int[] {0, 0, 0, 0, 0, 1, 0}));
        System.out.println(s.solution(11, 8, 10, 10, 1, new int[] {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1}));
        System.out.println(s.solution(11, 8, 10, 10, 100, new int[] {0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1}));
    }
}
