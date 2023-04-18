import _202304._18_Solution_1;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        _18_Solution_1 s = new _18_Solution_1();
        System.out.println(Arrays.toString(s.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[] {7000, 9000})));
        System.out.println(Arrays.toString(s.solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[] {1300, 1500, 1600, 4900})));
    }
}
