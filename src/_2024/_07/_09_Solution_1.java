package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18291
// - 구현 : 왜 안되는거지..equals도 맞고 hashcode도 맞고...
//          다시 봐야겠다..
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = getBoard(N, in);

//        for(int[] b : board) System.out.println(Arrays.toString(b));
        magic(board, N, getMoveInfo(in, M));
//        System.out.println("------------");
//        for(int[] b : board) System.out.println(Arrays.toString(b));

        // 정답 출력
        sb.append(getSum(N, board));
        System.out.println(sb);
    }
    private static int getSum(int n, int[][] board) {
        int sum = 0;
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++) sum += board[r][c];
        }
        return sum;
    }

    private static void magic(int[][] board, int n, Move[] moves) throws Exception{
        int[][] delta = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        Set<Point> cloudSet = getCloud(n);
        for(Move m : moves){
            for(Point cloud : cloudSet){
//                System.out.println("==============");
//                System.out.println(cloud.hashCode());
                cloud.move(m, n);
//                System.out.println(cloud.hashCode());
//                System.out.println("==============");
//                System.out.println("cloud : "+cloud.row+", "+cloud.col+
//                        " => nextCloud : "+nextCloud.row+", "+nextCloud.col+
//                        " | move : "+m.type+" ("+Move.dir[m.type][0]+", "+Move.dir[m.type][1]+"), "+m.count);
                int count = 1;
                for(int[] d : delta){
                    Point target = new Point(cloud, d);
                    if(target.row < 1 || target.row > n || target.col < 1 || target.col > n)
                        continue;
                    if(board[target.row][target.col] > 0) count++;
                }
                board[cloud.row][cloud.col] += count;
            }
            System.out.println("--------------");

            for(Point c : cloudSet) System.out.print("("+c.row+", "+c.col+"), ");
            System.out.println();
            cloudSet = getCloud(n, board, cloudSet);
            for(Point c : cloudSet) System.out.print("("+c.row+", "+c.col+"), ");
            System.out.println();
//            for(int[] b : board) System.out.println(Arrays.toString(b));
        }
    }

    private static Set<Point> getCloud(int n, int[][] board, Set<Point> cloudSet) {
        Set<Point> set = new HashSet<>();
        for(Point c : cloudSet) {
            System.out.print("("+c.row+", "+c.col+", "+c.hashCode()+"), ");
        }
        System.out.println();
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++){
                Point cur = new Point(r, c);
                if(cloudSet.contains(cur)) continue;
                if(board[r][c] >= 2){
                    set.add(cur);
                    board[r][c] -= 2;
                }
            }
        }
        return set;
    }

    private static Set<Point> getCloud(int n) {
        Set<Point> set = new HashSet<>();
        int[][] delta = new int[][]{{0, 1}, {0, 2}, {-1, 1}, {-1, 2}};
        for(int[] d : delta) set.add(new Point(n+d[0], d[1]));
        return set;
    }
    private static Move[] getMoveInfo(BufferedReader in, int m) throws Exception {
        StringTokenizer st = null;
        Move[] moves = new Move[m];
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(in.readLine());
            moves[i] = new Move(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }
//        for(Move move : moves) System.out.println("[type="+move.type+" ("+Move.dir[move.type][0]+", "+Move.dir[move.type][1]+"), count="+move.count);
        return moves;
    }
    private static int[][] getBoard(int n, BufferedReader in) throws Exception{
        int[][] board = new int[n+1][n+1];
        for(int r=1; r<=n; r++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int c=1; c<=n; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }
        return board;
    }

    private static class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
        public Point(Point cloud, int[] d) {
            this.row = cloud.row + d[0];
            this.col = cloud.col + d[1];
        }
        public void move(Move m, int n) {
            this.row = (this.row + Move.dir[m.type][0] * m.count);
            while(this.row < 0) this.row += n;
            this.row %= n;
            if(this.row == 0) this.row+=n;
            this.col = (this.col + Move.dir[m.type][1] * m.count);
            while(this.col < 0) this.col += n;
            this.col %= n;
            if(this.col == 0) this.col+=n;
        }
        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point p = (Point)obj;
                if(this.row == p.row && this.col == p.col) return true;
            }
            return false;
        }
    }

    private static class Move {
        static int[][] dir = new int[][]{{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        int type;
        int count;
        public Move(int type, int count){
            this.type =type;
            this.count =count;
        }
    }
}
