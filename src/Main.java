import _202309.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _21_Solution_1 s = new _21_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution()));
        System.out.println(s.solution(new int[][] {{1, 2}, {2, 3}}, 3, 2));
        System.out.println(s.solution(new int[][] {{4, 4, 3}, {3, 2, 2}, {2, 1, 0}}, 5, 3));
    }
}
