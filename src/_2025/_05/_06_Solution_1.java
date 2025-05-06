package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// https://www.acmicpc.net/problem/20544
// - DP : 이동할 수 있는 경우를 이용하여 각 위치에 도착할 수 있는 경우의 수 계산
public class _06_Solution_1 {
    static final int MOD = 1_000_000_007, POINT = 1010, TYPE = 2;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 도착 좌표
        int N = Integer.parseInt(in.readLine()) + 1;
        // dp 초기화
        // - dp[type][n] : n 위치가 0인 경우의 수
        //  - type : 높이 2인 장애물 등장 여부
        //  - n : 위치
        long[][] dp = new long[TYPE][POINT];

        dp[0][0] = 0;
        dp[1][0] = 1;
        dp[0][1] = 1;
        dp[1][1] = 0;
        dp[0][2] = 1;
        dp[1][2] = 0;
        dp[0][3] = 2;
        dp[1][3] = 1;

        for(int n = 4; n <= N; n++){
            // 높이 2인 장애물을 넘지 않은 경우
            dp[0][n] = (dp[0][n]
                    + dp[0][n-1]    // 이동 명령이 0 인경우
                    + dp[0][n-2]    // 이동 명령이 10 인경우
                    + dp[0][n-3])   // 이동 명령이 110 인경우
                    % MOD;

            // 높이 2인 장애물을 넘은 경우
            dp[1][n] = (dp[1][n]
                    // 이번 이동에서 높이 2인 장애물을 넘는 경우
                    + dp[0][n-2]        // 이동 명령이 20 인 경우
                    + dp[0][n-3] * 2    // 이동 명령이 120, 210 인 경우
                    // 이미 높이 2인 장애물을 넘은 경우
                    + dp[1][n-1]        // 이동 명령이 0 인 경우
                    + dp[1][n-2] * 2    // 이동 명령이 10, 20 인 경우
                    + dp[1][n-3] * 3)   // 이동 명령이 110, 120, 210 인 경우
                    % MOD;
        }

        // 정답 출력
        sb.append(dp[1][N]);
        System.out.println(sb);
    }
}
