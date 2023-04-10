import _202304._10.Solution1;

public class Main {
    public static void main(String[] args) {
        Solution1 s = new Solution1();
        System.out.println(s.solution(new int[][]{{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,1}, {0,0,0,0,1}}));
        System.out.println(s.solution(new int[][]{{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}}));
    }
}