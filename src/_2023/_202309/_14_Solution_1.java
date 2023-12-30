package _2023._202309;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2225
// - DP를 활용하여 해결
// - dp[n][k] : k개의 정수로 n을 만드는 경우의 수
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202309/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력값 설정
        int n = Integer.parseInt(st.nextToken());   // 목표값
        int k = Integer.parseInt(st.nextToken());   // 사용할 정수의 수

        // dp 초기화
        // - dp[n][k] : k개의 정수로 n을 만드는 경우의 수
        int[][] dp = new int[n+1][k+1];
        // - dp[v][1] 초기화 => 1개의 정수로 v를 만드는 경우의 수는 1개
        for(int v = 0; v <= n; v++) dp[v][1] = 1;

        // 1개의 정수를 사용하는 경우부터 모든 정수를 더해가며 경우의 수 계산
        for(int step = 1; step < k; step++){
            for(int v1 = 0; v1 <= n; v1++){
                for(int v2 = 0; v2 <= n; v2++){
                    int next = v2 + v1;
                    // 다음값이 n초과라면 종료!
                    if(next > n) break;
                    // v2의 모든 경우에 v1을 더하면 next가 되므로 dp[v2][step]만큼 증가
                    dp[next][step+1] += dp[v2][step];
                    // 10억으로 나눈 나머지로 계산
                    dp[next][step+1] %= 1000000000;
                }
            }
        }

        // 정답 반환
        sb.append(dp[n][k]);
        System.out.println(sb);
    }
}
