package _2025._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17208
// - DP : 남은 치즈버거, 감자튀김을 기준으로 처리할 수 있는 주문 개수 체크
public class _07_Solution_1 {
    // 주문 클래스
    static class Order{
        int burger;         // 치즈버거 개수
        int frenchFries;    // 감자튀김 개수
        public Order(int burger, int frenchFries){
            this.burger = burger;
            this.frenchFries = frenchFries;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_02/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 주문 개수
        int M = Integer.parseInt(st.nextToken());   // 치즈버거 개수
        int K = Integer.parseInt(st.nextToken());   // 감자튀김 개수

        // 주문 정보 입력
        Order[] orders = new Order[N+1];
        for(int n = 1; n <= N; n++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            orders[n] = new Order(x, y);
        }

        // DP 초기화
        // - dp[n][m][k] : n번 주문을 처리하고, m개의 버거, k개의 감자튀김이 남는 경우의 수
        int[][][] dp = new int[N+1][M+1][K+1];
        for(int n = 0; n <= N; n++){
            for(int m = 0; m <= M; m++) Arrays.fill(dp[n][m], -1);
        }

        // 가능한 모든 경우 처리!
        dp[0][M][K] = 0;
        for(int n = 1; n <= N; n++){
            for(int m = 0; m <= M; m++){
                for(int k = 0; k <= K; k++){
                    dp[n][m][k] = dp[n-1][m][k];

                    // 현재 주문을 처리하기 위한 이전 상태 계산
                    Order past = new Order(m+orders[n].burger,
                            k+orders[n].frenchFries);

                    // 범위를 벗어나거나 불가능한 경우일 경우 패스
                    if(past.burger > M ||
                            past.frenchFries > K ||
                            dp[n-1][past.burger][past.frenchFries] == -1
                    ) continue;

                    // DP 최대값 갱신
                    dp[n][m][k] = Math.max(dp[n][m][k], dp[n-1][past.burger][past.frenchFries] + 1);
                }
            }
        }

        // 정답 초기화
        int answer = 0;
        // - 마지막 주문의 모든 경우 중 최대값 선택
        for(int m = 0; m <= M; m++) {
            for (int k = 0; k <= K; k++) {
                answer = Math.max(answer, dp[N][m][k]);
            }
        }
        
        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
