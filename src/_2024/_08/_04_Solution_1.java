package _2024._08;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11909
// - DP : 이동 방향이 하, 우 2방향이므로 (1, 1)부터 차례로 탐색!
public class _04_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_08/_04_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        // 배열 크기
        int N = Integer.parseInt(in.readLine());
        int[][] map = new int[N+1][N+1];    // 배열 초기화
        int[][] dp = new int[N+1][N+1];     // DP 초기화

        // 배열 정보 입력!
        for(int r = 1; r <= N; r++){
            st = new StringTokenizer(in.readLine());
            Arrays.fill(dp[r], 1_999_999_999);
            for(int c = 1; c <= N; c++) map[r][c] = Integer.parseInt(st.nextToken());
        }

        // (1, 1) 좌표의 값을 0으로 초기화!
        dp[1][1] = 0;
        // 이동 방향 설정!
        final int[][] DELTA = new int[][] {{0, 1}, {1, 0}};
        // (1, 1)에서 (N, N)까지 진행!
        for(int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                // 마지막 좌표인 경우 종료!
                if(r == N && c == N) break;
                for(int[] d : DELTA){
                    // 다음 좌표 계산
                    int nextR = r + d[0];
                    int nextC = c + d[1];

                    // 좌표를 넘어갈 경우 패스
                    if(nextR > N || nextC > N) continue;

                    // 비용 계산!
                    int cost = map[nextR][nextC] - map[r][c];
                    if(cost < 0) cost = 0;
                    else cost++;
                    // 최소값 갱신!
                    dp[nextR][nextC] = Math.min(dp[nextR][nextC], dp[r][c] + cost);
                }
            }
//            System.out.println(Arrays.toString(dp[r]));
        }

        // 정답 반환
        sb.append(dp[N][N]);
        System.out.println(sb);
    }

}
