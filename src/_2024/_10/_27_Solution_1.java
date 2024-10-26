package _2024._10;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18427
// - DP : 이전까지의 결과를 활용해 차례로 계산!
public class _27_Solution_1 {
    public static final int MOD = 10_007;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_10/_27_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 학생 수
        int M = Integer.parseInt(st.nextToken());   // 최대 블록 개수
        int H = Integer.parseInt(st.nextToken());   // 목표 높이

        // DP 초기화
        int[][] dp = new int[N+1][H+1];
        // - 학생 0명이 높이 0을 만드는 경우는 1개!
        dp[0][0] = 1;

        // 학생 n명에 대해 차례로 가능한 높이 계산!
        for(int n = 1; n <= N; n++){
            // 블록 정보
            st = new StringTokenizer(in.readLine());
            List<Integer> blocks = new ArrayList<>();
            while(st.hasMoreTokens()) blocks.add(Integer.parseInt(st.nextToken()));

            // 모든 높이 탐색!
            for(int h = 0; h <= H; h++){
                dp[n][h] = dp[n-1][h];
                for(int block : blocks){
                    // 현재 block를 쌓기 위한 이전 높이 계산!
                    int pastHeight = h - block;
                    if(pastHeight < 0) continue;
                    dp[n][h] += dp[n-1][pastHeight];
                    dp[n][h] = dp[n][h] % MOD;
                }
            }
        }

        // 정답 반환
        sb.append(dp[N][H]);
        System.out.println(sb);
    }
}
