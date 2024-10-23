package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2698
// - DP : 수열 길이, 인접 비트의 수를 기준으로 DP 계산!
public class _23_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_23_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 길이 100, 최대 인접 개수 100 으로 DP 계산!
        int[][][] dp = setDP(100, 100);

        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        StringTokenizer st = null;
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 길이!
            int K = Integer.parseInt(st.nextToken());   // 인접 개수!

            // DP 값을 활용해 계산!
            sb.append(getSum(dp, N, K)).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    private static int getSum(int[][][] dp, int n, int k) {
        return dp[n][k][0] + dp[n][k][1];
    }

    private static int[][][] setDP(int N, int K) {
        // DP 초기화 : 길이 1인 경우 입력!
        int[][][] dp = new int[N+1][K+1][2];
        dp[1][0][0] = 1;
        dp[1][0][1] = 1;

        // DP 계산!
        for(int k = 0; k <= K; k++){
            for(int n = 2; n <= N; n++){
                if(k == 0) dp[n][k][1] = dp[n-1][k][0];
                else dp[n][k][1] = dp[n-1][k-1][1] + dp[n-1][k][0];
                dp[n][k][0] = dp[n-1][k][0] + dp[n-1][k][1];
            }
        }

        return dp;
    }
}
