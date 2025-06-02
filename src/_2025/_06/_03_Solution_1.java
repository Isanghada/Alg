package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15924
// - DP : 도착지를 기준으로 가능한 모든 좌표 탐색!
public class _03_Solution_1 {
    static final int MOD = 1_000_000_009;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 지도 크기
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 지도 정보 입력
        char[][] map = new char[N+1][M+1];
        for(int n = 1; n <= N; n++){
            String input = in.readLine();
            for(int m = 1; m <= M; m++) map[n][m] = input.charAt(m-1);
        }

        int[][] dp = new int[N+1][M+1];
        dp[N][M] = 1;

        long answer = 0;
        for(int n = N; n > 0; n--){
            for(int m = M; m > 0; m--){
                answer += dp[n][m];
                answer %= MOD;

                if(map[n][m-1] == 'E' || map[n][m-1] == 'B') dp[n][m-1] = (dp[n][m-1] + dp[n][m]) % MOD;
                if(map[n-1][m] == 'S' || map[n-1][m] == 'B') dp[n-1][m] = (dp[n-1][m] + dp[n][m]) % MOD;
            }
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
