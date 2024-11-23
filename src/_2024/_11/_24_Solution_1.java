package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1720
// - 참고: https://velog.io/@jollidah/BOJ-1720%EB%B2%88-%ED%83%80%EC%9D%BC-%EC%BD%94%EB%93%9C
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[] dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= N; i++) dp[i] = dp[i-1] + dp[i-2] * 2;

        if(N < 2) sb.append(dp[N]);
        else if((N & 1) == 1) sb.append((dp[N] + dp[N / 2]) / 2);
        else sb.append((dp[N / 2 - 1] * 2 + dp[N / 2] + dp[N]) / 2);

        // 정답 출력
        System.out.println(sb);
    }
}
