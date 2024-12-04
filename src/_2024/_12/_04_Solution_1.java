package _2024._12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14722
// - DP : 각 좌표마다 딸기, 초코, 바나나 우유를 마시는 경우의 수 계산
public class _04_Solution_1 {
    // 우유의 종류
    public static final int TYPE = 3;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_12/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 영역 크기
        int N = Integer.parseInt(in.readLine());

        // 우유 가계 정보 입력
        StringTokenizer st = null;
        int[][] board = new int[N+1][N+1];
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            for(int c = 1; c <= N; c++) board[r][c] = Integer.parseInt(st.nextToken());
        }

        // dp 초기화
        int[][][] dp = new int[TYPE][N+1][N+1];
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                // 각 좌표마다 우유 타입별로 게산
                for(int type = 0; type < TYPE; type++){
                    // 이전 결과 중 큰 값으로 저장
                    dp[type][r][c] = Math.max(dp[type][r-1][c], dp[type][r][c-1]);
                    // 좌표 위치의 우유 종류와 type이 같은 경우 큰 값 계산
                    if(board[r][c] == type) {
                        // 마셔야하는 이전 우유
                        int prevType = type - 1;
                        if(prevType < 0) prevType = TYPE - 1;
                        // 최대값 갱신
                        dp[type][r][c] = Math.max(dp[type][r][c], Math.max(dp[prevType][r - 1][c], dp[prevType][r][c - 1]) + 1);
                        // 만약, 딸기우유가 아닌 다른 우유를 처음 마신 경우 0으로 변환
                        if (dp[type][r][c] == 1 && type > 0) dp[type][r][c] = 0;
                    }
                }
            }
        }

//        for(int[][] d: dp){
//            for(int[] a: d) System.out.println(Arrays.toString(a));
//            System.out.println("--------------");
//        }

        // 정답 반환
        sb.append(getMaxCount(dp, N));
        System.out.println(sb);
    }

    private static int getMaxCount(int[][][] dp, int n) {
        return Math.max(dp[0][n][n], Math.max(dp[1][n][n], dp[2][n][n]));
    }
}
