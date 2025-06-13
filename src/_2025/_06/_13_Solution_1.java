package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30702
// - BFS
public class _13_Solution_1 {
    static class Point{
        int row;
        int col;
        public Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int[][] A = inputBoard(N, M, in);
        int[][] B = inputBoard(N, M, in);

//        for(int[] a : A) System.out.println(Arrays.toString(a));
//        System.out.println("-----");
//        for(int[] b : B) System.out.println(Arrays.toString(b));

        boolean[][] checked = new boolean[N][M];
        boolean answer = true;
        for(int r = 0; r < N && answer; r++){
            for(int c = 0; c < M && answer; c++){
                if(checked[r][c]) continue;
                if(!bfs(A, B, checked, r, c, N, M)) answer = false;
            }
        }


        // 정답 출력
        sb.append(answer ? "YES" : "NO");
        System.out.println(sb.toString());
    }

    private static boolean bfs(int[][] a, int[][] b, boolean[][] checked, int startR, int startC, int n, int m) {
        Deque<Point> deque = new LinkedList<>();

        int typeA = a[startR][startC];
        int typeB = b[startR][startC];

        deque.offerLast(new Point(startR, startC));
        checked[startR][startC] = true;
        while(!deque.isEmpty()){
            Point cur = deque.pollFirst();
            // System.out.println(cur.row+", "+cur.col+", "+a[cur.row][cur.col] +", "+b[cur.row][cur.col]);
            if(b[cur.row][cur.col] != typeB) return false;

            for(int[] d : DELTA){
                Point next = new Point(cur.row + d[0], cur.col + d[1]);

                if(next.row < 0 || next.row >= n ||
                        next.col < 0 || next.col >= m ||
                        checked[next.row][next.col] ||
                        a[next.row][next.col] != typeA
                ) continue;

                deque.offerLast(next);
                checked[next.row][next.col] = true;
            }
        }

        return true;
    }

    private static int[][] inputBoard(int n, int m, BufferedReader in) throws Exception{
        int[][] result = new int[n][m];

        for(int r = 0; r < n; r++){
            String input = in.readLine();
            for(int c = 0; c < m; c++) result[r][c] = input.charAt(c) - 'A';
        }

        return result;
    }
}
