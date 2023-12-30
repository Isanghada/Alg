package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2240
// - DP : 시간별로 이동 횟수에 따라 받을 수 있는 경우 찾기!
public class _12_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023._202312/_12_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int T = Integer.parseInt(st.nextToken());   // 자두의 수
        int W = Integer.parseInt(st.nextToken());   // 이동 횟수

        // 떨어지는 자두 위치 입력
        int[] jadoo = new int[T+1];
        for(int i = 1; i <= T; i++){
            jadoo[i] = Integer.parseInt(in.readLine());
        }

        // dp 초기화
        int[][] dp = new int[W+1][T+1];
        
        // 모든 시간대 확인
        for(int t = 1; t <= T; t++){
            // 자두의 위치
            int p = jadoo[t];

            // 이동 횟수에 따라 자두의 개수 계산
            // - 이동횟수는 시간대 이하로만 가능!
            for(int w=0; w<=W; w++) {
                // 이동하지 않는 경우
                if(w == 0) {
                    dp[w][t] = dp[w][t-1];
                    if(p == 1) dp[w][t]++;
                    continue;
                }

                // 짝수인 경우 : 1번 위치에 있는 경우
                if(w % 2 == 0){
                    if(p == 1) dp[w][t] = Math.max(dp[w-1][t-1], dp[w][t-1] + 1);
                    else dp[w][t] = Math.max(dp[w-1][t-1]+1, dp[w][t-1]);
                // 홀수인 경우 :2번 위치에 있는 경우
                }else{
                    if(p == 1) dp[w][t] = Math.max(dp[w-1][t-1] + 1, dp[w][t-1]);
                    else dp[w][t] = Math.max(dp[w-1][t-1], dp[w][t-1] + 1);
                }
            }
        }

        // 정답 초기화
        int answer = 0;
        // - 최대값 찾기
        for(int w = 0; w <= W; w++) answer = Math.max(answer, dp[w][T]);

        // 결과 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
