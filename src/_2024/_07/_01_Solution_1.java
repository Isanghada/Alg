package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2410
// - DP : 이전 결과를 바탕으로 계산!
//   N=1(1) : 1
//   N=2(2) : 1+1, 2
//   N=3(2) : 1+1+1, 2+1
//   N=4(4) : 1+1+1+1, 2+1+1, 2+2, 4
//   N=5(4) : 1+1+1+1+1, 2+1+1+1, 2+2+1, 4+1
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] dp = new int[N+3];
        dp[1] = 1;
        dp[2] = 2;
        for(int n = 3; n <= N; n++){
            if((n & 1) == 1) dp[n] = dp[n-1];
            else dp[n] = (dp[n-1] + dp[n/2]) % 1_000_000_000;
        }

        // 정답 입력
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
