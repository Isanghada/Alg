package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/14852
// - DP : 2×1, 1×2, 1×1을 사용하는 방법은 아래와 같습니다.
//          1. 2x1을 추가하는 경우
//          2. 1x1로 2x1을 만들어 추가하는 경우
//          3. 1x2로 2x2를 만들어 추가하는 경우
//          4. 1x2와 1x1로 2x2를 만들어 추가하는 경우 : 위치에 따라 2개
//          5. 1x2와 1x1로 (2x3, 2x4, 2x5,...)을 만드는 경우 : 위치에 따라 2개
//          => 2x1을 만드는 경우 2개, 2x2를 만드는 경우 3개, (2x3, 2x4, ...)을 만드는 경우
public class _15_Solution_1 {
    public static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());

        long[] dp = new long[N+1];
        long[] sum = new long[N+1];
        dp[0] = 1;
        dp[1] = 2;
        sum[0] = 1;
        sum[1] = 3;

        for(int n = 2; n <= N; n++) {
            dp[n] = (dp[n-1] * 2 + dp[n-2] * 3 + (n > 2 ? 2*sum[n-3] : 0)) % MOD;
            sum[n] = (sum[n-1] + dp[n]) % MOD;
        }
        // System.out.println(Arrays.toString(dp));

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb.toString());
    }
}
