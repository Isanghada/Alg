package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14728
// - DP : 각 경우 차례로 계산!
public class _01_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_01_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 단원 개수
        int T = Integer.parseInt(st.nextToken());   // 공부할 수 있는 총 시간

        // DP 초기화
        // - DP[n][t] : n개의 단원을 t시간 동안 공부할 때 얻는 최대 점수
        int[][] dp = new int[N+1][T+1];
        for(int part = 1; part <= N; part++){
            st = new StringTokenizer(in.readLine());
            int time = Integer.parseInt(st.nextToken());    // 시간
            int score = Integer.parseInt(st.nextToken());   // 점수
            // 이전까지의 결과에 현재 단원을 공부하는 경우 계산
            for(int t = 0; t <= T; t++){
                // 이전 단원까지의 결과로 초기화
                dp[part][t] = dp[part-1][t];
                // 현재 단원을 공부할 수 있는 경우 최대값으로 변경
                if(t - time >= 0) dp[part][t] = Math.max(dp[part][t], dp[part-1][t-time]+score);
            }
        }

        // 정답 입력
        sb.append(dp[N][T]);
        System.out.println(sb);
    }
}
