import _202309.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _20_Solution_1 s = new _20_Solution_1();
        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
            System.out.print(Arrays.toString(answer)+", ");
        System.out.println();
        for(int[] answer : s.solution(5, new int[][] {{0, 0, 0, 1}, {2, 0, 0, 1}, {4, 0, 0, 1}, {0, 1, 1, 1}, {1, 1, 1, 1}, {2, 1, 1, 1}, {3, 1, 1, 1}, {2, 0, 0, 0}, {1, 1, 1, 0}, {2, 2, 0, 1}}))
            System.out.print(Arrays.toString(answer)+", ");
        System.out.println();
//        System.out.println(Arrays.toString(s.solution()));
//        System.out.println(s.solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1, 2, 3, 3, 6, 7}));
    }
}
