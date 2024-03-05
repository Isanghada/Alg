package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6593
// - BFS : 시작 지점에서 최소 경로 탐색
public class _05_Solution_1 {
    // 이동 변수
    private static final int[][] DELTA = new int[][]{{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
    // 좌표 클래스
    public static class Node{
        int l;      // 층
        int r;      // 행
        int c;      // 열
        int time;   // 시간
        public Node(int l, int r, int c, int time){
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            // 빌딩 크기 입력
            st = new StringTokenizer(in.readLine());
            int L = Integer.parseInt(st.nextToken());   // 층
            int R = Integer.parseInt(st.nextToken());   // 행
            int C = Integer.parseInt(st.nextToken());   // 열

            // 모든 입력이 0인 경우 종료
            if(L == 0 && R == 0 && C ==0) break;

            // 시작 지머 초기화
            Node start = null;
            // 빌딩 정보 입력
            char[][][] board = new char[L][R][C];
            for(int l = 0; l < L; l++){
                for(int r = 0; r < R; r++){
                    String input = in.readLine();
                    for(int c = 0; c < C; c++){
                        board[l][r][c] = input.charAt(c);
                        // 시작 지점 입력
                        if(board[l][r][c] == 'S') start = new Node(l, r, c, 0);
                    }
                }
                in.readLine();
            }

            // BFS를 통해 가능하다면 최소 시간 반환!
            // 탈출이 불가능하면 Trapped! 반환
            sb.append(bfs(board, start)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static String bfs(char[][][] board, Node start) {
        StringBuilder sb = new StringBuilder();
        final int L = board.length;
        final int R = board[0].length;
        final int C = board[0][0].length;

        Deque<Node> deque = new LinkedList<>();
        boolean[][][] visited = new boolean[L][R][C];

        deque.offerLast(start);
        visited[start.l][start.r][start.c] = true;

        while(!deque.isEmpty()){
            Node cur = deque.pollFirst();

            if(board[cur.l][cur.r][cur.c] == 'E'){
                sb.append("Escaped in ").append(cur.time).append(" minute(s).");
                return sb.toString();
            }

            for(int[] d : DELTA){
                Node next = new Node(cur.l+d[0], cur.r+d[1], cur.c+d[2], cur.time+1);

                if(next.l < 0 || next.l >= L || next.r < 0 || next.r >= R || next.c < 0 || next.c >= C ||
                        visited[next.l][next.r][next.c] ||
                        board[next.l][next.r][next.c] == '#'
                ) continue;

                deque.offerLast(next);
                visited[next.l][next.r][next.c] = true;
            }
        }

        sb.append("Trapped!");
        return sb.toString();
    }
}
