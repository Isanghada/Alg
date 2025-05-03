package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2806
// - DP : 차례대로 전부 A로 만드는 경우, B로 만드는 경우를 계산
//          1. 임의의 글자 1개 변경
//          2. 첫 K개의 글자 모두 변경
public class _03_Solution_1 {
    static final int TYPE = 2, MAX = 10_000_000;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 분자 길이
        int N = Integer.parseInt(in.readLine());
        // 분자 정보
        char[] molecules = in.readLine().toCharArray();

        // DP 초기화
        // - dp[0][n] : n번째 글자까지 A로 만드는 최소 회수
        // - dp[1][n] : n번째 글자까지 B로 만드는 최소 회수
        int[][] dp = new int[TYPE][N+1];
        for(int type = 0; type < TYPE; type++) Arrays.fill(dp[type], MAX);

        // 첫 글자를 확인하여 DP 초기화
        if(molecules[0] == 'A') {
            dp[0][0] = 0;
            dp[1][0] = 1;
        }else{
            dp[0][0] = 1;
            dp[1][0] = 0;
        }

        // 이전 글자까지 A로 만든 결과, B로 만든 결과를 활용해 최소 횟수 계산
        for(int n = 1; n < N; n++){
            if(molecules[n] == 'A'){
                // 1. 이전 A로 만든 최소 횟수
                // 2. 이전 B로 만든 최소 횟수 + 1 : 첫 K개 글자 변경
                dp[0][n] = Math.min(dp[0][n-1], dp[1][n-1] + 1);

                // 1. 이전 A로 만드 최소 횟수 + 1 : 첫 K개 글자 변경
                // 2. 이전 B로 만든 최소 횟수 + 1 : 한 글자 변경
                dp[1][n] = Math.min(dp[0][n-1] + 1, dp[1][n-1] + 1);
            }else{
                // 1. 이전 A로 만든 최소 횟수 + 1 : 첫 K개 글자 변경
                // 2. 이전 B로 만든 최소 횟수 + 1 : 한 글자 변경
                dp[0][n] = Math.min(dp[0][n-1] + 1, dp[1][n-1] + 1);

                // 1. 이전 A로 만든 최소 횟수 + 1 : 첫 K개 글자 변경
                // 2. 이전 B로 만든 최소 횟수
                dp[1][n] = Math.min(dp[0][n-1] + 1, dp[1][n-1]);
            }
        }

        // 정답 반환
        sb.append(dp[0][N-1]);
        System.out.println(sb);
    }
}
