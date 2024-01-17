package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9764
// - DP : 이전 결과를 토대로 다음 결과 계산
public class _17_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // DP 초기화
        // dp[target][range] : target을 1~range 범위의 자연수의 합으로 만드는 경우
        int[][] dp = new int[2001][2001];
        // - 0을 만드는 경우 모두 1로 초기화
        for(int num = 0; num < 2001; num++) dp[0][num] = 1;
        
        // 모든 경우 계산
        for(int target = 1; target < 2001; target++){
            for(int range = 1; range < 2001; range++){
                // 1. range를 포함하지 않고 target을 만드는 경우의 수
                dp[target][range] = dp[target][range-1];
                // target이 range 이상인 경우
                if(target >= range){
                    // range를 포함하여 target을 만드는 경우의 수
                    dp[target][range] += dp[target-range][range-1];
                    // 나머지 연산
                    dp[target][range] %= 100999;
                }
            }
        }

        // 테스트케이스 입력
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // N 입력
            int N = Integer.parseInt(in.readLine());
            // 경우의 수 반환
            sb.append(dp[N][N]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
