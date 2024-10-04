package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17069
// - DP : 각 방향별로 가능한 경우를 차례로 계산!
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 집 크기
        int N = Integer.parseInt(in.readLine());
        // 집 정보
        int[][] map = new int[N+1][N+1];

        StringTokenizer st = null;
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        // DP 초기화 : (r, c)를 끝 좌표로 가지는 파이프!
        // - dp[0][r][c] : 가로 방향 파이프 경우의 수
        // - dp[1][r][c] : 세로 방향 파이프 경우의 수
        // - dp[2][r][c] : 대각선 방향 파이프 경우의 수
        long[][][] dp = new long[3][N+1][N+1];

        // 초기 위치는 가로 방향 1가지 뿐!
        dp[0][1][2] = 1L;

        // 모든 방향 차례로 계산!
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if(map[r][c] == 0) {
                    dp[0][r][c] += dp[0][r][c-1] + dp[2][r][c-1];
                    dp[1][r][c] += dp[1][r-1][c] + dp[2][r-1][c];
                    if(map[r-1][c] == 0 && map[r][c-1] == 0) {
                        dp[2][r][c] += dp[0][r-1][c-1] + dp[1][r-1][c-1] + dp[2][r-1][c-1];
                    }
                }
            }
        }

        // 정답 반환
        sb.append(dp[0][N][N]+dp[1][N][N]+dp[2][N][N]);
        System.out.println(sb);
    }
}
