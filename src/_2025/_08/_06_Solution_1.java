package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://www.acmicpc.net/problem/16132
// -
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int total = N * (N+1) / 2;

        if((total & 1) == 1) sb.append(0);
        else{
            int target = total / 2;
            long[][] dp = new long[N+1][target+1];
            dp[0][0] = 1;

            for(int n = 1; n <= N; n++){
                for(int i = 0; i < n; i++) dp[n][i] = dp[n-1][i];
                for(int i = n; i <= target; i++){
                    dp[n][i] = dp[n-1][i] + dp[n-1][i-n];
                }
                // System.out.println(Arrays.toString(dp[n]));
            }

            sb.append(dp[N][target] / 2);
        }

        // 정답 출력
        System.out.println(sb);
    }
}
