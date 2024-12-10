package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2194
// - BFS : 시작 위치를 기준으로 너비 우선 탐색을 통해 최소 이동 횟수 탐색!
public class _10_Solution_1 {
    // 좌표 클래스
    public static class Point{
        int row;
        int col;
        int count;
        public Point(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }
    public static final int[][] DELTA = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   //
        int M = Integer.parseInt(st.nextToken());   //
        int A = Integer.parseInt(st.nextToken());   //
        int B = Integer.parseInt(st.nextToken());   //
        int K = Integer.parseInt(st.nextToken());   //

        boolean[][] board = new boolean[N+1][M+1];
        while(K-- > 0){
            st = new StringTokenizer(in.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = true;
        }

        st = new StringTokenizer(in.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        st = new StringTokenizer(in.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);

        // 정답 출력
        sb.append(bfs(board, start, end, A, B, N, M));
        System.out.println(sb.toString());
    }

    private static int bfs(boolean[][] board, Point start, Point end, int a, int b, int n, int m) {
        Deque<Point> deque = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][m+1];

        deque.offerLast(start);
        visited[start.row][start.col] = true;

        while(!deque.isEmpty()){
            Point cur = deque.pollFirst();
            if(cur.row == end.row && cur.col == end.col) return cur.count;

            for(int[] d : DELTA){
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.count+1);
                if(check(board, visited, next, a, b, n, m)) continue;

                visited[next.row][next.col] = true;
                deque.offerLast(next);
            }
        }

        // for(boolean[] aaa : board) System.out.println(Arrays.toString(aaa));


        return -1;
    }

    private static boolean check(boolean[][] board, boolean[][] visited, Point p, int a, int b, int n, int m) {
        for(int r = p.row; r < p.row+a; r++){
            for(int c = p.col; c < p.col+b; c++){
                if(r > n || r < 1 ||
                        c > m || c < 1 ||
                        board[r][c] ||
                        (r == p.row && c == p.col && visited[r][c])
                ) return true;
            }
        }

        return false;
    }
}
