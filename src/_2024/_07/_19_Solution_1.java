package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/13398
// - DP : 이전 결과를 활용해 최대값 계산
// - 참고 : 카데인 알고리즘(https://medium.com/@vdongbin/kadanes-algorithm-%EC%B9%B4%EB%8D%B0%EC%9D%B8-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-acbc8c279f29)
public class _19_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_19_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 수열 길이
        int N = Integer.parseInt(in.readLine());
        // 수열 정보
        int[] A = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::new).toArray();

        // 정답 출력
        // - DP를 통해 최대값 계산!
        sb.append(dp(N, A));
        System.out.println(sb.toString());
    }

    private static int dp(int n, int[] a) {
        // dp[i][j]
        // i = 0 : 수를 제거하지 않음
        // i = 1 : 특정 수를 제거함
        int[][] dp = new int[2][n];
        dp[0][0] = a[0];
        dp[1][0] = a[0];

        int answer = a[0];
        for(int i = 1; i < n; i++){
            dp[0][i] = Math.max(dp[0][i-1]+a[i], a[i]);
            dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1]+a[i]);

            answer = Math.max(answer, Math.max(dp[0][i], dp[1][i]));
        }

        return answer;
    }
}
