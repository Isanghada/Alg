package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/17404
// - DP
public class _21_Solution_1 {
    public static final int SIZE = 3, MAX = 1000*1000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(in.readLine());
        int[][] arr = new int[N+1][SIZE];
        int[][] dp = new int[N+1][SIZE];

        for(int n = 1; n <= N; n++){
            arr[n] = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = MAX;
        for(int k = 0; k < 3; k++){
            for(int i = 0; i < SIZE; i++){
                if(k == i) dp[1][i] = arr[1][i];
                else dp[1][i] = MAX;
            }

            for(int i = 2; i <= N; i++){
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
            }

            for(int i = 0; i < SIZE; i++){
                if(i != k) answer = Math.min(answer, dp[N][i]);
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}