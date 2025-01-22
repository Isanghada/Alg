package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22116
// - 다익스트라 : 시작 위치부터 가능한 모든 위치 탐색!
public class _22_Solution_1 {
    // 노드 클래스
    static class Node implements Comparable<Node>{
        int row;    // 행 좌표
        int col;    // 열 좌표
        int height; // 높이
        int slope;  // 최소 경사
        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
        public Node(int row, int col, int height, int slope){
            this(row, col);
            this.height = height;
            this.slope = slope;
        }
        @Override
        public int compareTo(Node n){
            return this.slope - n.slope;
        }
    }
    // 최대값
    static final int MAX = 1_000_000_000;
    // 이동 방향
    static final int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;
        // 높이 정보
        int[][] map = new int[N+1][N+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        // (1, 1)에서 다른 좌표로 이동할 때 최대 좌표의 최소값 계산
        int[][] dp = getDP(map, N);

        // 정답 출력
        sb.append(dp[N][N]);
        System.out.println(sb);
    }

    private static int[][] getDP(int[][] map, int n) {
        int[][] dp = new int[n+1][n+1];
        for(int r = 1; r <= n; r++) Arrays.fill(dp[r], MAX);

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, 1, map[1][1], 0));
        dp[1][1] = 0;
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(dp[cur.row][cur.col] < cur.slope) continue;
            if(cur.row == n && cur.col == n) break;

            for(int[] d : DELTA){
                Node next = new Node(
                        cur.row+d[0],
                        cur.col+d[1]
                );
                if(next.row < 1 || next.row > n ||
                        next.col < 1 || next.col > n
                ) continue;

                next.height = map[next.row][next.col];
                next.slope = Math.max(Math.abs(next.height - cur.height), cur.slope);

                if(dp[next.row][next.col] > next.slope){
                    dp[next.row][next.col] = next.slope;
                    pq.offer(next);
                }
            }
        }

        return dp;
    }
}
