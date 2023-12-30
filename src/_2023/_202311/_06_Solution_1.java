package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2624
// - DP!! : 각 동전을 사용해 가능한 모든 조합 계산!
public class _06_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202311/_06_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지폐의 금액!
        int T = Integer.parseInt(in.readLine());
        // 동전의 가지 수!
        int K = Integer.parseInt(in.readLine());

        // 동전 정보!
        int[][] priceArr = new int[K+1][2];
        // 동전 추가
        for(int i = 1; i <= K; i++){
            String[] split = in.readLine().split(" ");
            // 동전의 금액
            priceArr[i][0] = Integer.parseInt(split[0]);
            // 동전의 개수
            priceArr[i][1] = Integer.parseInt(split[1]);
        }

        // DP 초기화
        // - dp[i][j] : i번째 동전까지 사용해 j 금액을 만들 수 있는 가지수
        int[][] dp = new int[K+1][T+1];
        // - [0][0] : 0원을 만들 수 있는 경우는 1개!
        dp[0][0] = 1;

        // 각 동전을 차례로 사용해 만들 수 있는 경우의 수 계산
        for(int i = 1; i <= K; i++){
            int p = priceArr[i][0];
            // 동전 사용 개수!
            for(int j = 0; j <= priceArr[i][1]; j++){
                for(int k = 0; k <= T; k++){
                    // 현재 값 계산
                    int cur = k + p * j;
                    // T 초과일 경우 종료
                    if(cur > T) break;
                    // cur을 개수 추가!
                    dp[i][cur] += dp[i-1][k];
                }
            }
        }

        // K개의 동전으로 T를 만드는 경우의 수 반환
        sb.append(dp[K][T]);
        System.out.println(sb);

    }
}
