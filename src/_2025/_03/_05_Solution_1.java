package _2025._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11985
// - DP : 각 오렌지를 기준으로 한 상자에 담을 수 있는 경우 중 최소 비용 계산
public class _05_Solution_1 {
    static final long MAX = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_03/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] dp = new long[N+1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;

        long[] oranges = new long[N+1];
        for(int i = 1; i <= N; i++) oranges[i] = Integer.parseInt(in.readLine());

        for(int i = 1; i <= N; i++){
            long max = 0;
            long min = MAX;
            for(int j = 1; j <= M; j++){
                if( i < j ) break;
                max = Math.max(max, oranges[i-j+1]);
                min = Math.min(min, oranges[i-j+1]);
                dp[i] = Math.min(dp[i], dp[i-j]+ K + j * (max - min));
            }
        }

        // 정답 출력
        sb.append(dp[N]);
        System.out.println(sb);
    }
}
