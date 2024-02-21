package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/3067
// - DP : 각 동전을 사용하여 얻을 수 있는 경우를 차례로 계산
public class _21_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트케이스 입력
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            // 동전 종류의 수
            int N = Integer.parseInt(in.readLine());
            // 동전 종류 입력
            int[] coins = Arrays.stream(("0 " + in.readLine()).split(" ")).mapToInt(Integer::new).toArray();
            // 목표 금액
            int K = Integer.parseInt(in.readLine());

            // DP 초기화
            int[][] dp = new int[N+1][K+1];
            for(int coinIdx = 1; coinIdx <= N; coinIdx++){
                // 현재 동전으로 만들 수 있는 초기 상태!
                dp[coinIdx][coins[coinIdx]]++;
                // 1 ~ K까지 차례로 만들 수 있는 모든 경우 탐색
                for(int price = 1; price <= K; price++){
                    // 이전 상태 그대로 저장
                    dp[coinIdx][price] += dp[coinIdx-1][price];
                    // 현재 동전을 추가할 수 있는 경우
                    if(price - coins[coinIdx] >= 0) {
                        dp[coinIdx][price] += dp[coinIdx][price - coins[coinIdx]];
                    }
                }
            }
//            System.out.println(Arrays.toString(coins));
//            for(int[] d : dp) System.out.println(Arrays.toString(d));
            // N개의 동전을 사용해 K를 만드는 경우의 수 반환
            sb.append(dp[N][K]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }
}