package _2024._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/20500
// - 참고 : https://kangwlgns.tistory.com/17
// - DP : 15의 배수가 되기 위한 규칙 활용!
//        1. 3의 배수 조건 : 모든 자리수의 합을 3으로 나누었을 때 나누어 떨어지는 경우
//        2. 5의 배수 조건 : 마지막 값이 0 또는 5인 경우!
// - 모든 자연수를 확인하기 어려우므로, 마지막 값은 5로 고정하고 첫 자리에 1 또는 5를 추가하며 값 확인!
//    1, 10, 100, 1000, ... => 3으로 나눌 경우 나머지 1
//    5, 50, 500, 5000, ... => 3으로 나눌 경우 나머지 2
//   따라서, 이전 경우에 1 또는 5를 추가하여 나머지 값을 조절할 수 있다!
//   ex) 55 % 3 = 1 => (155 % 3) = ((100 % 3) + (55 % 3)) % 3 = (1 + 1) % 3 = 2
//   3으로 나누어 떨어지고, 마지막 값이 5인 경우 15의 배수가 되므로
//   1, 5를 추가하여 나머지를 조절하며 게산할 수 있다
public class _26_Solution_1 {
    public static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_07/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정수 길이
        int N = Integer.parseInt(in.readLine());

        // 길이가 1인 경우 가능한 경우가 없으므로 0 반환
        if(N == 1) sb.append(0);
        else{
            // dp 초기화
            // - dp[m][n] : 5로 끝나는 n자리수 중 3으로 나눈 나머지가 m인 개수!
            int[][] dp = new int[3][N+1];
            dp[0][2] = 1;   // 15
            dp[1][2] = 1;   // 55

            // 모든 경우 계산!
            for(int i = 3; i <= N; i++){
                // 나머지가 1일 때 5를 추가하거나, 나머지가 2일 때 1을 추가하면 3으로 나누어 떨어진다.
                dp[0][i] = (dp[1][i-1] + dp[2][i-1]) % MOD;
                // 나머지가 0일 때 1을 추가하거나, 나머지가 2일 때 5를 추가하면 3으로 나눈 나머지가 1인다.
                dp[1][i] = (dp[0][i-1] + dp[2][i-1]) % MOD;
                // 나머지가 0일 때 5를 추가하거나, 나머지가 1일 때 1을 추가하면 3으로 나눈 나머지가 2인다.
                dp[2][i] = (dp[0][i-1] + dp[1][i-1]) % MOD;
            }
            // 3으로 나누어 떨어지는 경우 반환!
            sb.append(dp[0][N]);
        }

        // 정답 반환
        System.out.println(sb);
    }
}
