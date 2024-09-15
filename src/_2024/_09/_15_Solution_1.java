package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14497
// - 다익스트라 : 주난이의 위치에서 BFS를 통해 각 위치간의 최소 횟수 계산
public class _15_Solution_1 {
    // 노드 클래스
    private static class Node implements Comparable<Node>{
        int row;    // 행
        int col;    // 열
        int cost;   // 횟수
        public Node(int row, int col, int cost){
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        
        st = new StringTokenizer(in.readLine());
        // 시작 위치
        int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        // 타겟 위치
        int[] target = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        // 교실 정보
        int[][] board = new int[N+1][M+1];
        for(int r = 1; r <= N; r++){
            String input = " " + in.readLine();
            for(int c = 1; c<= M; c++) board[r][c] = input.charAt(c);
        }

        // 정답 출력
        // - start에서 target으로 가는 최소 횟수 계산
        sb.append(dijkstra(board, start, target, N, M));
        System.out.println(sb.toString());
    }
    // 이동 변수
    private static int[][] DELTA = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int dijkstra(int[][] board, int[] start, int[] target, int n, int m) {
        // 우선순위 큐 초기화
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        // 방문 배열 초기화
        boolean[][] visited = new boolean[n+1][m+1];

        // 초기 정보 설정
        pq.offer(new Node(start[0], start[1], 0));
        visited[start[0]][start[1]] = true;

        while(!pq.isEmpty()){
            // 현재 노드 반환
            Node cur = pq.poll();

            // 타겟 위치인 경우 횟수 반환
            if(cur.row == target[0] && cur.col == target[1]) return cur.cost;

            // 이동!
            for(int[] d : DELTA){
                // 다음 좌표 계산
                int nextRow = cur.row+d[0];
                int nextCol = cur.col+d[1];

                // 범위를 벗어나거나 이미 방문한 경우 패스
                if(nextRow < 1 || nextRow > n ||
                        nextCol < 1 || nextCol > m ||
                        visited[nextRow][nextCol]) continue;

                // 친구 위치한 경우 횟수 1 증가!
                pq.offer(new Node(nextRow, nextCol, cur.cost + (board[nextRow][nextCol] == '0' ? 0 : 1)));
                visited[nextRow][nextCol] = true;
            }
        }

        return 0;
    }
}
