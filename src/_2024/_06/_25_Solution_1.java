package _2024._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17485
// - DP : 각 원소별로 이동하는 방향에 따라 계산!
public class _25_Solution_1 {
    // 이동 변수
    // - DELTA[0][x][x] : 왼쪽 이동으로 이동할 수 있는 경우
    // - DELTA[1][x][x] : 중앙 이동으올 이동할 수 있는 경우
    // - DELTA[2][x][x] : 오른쪼 이동으로 이동할 수 있는 경우
    public static int[][][] DELTA = new int[][][]{
            {{1, -1, 1}, {2, -1, 1}},
            {{0, -1, 0}, {2, -1, 0}},
            {{0, -1, -1}, {1, -1, -1}}
    };
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_06/_25_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 좌표
        int M = Integer.parseInt(st.nextToken());   // 열 좌표

        // 최대값
        final int LIMIT = 1_000_000;
        // 행렬 입력
        int[] map = new int[M+1];

        // dp 초기화
        // - dp[type][r][c] : type으로 (r, c) 좌표에 도달하는 최소값
        int[][][] dp = new int[3][N+1][M+2];
        for(int type = 0 ; type < 3; type++){
            for(int r = 1; r <= N; r++) Arrays.fill(dp[type][r], LIMIT);
        }

        
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            // r행 입력
            for(int c = 1; c <= M; c++) map[c] = Integer.parseInt(st.nextToken());
            // (r, c)에 도달하는 type 이동 최소값 계산!
            for(int c = 1; c <= M; c++){
                for(int type = 0; type < 3; type++){
                    dp[type][r][c] = map[c] + Math.min(
                            dp[DELTA[type][0][0]][r+DELTA[type][0][1]][c+DELTA[type][0][2]],
                            dp[DELTA[type][1][0]][r+DELTA[type][1][1]][c+DELTA[type][1][2]]
                    );
                }
            }
        }

        // 정답 초기화
        int answer = Integer.MAX_VALUE;
        // 모든 타입별로 최소값 탐색
        for(int type = 0; type < 3; type++){
            for(int c = 1; c <= M; c++) answer = Math.min(answer, dp[type][N][c]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}