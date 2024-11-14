package _2024._11;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1749
// - 누적합 : 모든 범위의 누적합을 구한뒤, 가능한 부분 행렬의 합을 비교!
public class _14_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_11/_14_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기

        // 보드 정보
        int[][] board = new int[N+1][M+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= M; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // 누적합
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= M; c++) board[r][c] += board[r][c-1];
        }

        for(int c = 1; c <= M; c++){
            for(int r = 1; r <= N; r++) board[r][c] += board[r-1][c];
        }
        /////////////////

        // 정답 초기화
        int answer = Integer.MIN_VALUE;
        // 모든 범위 탐색!
        for(int startR = 1; startR <= N; startR++){
            for(int startC = 1; startC <= M ; startC++){
                for(int endR = startR; endR <= N; endR++) {
                    for (int endC = startC; endC <= M; endC++) {
                        answer = Math.max(answer,
                                board[endR][endC]
                                -board[startR-1][endC]
                                - board[endR][startC-1]
                                + board[startR-1][startC-1]
                        );
                    }
                }
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb.toString());
    }
}

