package _2023._202311;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1577
// - DP를 통해 해결!
// - 최소 이동 거리 (N+M)인 경우의 수! 이므로
// - do[i][j] = (왼쪽 도로에서 이동할 수 있는 경우의 수) + (위쪽 도로에서 이동할 수 있는 경우의 수)
//            = dp[i][j-1] + dp[i-1][j]
// - 단, 이동할 수 없는 도로가 있으므로 이를 확인하고 이동할 수 있는 경우만 체크
public class _11_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2023/_202311/_11_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 도로 크기 입력
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());   // 열 크기
        int M = Integer.parseInt(st.nextToken());   // 행 크기

        // 공사중인 도로의 수
        int K = Integer.parseInt(in.readLine());
        
        // 공사중인 도로
        // - [0][r][c] : (r, c)를 시작으로 하는 세로 도로 공사중 여부
        // - [1][r][c] : (r, c)를 시작으로 하는 가로 도로 공사중 여부
        boolean[][][] disableRoad = new boolean[2][M+2][N+2];
        // 공사중인 도로 입력
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(in.readLine(), " ");
            // 도로 정보 입력
            int c1 = Integer.parseInt(st.nextToken()) + 1;
            int r1 = Integer.parseInt(st.nextToken()) + 1;
            int c2 = Integer.parseInt(st.nextToken()) + 1;
            int r2 = Integer.parseInt(st.nextToken()) + 1;

            if(r1 == r2) disableRoad[1][r1][Math.min(c1, c2)] = true;
            else disableRoad[0][Math.min(r1, r2)][c1] = true;
        }

        // 이동 방향
        int[][] DELTA = new int[][] {{-1, 0}, {0, -1}};
        // dp 초기화
        long[][] dp = new long[M+2][N+2];
        // - 시작 지점을 1로 초기화
        dp[1][1] = 1L;
        // 모든 좌표에 대해 탐색
        for(int r = 1; r < dp.length; r++){
            for(int c = 1; c < dp[0].length; c++){
                // (r, c)에 연결된 도로 확인 : 왼쪽, 위쪽만 확인(최소 이동 거리만 확인하므로!)
                for(int type = 0 ; type < 2; type++){
                    // 시작 도로 좌표
                    int startR = r + DELTA[type][0];
                    int startC = c + DELTA[type][1];

                    // 이동가능한 도로인 경우
                    // - start 좌표로 이동할 수 있는 경우의 수만큼 증가!
                    if(!disableRoad[type][startR][startC]) dp[r][c] += dp[startR][startC];
                }
            }
        }
//        for(long[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }

        // 목적지에 도달하는 경우의 수 반환
        sb.append(dp[M+1][N+1]);
        System.out.println(sb);
    }
}
