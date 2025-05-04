package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/30024
// -
public class _04_Solution_1 {
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int value;
        public Point(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
        @Override
        public int compareTo(Point o) {
            return o.value - this.value;
        }
    }
    static final int[][] DELTA = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N+1][M+1];
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            for(int m = 1; m <= M; m++) board[n][m] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][M+1];
        addBoundary(pq, visited, board, N, M);

        int K = Integer.parseInt(in.readLine());
        while(K-- > 0){
            if(!pq.isEmpty()){
                Point cur = pq.poll();

                sb.append(cur.x).append(" ").append(cur.y).append("\n");

                for(int[] d : DELTA){
                    int nextX = cur.x + d[0];
                    int nextY = cur.y + d[1];

                    if(nextX > N || nextX < 1 ||
                            nextY > M || nextY < 1 ||
                            visited[nextX][nextY]) continue;

                    visited[nextX][nextY] = true;
                    pq.offer(new Point(nextX, nextY, board[nextX][nextY]));
                }
            }
        }


        // 정답 반환
        System.out.println(sb.toString().trim());
    }

    private static void addBoundary(PriorityQueue<Point> pq, boolean[][] visited, int[][] board, int n, int m) {
        // 세로 경계 추가
        for(int x = 1; x <= n; x++){
            if(!visited[x][1]){
                visited[x][1] = true;
                pq.offer(new Point(x, 1, board[x][1]));
            }

            if(!visited[x][m]){
                visited[x][m] = true;
                pq.offer(new Point(x, m, board[x][m]));
            }
        }

        // 가로 경계 추가
        for(int y = 1 ; y <= m ; y++) {
            if(!visited[1][y]) {
                visited[1][y] = true;
                pq.add(new Point(1, y, board[1][y]));
            }

            if(!visited[n][y]) {
                visited[n][y] = true;
                pq.add(new Point(n, y, board[n][y]));
            }
        }
    }
}
