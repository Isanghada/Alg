import _202309._01_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _01_Solution_1 s = new _01_Solution_1();
//        System.out.println(Arrays.toString(s.solution(21)));
//        System.out.println(Arrays.toString(s.solution(58)));
        System.out.println(s.solution(11, new int[] {4, 11}, 1));
        System.out.println(s.solution(16, new int[] {9}, 2));
        System.out.println(s.solution(16, new int[] {1, 7}, 5));
    }
}
