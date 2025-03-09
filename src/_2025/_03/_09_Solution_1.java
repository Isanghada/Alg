package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13707
// - 참고 : https://jun-bae.tistory.com/27
public class _09_Solution_1 {
    static final int MOD = 1_000_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N+1][K+1];
        Arrays.fill(dp[0], 1);

        for(int n = 1; n <= N; n++){
            dp[n][1] = 1;
            for(int k = 2; k <= K; k++){
                dp[n][k] = (dp[n][k-1] + dp[n-1][k]) % MOD;
            }
        }

//        for(int[] d : dp) System.out.println(Arrays.toString(d));

        // 정답 출력
        sb.append(dp[N][K]);
        System.out.println(sb);
    }
}
