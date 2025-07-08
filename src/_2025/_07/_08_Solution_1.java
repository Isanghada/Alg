package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/23256
// - DP : 각 경우의 수를 차례로 계산!
//          2 가지 경우 계산
//           1. 전체 경우의 수 : 다음을 만들 수 있는 경우는 3가지!
//           2. 가장 우측이 1칸인 경우의 수 : 다음을 만들 수 있는 경우는 4가지!
//                                            가장 우측을 2칸으로 변경하여 다음 경우 생성!
public class _08_Solution_1 {
    static final int MAX = 1_000_000, MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        long[][] dp = new long[2][MAX+1];
        dp[0][1] = 7;
        dp[1][1] = 3;

        for(int i = 2; i <= MAX; i++){
            dp[0][i] = (dp[0][i-1] * 3 + dp[1][i-1] * 4) % MOD;
            dp[1][i] = (dp[0][i-1] + dp[1][i-1] * 2) % MOD;
        }

        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            int n = Integer.parseInt(in.readLine());
            sb.append(dp[0][n]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}
