package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17953
// - DP : 날짜별로 각 디저트를 먹을 떄의 최대 만족감을 계산
public class _03_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_03_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 날짜 수
        int M = Integer.parseInt(st.nextToken());   // 디저트 수

        // 디저트 만족감 초기화
        int[][] desserts = new int[M][N+1];
        // 디저트 만족감 입력
        for(int m = 0; m < M; m++){
            st = new StringTokenizer(in.readLine());
            for(int n = 1; n <= N; n++) desserts[m][n] = Integer.parseInt(st.nextToken());
        }

        // dp 초기화
        // - dp[m][n] : m 디저트를 n 날에 먹을 때 최대 만족감
        int[][] dp = new int[M][N+1];
        for(int m = 0 ; m < M; m++) dp[m][1] = desserts[m][1];

        for(int n = 2; n <= N; n++){
            // n날에 먹을 디저트 target
            for(int target = 0; target < M; target++){
                // 이전 날에 먹은 디저트 before
                for(int before = 0; before < M; before++){
                    // 가능한 모든 경우 중 최대값으로 갱신
                    dp[target][n] = Math.max(
                            dp[target][n],
                            dp[before][n-1] + (target == before ?
                                                desserts[target][n] / 2 :
                                                desserts[target][n])
                    );
                }
            }
        }
        // 정답 초기화
        int answer = 0;
        // 모든 디저트를 마지막 날에 먹는 경우 중 최대값으로 갱신
        for(int[] score : dp) {
//            System.out.println(Arrays.toString(score));
            answer = Math.max(answer, score[N]);
        }

        // 정답 반환
        sb.append(answer);
        System.out.println(sb);
    }
}
