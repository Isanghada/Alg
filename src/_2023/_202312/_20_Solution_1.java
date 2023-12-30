package _2023._202312;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2418
// - DP : 각 단계의 가능한 경우를 차례로 계산
public class _20_Solution_1 {
    // 입력값 설정
    public static int H, W, L;
    public static long[][][] DP;
    public static char[][] BOARD;
    public static char[] WORD;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202312/_20_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 격자 설정값
        StringTokenizer st = new StringTokenizer(in.readLine());
        H = Integer.parseInt(st.nextToken());   // 행 길이
        W = Integer.parseInt(st.nextToken());   // 열 길이
        L = Integer.parseInt(st.nextToken());   // 단어 길이

        // 격자 정보 입력
        BOARD = new char[H][W];
        for(int r = 0; r < H; r++) BOARD[r] = in.readLine().toCharArray();

        // 단어 입력
        WORD = in.readLine().toCharArray();

        // DP 초기값 설정 : 첫 단계 입력
        DP = new long[L][H][W];
        for(int r = 0; r < H; r++){
            for(int c = 0; c < W; c++){
                if(BOARD[r][c] == WORD[0]){
                    DP[0][r][c] = 1;
                }
            }
        }

        // 차례로 다음 단계 모두 실행
        for(int step = 0; step < L-1; step++) getCount(step);

        // 정답 초기화
        long answer = 0;
        // 마지막 단계의 가능한 경우의 합 계산
        for(int r = 0 ; r < H; r++){
            for(int c = 0; c < W; c++){
                answer += DP[L-1][r][c];
            }
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }

    // 이동 방향 변수 : (상, 하, 좌, 우, 대각선) 8개
    public static int[][] DELTA = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    // 다음 단계 계산 함수
    private static void getCount(int step) {
        for(int r = 0; r < H; r++){
            for(int c = 0; c < W; c++){
                // 가능한 경우만 실행!
                if(DP[step][r][c] != 0){
                    // 가능한 방향 모두 탐색
                    for(int[] d : DELTA){
                        int nextR = r + d[0];
                        int nextC = c + d[1];

                        // 아래의 경우 패스
                        // - 범위를 벗어나는 경우
                        // - 다음 단어가 아닌 경우
                        if(nextR < 0 || nextR >= H ||
                        nextC < 0 || nextC >= W ||
                        BOARD[nextR][nextC] != WORD[step+1]
                        ) continue;
                        // 경우의 수 계산
                        DP[step+1][nextR][nextC] += DP[step][r][c];
                    }
                }
            }
        }
    }
}
