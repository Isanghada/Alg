package _2025._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14945
// - DP : 두 사람의 거리 차이를 기준으로 경우의 수 계산
//        거리가 0인 경우는 불가능하므로 가능한 경우 체크
//          - 거리가 그대로인 경우 : (아래, 아래), (대각, 대각)
//          - 거리가 늘어나는 경우 : (아래, 대각)
//          - 거리가 줄어드는 경우 : (대각, 아래)
public class _07_Solution_1 {
    static final int MOD = 10007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_04/_07_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 모서리 길이
        int N = Integer.parseInt(in.readLine());
        // DP 초기화
        // - dp[n][distance] : (타일 수, 두 사람 사이의 거리)인 경우의 수
        //                     두 사람 사이의 거리가 0인 경우는 불가능한 경우!
        int[][] dp = new int[N+1][N+1];

        // 길이가 2이상인 경우 탐색
        if(N > 1){
            // 길이가 2인 경우 경우의 수 2
            dp[2][1] = 2;

            // 길이 3이상인 경우부터 모두 탐색
            for(int n = 3; n <= N; n++){
                for(int distance = 1; distance < n; distance++){
                    // 거리 차이가 그대로인 경우
                    dp[n][distance] = (dp[n-1][distance] * 2) % MOD;
                    // 거리가 늘어나는 경우
                    dp[n][distance] = (dp[n][distance] + dp[n-1][distance-1]) % MOD;
                    // 거리가 줄어드는 경우
                    dp[n][distance] = (dp[n][distance] + dp[n-1][distance+1]) % MOD;
                }
            }
        }
        
        // 정답 초기화
        int answer = 0;
        // 문에 도달하는 모든 경우의 합
        for(int count: dp[N]) answer = (answer + count) % MOD;

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
