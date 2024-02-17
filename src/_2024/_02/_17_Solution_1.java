package _2024._02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22115
// - DP : 배낭 문제
public class _17_Solution_1 {
    public static final int MAX = 1000000;      // 최대값
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_02/_17_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 커피의 개수
        int K = Integer.parseInt(st.nextToken());   // 섭취해야하는 카페인의 양

        // DP 초기화
        int[] dp = new int[K+1];
        Arrays.fill(dp, MAX);
        // 카페인 0을 섭취하는 경우는 커피 0개를 마시는 것!
        dp[0] = 0;

        // 모든 커피의 경우 차례로 계산
        st = new StringTokenizer(in.readLine());
        while(N-- > 0){
            // 커피의 카페인
            int C = Integer.parseInt(st.nextToken());
            // 아래의 경우 중 최소값으로 갱신
            // - 원래의 커피 개수
            // - 현재 커피를 마시는 경우의 커피 개수
            for(int i = K; i >= C; i--) dp[i] = Math.min(dp[i], dp[i-C]+1);
        }

        // 정답 출력
        // - 100개 초과의 커피를 마셔야하는 경우 => 불가능한 경우 -1 반환
        // - 아닌 경우 커피의 개수 반환
        sb.append(dp[K] > 100 ? -1 : dp[K]);
        System.out.println(sb);
    }
}
