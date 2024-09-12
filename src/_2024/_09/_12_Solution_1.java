package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1563
// - DP : 결석, 연속 지각 횟수에 따라 DP를 계산하여 개근상을 받을 수 있는 정보 반환!
public class _12_Solution_1 {
    public static final int MOD = 1_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 한 학기 기간
        int N = Integer.parseInt(in.readLine());
        // DP 초기화
        // dp[day][l][a]
        // - day : 날짜
        // - l : 지각 횟수
        // - a : 연속 지각 횟수
        int[][][] dp = new int[N+1][2][3];

        // 첫 날 출결 정보!
        dp[1][0][0] = 1;
        dp[1][1][0] = 1;
        dp[1][0][1] = 1;

        // DP를 통해 모든 정보 계산
        for(int day = 2; day <= N; day++){
            dp[day][0][0] = (dp[day-1][0][0] + dp[day-1][0][1] + dp[day-1][0][2]) % MOD;
            dp[day][0][1] = dp[day-1][0][0] % MOD;
            dp[day][0][2] = dp[day-1][0][1] % MOD;
            dp[day][1][0] = (dp[day-1][0][0] + dp[day-1][0][1] + dp[day-1][0][2]
                    + dp[day-1][1][0]  + dp[day-1][1][1] + dp[day-1][1][2]) % MOD;
            dp[day][1][1] = dp[day-1][1][0] % MOD;
            dp[day][1][2] = dp[day-1][1][1] % MOD;
        }

        // 개근상 정보 계산!
        int answer = 0;
        for(int lateness = 0; lateness < 2; lateness++){
            for(int absence = 0; absence < 3; absence++) answer = (answer + dp[N][lateness][absence]);
        }
        answer %= MOD;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}
