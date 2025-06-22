package _2025._06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17498
// - DP : 각 좌표에서 이동할 수 있는 모든 경우 체크!
public class _22_Solution_1 {
    static final int MIN = -2_100_000_000;  // 최소 점수
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_06/_22_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 행 크기
        int M = Integer.parseInt(st.nextToken());   // 열 크기
        int D = Integer.parseInt(st.nextToken());   // 점프 크기
    
        int[][] board = new int[N+1][M+1];      // 격자
        int[][] dp = new int[N+1][M+1];         // DP
        // 격자 정보 입력, DP 초기화
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            Arrays.fill(dp[r], MIN);
            for(int c = 1; c <= M; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // 첫 행 초기화! : 첫 행에서 시작하므로 점수 0으로 설정
        Arrays.fill(dp[1], 0);
        // 모든 좌표 탐색
        for(int r = 1; r < N; r++){
            for(int c = 1; c <= M; c++){
                // 점프!
                for(int d = 1; d <= D; d++){
                    // 행 이동 변수
                    for(int moveR = 1; moveR <= d; moveR++){
                        int moveC = d - moveR;  // 열 이동 변수
                        int nextR = r + moveR;
                        if(nextR > N) continue;

                        int[] nextCols = new int[]{c - moveC, c + moveC};
                        for(int nextC : nextCols){
                            if(nextC < 1 || nextC > M) continue;
                            dp[nextR][nextC] = Math.max(dp[nextR][nextC],
                                    dp[r][c] + board[r][c] * board[nextR][nextC]);
                        }
                    }
                }
            }
        }

        int answer = MIN;
        for(int c = 1; c <= M; c++) answer = Math.max(answer, dp[N][c]);

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
