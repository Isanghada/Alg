package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21610
// - 구현 : 동작 과정을 차례로 구축
//          1. 구름 위치 계산
//          2. 구름 위치 이동
//          3. 구름칸 물의 양 증가
//          4. 물복사 버그 계산
//          5. 새로운 구름 생성
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 격자 크기
        int M = Integer.parseInt(st.nextToken());   // 구름 이동 횟수

        // 격자 입력
        int[][] board = getBoard(N, in);

//        for(int[] b : board) System.out.println(Arrays.toString(b));
        // 비바라기 시전
        magic(board, N, getMoveInfo(in, M));
//        System.out.println("------------");
//        for(int[] b : board) System.out.println(Arrays.toString(b));

        // 정답 출력
        // - 물의 양 계산
        sb.append(getSum(N, board));
        System.out.println(sb);
    }
    // 물의 양 게산 함수 : 격자의 모든 물의 양 합산
    private static int getSum(int n, int[][] board) {
        int sum = 0;
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++) sum += board[r][c];
        }
        return sum;
    }

    // 비바라기 함수
    private static void magic(int[][] board, int n, Move[] moves) throws Exception{
        // 물복사 버그에 활용한 변수
        int[][] delta = new int[][]{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        // 초기 구름 생성
        Set<Point> cloudSet = getCloud(n);
        // - 구름 이동 반복!
        for(Move m : moves){
            // - 동작 후 구름이 생기지 않아야할 위치
            Set<Point> remove = new HashSet<>();
            // 구름 위치 물의 양 증가
            for(Point cloud : cloudSet){
//                System.out.println("==============");
//                System.out.println(cloud.hashCode());
                // 구름 이동
                cloud.move(m, n);
                // 구름 재생성 X 좌표 추가
                remove.add(cloud);
//                System.out.println(cloud.hashCode());
//                System.out.println("==============");
//                System.out.println("cloud : "+cloud.row+", "+cloud.col+
//                        " => nextCloud : "+nextCloud.row+", "+nextCloud.col+
//                        " | move : "+m.type+" ("+Move.dir[m.type][0]+", "+Move.dir[m.type][1]+"), "+m.count);
                // 물의 양 증가
                board[cloud.row][cloud.col]++;
            }
            // 물복사 버그
            for(Point cloud : cloudSet){
                // 물의 증가량
                int count = 0;
                // 대각 위치 확인
                for(int[] d : delta){
                    Point target = new Point(cloud, d);
                    // 범위를 벗어날 경우 패스
                    if(target.row < 1 || target.row > n || target.col < 1 || target.col > n)
                        continue;
                    // 물이 있는 경우 증가
                    if(board[target.row][target.col] > 0) count++;
                }
                // 물의 양만큼 증가
                board[cloud.row][cloud.col] += count;
            }
            // 새로운 구름 생성
            cloudSet = getCloud(n, board, remove);
//            System.out.println("------------");
//            for(int[] b : board) System.out.println(Arrays.toString(b));
//            for(Point c : cloudSet) System.out.print("("+c.row+", "+c.col+"), ");
//            System.out.println(cloudSet.size());
        }
    }
    // 구름 생성 함수 : remove를 제외한 위치에 2이상의 물이 있는 경우 구름 생성
    private static Set<Point> getCloud(int n, int[][] board, Set<Point> remove) {
        Set<Point> set = new HashSet<>();
//        for(Point c : remove) {
//            System.out.print("("+c.row+", "+c.col+", "+c.hashCode()+", "+remove.contains(c)+"), ");
//        }
//        System.out.println();
        for(int r = 1; r <= n; r++){
            for(int c = 1; c <= n; c++){
                Point cur = new Point(r, c);
                if(remove.contains(cur)) continue;
                if(board[r][c] >= 2){
                    set.add(cur);
                    board[r][c] -= 2;
                }
            }
        }
        return set;
    }
    // 구름 생성 함수 : 초기 구름 생성!
    private static Set<Point> getCloud(int n) {
        Set<Point> set = new HashSet<>();
        int[][] delta = new int[][]{{0, 1}, {0, 2}, {-1, 1}, {-1, 2}};
        for(int[] d : delta) set.add(new Point(n+d[0], d[1]));
        return set;
    }
    // 구름 이동 정보 입력 함수
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
    // 격자 입력 함수
    private static int[][] getBoard(int n, BufferedReader in) throws Exception{
        int[][] board = new int[n+1][n+1];
        for(int r=1; r<=n; r++){
            StringTokenizer st = new StringTokenizer(in.readLine());
            for(int c=1; c<=n; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }
        return board;
    }
    // 좌표 클래스
    private static class Point{
        int row;
        int col;
        // 구름 좌표 생성자
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
        // 구름 대각 확인 생성자
        public Point(Point cloud, int[] d) {
            this.row = cloud.row + d[0];
            this.col = cloud.col + d[1];
        }
        // 구름 이동 생성자
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
        // contains활용을 위해 equals, hashCode 재정의
        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Point){
                Point p = (Point) obj;
                if((this.row == p.row) && (this.col == p.col)) return true;
            }
            return super.equals(obj);
        }
    }
    // 이동 클래스
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
