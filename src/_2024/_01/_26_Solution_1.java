package _2024._01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/28069
// - DP : 0번째 계단부터 각 계단에 도달하는 최소 횟수 계산
public class _26_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_01/_26_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 계단의 수
        int K = Integer.parseInt(st.nextToken());   // 계단을 오르는 횟수

        // DP 초기화 : 최대값
        int[] dp = new int[N+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        // 초기값 설정 : 0번째 계단에서 시작
        dp[0] = 0;
        // 각 계단에서 다음 계단으로 이동! : 이동 횟수 최소값으로 변경!
        for(int stair = 0; stair < N; stair++){
            // 1. 계단 한 칸을 오르는 경우
            int next = stair+1;
            dp[next] = Math.min(dp[next], dp[stair]+1);

            // 2. 지팡이로 오르는 경우
            next = stair + Math.floorDiv(stair, 2);
            if(next <= N) dp[next] = Math.min(dp[next], dp[stair]+1);
        }

        // 정답 반환
        // - N 번째 계단에 K번 이하로 도달할 경우 minigimbob 출력
        // - 아닐 경우 water 출력
        sb.append((dp[N] <= K) ? "minigimbob" : "water");
        System.out.println(sb);
    }
}
