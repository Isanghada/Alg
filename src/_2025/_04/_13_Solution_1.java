package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1437
// - DP : 2와 3으로 이루어진 분해합에서 3이 최대한 많이 들어가도록 계산
public class _13_Solution_1 {
    static final int MOD =  10_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_13_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기값
        int N = Integer.parseInt(in.readLine());

        // dp 초기화
        int[] dp = new int[N+5];
        for(int n = 1; n <= 4; n++) dp[n] = n;

        // 이전 값에 3을 더하는 경우!
        for(int n = 5; n <= N; n++){
            dp[n] = (dp[n-3] * 3) % MOD;
        }

        // System.out.println(Arrays.toString(dp));

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb.toString());
    }
}
