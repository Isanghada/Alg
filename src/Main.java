import _202310.*;

public class Main {
    public static void main(String[] args) {
        _09_Solution_1 s = new _09_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution(10, new long[]{1, 3, 4, 1, 3, 1})));
        System.out.println(s.solution(4, new int[][] {{1, 2}, {2, 3}, {3, 4}}));
        System.out.println(s.solution(5, new int[][] {{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
    }
}
