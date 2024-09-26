package _2024._09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15553
// - DP : 각 사탕별로 돈의 제한 내에서 가장 많은 칼로리를 차례로 계산!
public class _26_Solution_1 {
    // 사탕 클래스
    public static class Candy{
        int calorie;    // 칼로리
        int money;      // 가격
        public Candy(int calorie, int money){
            this.calorie = calorie;
            this.money = money;
        }
    }
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_09/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            // 사탕의 수
            int N = Integer.parseInt(st.nextToken());
            // 돈의 양
            // - 돈의 경우 소수점을 제외하고 활용!
            int M = Integer.parseInt(st.nextToken().replace(".", ""));

            // 모두 0인 경우 종료
            if(N == 0 && M == 0) break;

            // dp 초기화
            int[][] dp = new int[N+1][M+1];
            for(int n = 1; n <= N; n++){
                st = new StringTokenizer(in.readLine());
                // 사탕 정보
                Candy candy = new Candy(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken().replace(".", ""))
                );

                // 현재 사탕을 구매하지 않는 경우 입력
                for(int m = 1; m <= M; m++) dp[n][m] = dp[n-1][m];

                // 현재 사탕을 구매할 수 있는 경우 모두 갱신!
                for(int m = candy.money; m <= M; m++){
                    dp[n][m] = Math.max(dp[n][m],
                            Math.max(dp[n][m-candy.money], dp[n-1][m- candy.money]) + candy.calorie);
                }
            }

            sb.append(dp[N][M]).append("\n");
        }

        // 정답 반환
        System.out.println(sb);
    }
}
