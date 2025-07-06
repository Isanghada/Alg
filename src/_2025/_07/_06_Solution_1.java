package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/11056
// - DP
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] A = ("0"+in.readLine()).toCharArray();
        char[] B = ("0"+in.readLine()).toCharArray();

        int aLen = A.length - 1;
        int bLen = B.length - 1;

        int[][] dp = new int[aLen+1][bLen+1];

        for(int a = 1; a <= aLen; a++){
            for(int b = 1; b<= bLen; b++){
                dp[a][b] = Math.max(dp[a-1][b], dp[a][b-1]);
                if(A[a] == B[b]) dp[a][b] = Math.max(dp[a][b], dp[a-1][b-1] + 1);
            }
        }

        int answer = aLen + bLen - dp[aLen][bLen];

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
