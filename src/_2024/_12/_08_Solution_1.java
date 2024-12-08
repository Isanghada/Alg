package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2253
// - DP : 각 번호의 돌에 도달할 수 있는 경우의 수를 점프 범위에 따라 계산!
public class _08_Solution_1 {
    // MAX_JUMP : 점프 범위를 차례로 증가시킬 경우 10000을 넘는 경우는 141
    //            여유있게 계산하고자 200으로 설정
    public static final int INF = 100_000_000, MAX_JUMP = 200;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_08_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 돌의 수
        int M = Integer.parseInt(st.nextToken());   // 작은 돌의 수

        // 작은 돌 번호 입력
        Set<Integer> unusedStone = new HashSet<>();
        while(M-- > 0) unusedStone.add(Integer.parseInt(in.readLine()));

        // dp 초기화
        // - dp[stone][jump] = jump로 stone에 도달할 수 있는 경우의 수
        int[][] dp = new int[N+1][MAX_JUMP];
        for(int n = 0; n <= N; n++) {
            Arrays.fill(dp[n], INF);
        }
        dp[1][0] = 0;

        // 모든 돌에 대해 jump 탐색!
        for(int stone = 1; stone < N; stone++){
            for(int jump = 0; jump < MAX_JUMP; jump++){
                // 도달할 수 없는 경우 패스
                if(dp[stone][jump] == INF) continue;
                // 가능한 점프 범위 탐색
                for(int next = jump-1; next <= jump+1; next++){
                    // 한 칸 이상 이동할 경우만 탐색
                    if(next > 0){
                        // 다음 돌 번호
                        int nextStone = stone+next;
                        // 범위내에 있는 돌이고, 작은 돌이 아닌 경우 최소값 갱신!
                        if(nextStone <= N && !unusedStone.contains(nextStone)) {
                            dp[nextStone][next] = Math.min(dp[nextStone][next], dp[stone][jump]+1);
                        }
                    }
                }
            }
        }

        int answer = INF;
        for(int jumpCount : dp[N]) answer = Math.min(answer, jumpCount);

        // 정답 출력
        sb.append(answer == INF ? -1 : answer);
        System.out.println(sb);
    }
}
