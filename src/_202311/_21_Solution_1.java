package _202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4811
// - DP를 활용해 해결
// - [큰 알약의 수][작은 알약의 수]일 경우의 경우의 수 계산
public class _21_Solution_1 {
    public static long[][] dp;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_202311/_21_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(in.readLine());
        StringBuilder sb = new StringBuilder();

        // 30개의 알약일 경우까지 모두 계산!
        dp = new long[31][31];
        dp[0][0] = 1;   // 0개인 경우의 수
        dp[1][0] = 1;   // 1개인 경우의 수
        dp[2][0] = 2;   // 2개인 경우의 수

        // DP 계산
        calculateDP(30, 0);

        // 테스트 케이스 반복!
        while(true){
            // 알약의 수 입력
            int N = Integer.parseInt(in.readLine());
            // 0인 경우 종료
            if(N == 0) break;
            // 계산된 값 반환
            sb.append(dp[N][0]).append("\n");
        }

        // 정답 출력
        System.out.println(sb);
    }

    // DP 계산 함수
    // - big : 큰 알약의 수
    // - small : 작은 알약의 수
    private static long calculateDP(int big, int small) {
        // 큰 알약이 없는 경우 남은 경우의 수는 1
        if(big == 0) return 1;
        // 0이 아닌 경우 : 이미 계산된 경우 이므로 값 반환
        if(dp[big][small] != 0) return dp[big][small];

        // 경우의 수 계산
        long count = 0;
        // 큰 알약을 사용할 경우의 수 계산
        count += calculateDP(big-1, small+1);
        // 작은 알약을 사용할 경우의 수 계산(단, 작은 알약이 있는 경우)
        if(small != 0) count += calculateDP(big, small-1);

        // 계산된 경우의 수 반환
        return dp[big][small] = count;
    }
}
