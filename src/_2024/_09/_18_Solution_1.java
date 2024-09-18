package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2133
// - DP : 만들 수 있는 경우의 수를 아래 규칙을 사용핼 차례로 계산
//        1) N == 2인 경우 : 3개의 경우
//                           ∣∣   --   --
//                           ∣∣   ∣∣   --
//                           --   ∣∣   --
//        2) N == 4인 경우 : 2개의 경우
//                          ----   ∣--∣
//                          ∣--∣   ∣--∣
//                          ∣--∣   ----
//        3) N == 6인 경우 : 2개의 경우
//                          ------   ∣----∣
//                          ∣----∣   ∣----∣
//                          ∣----∣   ------
//        4) 4이상의 짝수 길이로 만들 수 있는 경우는 각각 2개!
public class _18_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_18_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 타일 길이
        int N = Integer.parseInt(in.readLine());
        // 홀수인 경우 만들 수 없으므로 0 반환
        if((N & 1) == 1) sb.append(0);
        else{
            // DP 초기화
            int[] dp = new int[31];
            // 길이 0인 경우 1개
            dp[0] = 1;

            // 길이 2부터 차례로 계산!
            for(int n = 2; n <= N; n += 2){
                // 길이 2인 경우 3개를 추가하는 경우
                dp[n] = dp[n-2] * 3;
                // 4이상의 길이를 추가하는 경우
                for(int tile = 4; tile <= n; tile += 2) dp[n] += dp[n-tile] * 2;
            }

            // 정답 반환
            sb.append(dp[N]);
        }

        // 정답 출력
        System.out.println(sb.toString());
    }
}
