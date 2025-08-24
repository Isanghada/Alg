package _2025._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12888
// - DP : 높이가 H일 때 차의 개수는 외곽을 도는 1개, sum(dp[0], dp[1], ..., dp[H-2]) * 2 개
//        이므로 이를 점화식으로 활용해 계산
public class _24_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_08/_24_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 높이
        int H = Integer.parseInt(in.readLine());

        // DP 초기화
        // - 빠른 계산을 위해 누적합 활용
        long[] dp = new long[H+1];
        dp[0] = 1;

        if(H > 0){
            dp[1] = 2;
            // 점화식 : dp[h] = (dp[h-1] + dp[h-2] * 2 + 1)
            //          - dp[h-1] : 누적합 계산을 위한 이전값
            //          - dp[h-2] * 2 : 내부를 도는 차량의 수
            //          - 1 : 외곽을 도는 차량의 수
            for(int h = 2; h <= H; h++) dp[h] = dp[h-1] + dp[h-2] * 2 + 1;
            sb.append(dp[H] - dp[H-1]);
        }else sb.append(dp[H]);

        // 정답 출력
        System.out.println(sb);
    }
}
