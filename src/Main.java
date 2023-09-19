import _202309.*;

public class Main {
    public static void main(String[] args) {
        _19_Solution_1 s = new _19_Solution_1();
//        System.out.println(Arrays.toString(s.solution()));
        System.out.println(s.solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1, 2, 3, 3, 6, 7}));
        System.out.println(s.solution(7, 10, new int[][] {{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}, {3, 5}, {4, 6}, {5, 6}, {5, 7}, {6, 7}}, 6, new int[] {1, 2, 3, 5, 6, 7}));
    }
}
