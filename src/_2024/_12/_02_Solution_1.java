package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1660
// - DP : 사면체에서 대포알 개수를 구하여 대포알 개수에서 최소 사면체 개수 계산!
public class _02_Solution_1 {
    // 최대 사면체 크기
    public static final int MAX = 122;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_02_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 각 사면체에서 대포알 개수!
        int[] counts = new int[MAX];
        counts[0] = 0;
        counts[1] = 1;
        for(int i = 2; i < MAX; i++) counts[i] = counts[i-1] + (counts[i-1] - counts[i-2]) + i;

        // 대포알 개수
        int N = Integer.parseInt(in.readLine());

        // dp 초기화
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 1;
        // 최소 사면체 개수 계산
        for(int n = 2; n <= N; n++){
            for(int j = 1; j < MAX; j++){
                if(counts[j] > n) break;
                dp[n] = Math.min(dp[n], dp[n-counts[j]] + 1);
            }
        }

        // 정답 반환
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
