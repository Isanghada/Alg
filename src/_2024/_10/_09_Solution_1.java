package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18430
// - 브루트포스 : 최대 범위가 5 X 5 이므로 가능한 모든 경우 탐색!
public class _09_Solution_1 {
    // N : 세로 크기
    // M : 가로 크기
    // LIMIT : 인덱스 제한값
    // ANSWER : 부메랑들 강도 합의 최댓값
    public static int N, M, LIMIT, ANSWER;
    // 나무 재료 정보
    public static int[][] BOARD;
    // 방문 배열
    public static boolean[][] VISITED;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로 크기
        M = Integer.parseInt(st.nextToken());   // 가로 크기
        LIMIT = N * M;  // 인덱스 제한값!
                        // index / M => 세로 좌표
                        // index % M => 가로 좌표

        // 나무 재료 배열
        BOARD = new int[N][M];
        // 방문 배열
        VISITED = new boolean[N][M];
        // 정보 입력
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 0; c < M; c++) BOARD[r][c] = Integer.parseInt(st.nextToken());
        }

        // 재귀를 통해 가능한 모든 경우 탐색!
        // - idx를 기준으로 부메랑 생성!
        bfs(0, 0);

        // 정답 출력
        sb.append(ANSWER);
        System.out.println(sb);
    }
    // 부메장 좌표!
    private static int[][][] DELTA = new int[][][] {{{0, -1}, {1, 0}},
                                                    {{-1, 0}, {0, -1}},
                                                    {{-1, 0}, {0, 1}},
                                                    {{0, 1}, {1, 0}}};
    // bfs 함수 : idx를 기준으로 가능하다면 부메랑 생성!
    private static void bfs(int idx, int total) {
        // 모든 인덱스를 탐색한 경우 정답 갱신!
        if(idx == LIMIT){
            ANSWER = Math.max(ANSWER, total);
        }else{
            // idx로 (r, c) 생성!
            int r = idx / M;
            int c = idx % M;

            // (r, c)를 방문하지 않은 경우 부메랑 탐색
            if(!VISITED[r][c]){
                for(int[][] d : DELTA){
                    // 부메랑 좌표 생성!
                    int[][] next = new int[][]{ {r+d[0][0], c+d[0][1]}, {r+d[1][0], c+d[1][1]}};
                    // 부메랑을 만들 수 있다면 생성!
                    if(check(next)){
                        VISITED[next[0][0]][next[0][1]] = true;
                        VISITED[next[1][0]][next[1][1]] = true;
                        VISITED[r][c] = true;
                        bfs(idx+1,
                            total +
                                    2*BOARD[r][c] +
                                    BOARD[next[0][0]][next[0][1]] +
                                    BOARD[next[1][0]][next[1][1]]);
                        VISITED[next[0][0]][next[0][1]] = false;
                        VISITED[next[1][0]][next[1][1]] = false;
                        VISITED[r][c] = false;
                    }
                }
            }
            bfs(idx+1, total);
        }
    }

    private static boolean check(int[][] next) {
        for(int[] p : next){
            if(p[0] < 0 || p[0] >= N ||
                    p[1] < 0 || p[1] >= M ||
                    VISITED[p[0]][p[1]]){
                return false;
            }
        }
        return true;
    }
}
