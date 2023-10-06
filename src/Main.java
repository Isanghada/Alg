import _202310.*;

public class Main {
    public static void main(String[] args) {
        _06_Solution_1 s = new _06_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution(10, new long[]{1, 3, 4, 1, 3, 1})));
        System.out.println(s.solution(new String[] {"go", "gone", "guild"}));
        System.out.println(s.solution(new String[] {"abc", "def", "ghi", "jklm"}));
        System.out.println(s.solution(new String[] {"word", "war", "warrior", "world"}));
    }
}
