package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4095
// - DP : 각 위치에서 가능한 최대 크기의 정사각형 탐색!
//        (r, c)에서 가능한 최대 정사각형 크기!
//        1. (r, c)의 0인 경우 0
//        2. (r, c)가 1인 경우 (r-1, c-1), (r-1, c), (r, c-1)에서 가능한 정사각형 중 최소값 + 1
public class _20_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        while(true){
            st = new StringTokenizer(in.readLine());
            int N = Integer.parseInt(st.nextToken());   // 행 크기
            int M = Integer.parseInt(st.nextToken());   // 열 크기

            if(N == 0 && M == 0) break;

            // 행렬 정보 입력
            int[][] dp = new int[N+1][M+1];
            for(int r = 1; r <= N; r++){
                st = new StringTokenizer(in.readLine());
                for(int c = 1; c <= M; c++) dp[r][c] = Integer.parseInt(st.nextToken());
            }
            
            // 정답 초기화
            int answer = 0;
            // 모든 좌표 차례로 탐색!
            for(int r = 1; r <= N; r++){
                for(int c = 1; c <= M; c++){
                    if(dp[r][c] != 0){
                        dp[r][c] = min(dp[r-1][c-1], dp[r-1][c], dp[r][c-1]) + 1;
                        answer = Math.max(answer, dp[r][c]);
                    }
                }
            }

            sb.append(answer).append("\n");
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
