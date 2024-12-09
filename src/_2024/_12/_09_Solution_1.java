package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14925
// - 누적합 : 각 좌표에서 최대 크기를 차례로 계산!
//            정사각형의 크기는 (r, c)를 우하단이고 들판이라고 할 때
//            (r, c-1), (r-1, c), (r-1, c-1) 중 최소 길이 + 1
public class _09_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_09_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int M = Integer.parseInt(st.nextToken());   // 행 길이
        int N = Integer.parseInt(st.nextToken());   // 열 길이

        // 정답 초기화
        int answer = 0;
        // 땅 정보 초기화
        int[][] board = new int[M+1][N+1];

        // 땅 정보 입력 및 누적합
        for(int r = 1; r <= M; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++) {
                // 땅 정보 입력
                board[r][c] = Integer.parseInt(st.nextToken());
                // 들판이 아닌 경우 -1로 갱신 후 패스
                if(board[r][c] > 0) {
                    board[r][c] = -1;
                    continue;
                }
                // 주변의 정사각형 길이 중 최소값 확인
                int minL = Math.min(board[r][c-1], Math.min(board[r-1][c], board[r-1][c-1]));
                // (r, c)의 최대 정사각형 길이 갱신
                board[r][c] = Math.max(minL, 0) + 1;
                answer = Math.max(answer, board[r][c]);
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
