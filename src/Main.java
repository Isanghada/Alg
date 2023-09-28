import _202309.*;

public class Main {
    public static void main(String[] args) {
        _28_Solution_1 s = new _28_Solution_1();
//        for(int[] answer : s.solution(5, new int[][] {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1}, {4, 2, 1, 1}, {3, 2, 1, 1}}))
//            System.out.print(Arrays.toString(answer)+", ");
//        System.out.println();
//        System.out.println(Arrays.toString(s.solution(new int[]{1, 1, 1, 1, 1, 1, 2, 5, 8, 2, 1, 1, 4, 8, 8, 8, 12, 6, 6}, new int[] {4, 3, 1, 5, 6})));
        System.out.println(s.solution(new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                    {0, 0, 0, 0, 0, 0, 4, 0, 0, 0},
                                                    {0, 0, 0, 0, 0, 4, 4, 0, 0, 0},
                                                    {0, 0, 0, 0, 3, 0, 4, 0, 0, 0},
                                                    {0, 0, 0, 2, 3, 0, 0, 0, 5, 5},
                                                    {1, 2, 2, 2, 3, 3, 0, 0, 0, 5},
                                                    {1, 1, 1, 0, 0, 0, 0, 0, 0, 5}}));
    }
}
