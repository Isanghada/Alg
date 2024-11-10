package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/
// - BFS : 시작 위치부터 차례로 이동하며 최소값 탐색!
public class _10_Solution_1 {
    // 좌표 클래스
    public static class Point implements Comparable<Point>{
        int row;
        int col;
        int count;
        public Point(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
        @Override
        public int compareTo(Point p){
            return this.count - p.count;
        }
    }
    // 이동 변수
    public static int[][] DELTA = new int[][]{{0, -1}, {0, 1}, {-1,0}, {1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_10_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미로 크기
        int N = Integer.parseInt(in.readLine());

        // 미로 배열
        int[][] board = new int[N][N];
        // 방문 배열 : 변경할 방의 최소값!
        int[][] dist = new int[N][N];
        for(int r = 0; r < N; r++) {
            String input = in.readLine();
            for(int c = 0; c < N; c++){
                board[r][c] = input.charAt(c) - '0';
                dist[r][c] = 1_000_000;
            }
        }

        // bfs를 통해 최소값 탐색
        bfs(board, dist, N);


        // 정답 출력
        sb.append(dist[N-1][N-1]);
        System.out.println(sb.toString());
    }

    private static void bfs(int[][] board, int[][] dist, int n) {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        pq.offer(new Point(0, 0, 0));
        visited[0][0] = true;

        while(!pq.isEmpty()){
            Point cur = pq.poll();
            dist[cur.row][cur.col] = cur.count;
            if(cur.row == (n-1) && cur.col == (n-1)) break;
            for(int[] d : DELTA){
                // 다음 좌표 계산
                Point next = new Point(cur.row+d[0], cur.col+d[1], cur.count);

                // 범위를 벗어나거나, 최소값이 아닌 경우 패스
                if(next.row < 0 || next.row >= n ||
                        next.col < 0 || next.col >= n ||
                        visited[next.row][next.col]
                ) continue;

                // 최소값 갱신
                if(board[next.row][next.col] == 0) next.count++;
                visited[next.row][next.col] = true;

                // 좌표 추가
                pq.offer(next);
            }
        }
    }
}
