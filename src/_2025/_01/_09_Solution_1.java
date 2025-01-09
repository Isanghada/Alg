package _2025._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2758
// - DP : 첫 번째 로또 번호부터 차례로 가능한 모든 경우 체크!
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_01/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 로또 번호 경우의 수 DP : 누적합을 활용해 계산
        // - n : 번호의 수
        // - m : 번호 범위
        long[][] dp = getDP(10, 2000);
        // for(long[] d : dp) System.out.println(Arrays.toString(d));
        
        StringTokenizer st = null;
        // 테스트 케이스
        int T = Integer.parseInt(in.readLine());
        while(T-- > 0){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 번호의 수
            int M = Integer.parseInt(st.nextToken());   // 번호 범위

            // 계산된 결과에서 (N, M) 반환
            sb.append(dp[N][M]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // DP 생성 함수
    private static long[][] getDP(int n, int m) {
        // dp 초기화
        long[][] dp = new long[n+1][m+1];
        // 0번째의 경우 1로 초기화
        Arrays.fill(dp[0], 1L);
        // 탐색할 번호 인덱스
        for(int step = 1; step <= n; step++){
            // 해당 인덱스에 선택할 번호
            for(int number = 1; number <= m; number++){
                // 누적합
                // - number 이전까지 가능한 경우의 수
                // - number을 선택할 수 있는 경우의 수
                dp[step][number] = dp[step][number-1] + dp[step-1][number/2];
            }
        }

        return dp;
    }
}
