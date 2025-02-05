package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1757
// - DP : 각 시간별로 지침 정도에 따라 최대값 계산!
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 운동할 시간
        int M = Integer.parseInt(st.nextToken());   // 최대 지침 정도

        // 운동 정보
        int[] D = new int[N+1];
        for(int i = 1; i <= N; i++) D[i] = Integer.parseInt(in.readLine());

        // dp 초기화
        // - dp[n][m] : n 시간에 m 지침 정도일 때의 최대 거리
        int[][] dp = new int[N+1][M+1];

        // 모든 시간 계산
        for(int d = 1; d <= N; d++){
            // m 지침 정도에서 쉬는 경우
            dp[d][0] = dp[d-1][0];
            for(int m = 1; m <= M && d > m; m++){
                dp[d][0] = Math.max(dp[d][0], dp[d-m][m]);
            }

            // m 지침 정도가 되도록 운동하는 경우
            for(int m = 1; m <= M; m++){
                dp[d][m] = dp[d-1][m-1] + D[d];
            }
        }

        // 정답 출력
        sb.append(dp[N][0]);
        System.out.println(sb);
    }
}
