import _202310.*;

public class Main {
    public static void main(String[] args) {
        _02_Solution_1 s = new _02_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution(10, new long[]{1, 3, 4, 1, 3, 1})));
        System.out.println(s.solution(3, 2, new int[][] {{1170, 1210}, {1200, 1260}}));
        System.out.println(s.solution(2, 1, new int[][] {{840, 900}}));
        System.out.println(s.solution(2, 2, new int[][] {{600, 630}, {630, 700}}));
        System.out.println(s.solution(4, 5, new int[][] {{1140, 1200}, {1150, 1200}, {1100, 1200}, {1210, 1300}, {1220, 1280}}));
    }
}
