package _2025._05;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21923
// - DP : 상승 비행, 하강 비행에 대한 점수를 계산하고 최대값 탐색
public class _05_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_05/_05_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        // 구역 크기 입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N+1][M+1];  // 구역 초기화
        // upDP[n][m] : (n, m) 좌표에 상승하는 경우의 최대 점수
        int[][] upDP = new int[N+2][M+2];   // 상승 비행 DP
        // downDP[n][m] : (n, m) 좌표에서 하강하는 경우의 최대 점수
        int[][] downDP = new int[N+2][M+2]; // 하강 비행 DP
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            // 구역 정보 입력
            for(int m = 1; m <= M; m++) board[n][m] = Integer.parseInt(st.nextToken());
            Arrays.fill(upDP[n], -100_000_000);
            Arrays.fill(downDP[n], -100_000_000);
        }
        Arrays.fill(upDP[N+1], -100_000_000);
        Arrays.fill(downDP[N+1], -100_000_000);
        upDP[N+1][1] = 0;
        downDP[N+1][M] = 0;

        // 최하단부터 점수 계산
        for(int n = N; n > 0; n--){
            // 상승 비행 : 좌측부터 계산
            for(int m = 1; m <= M; m++){
                upDP[n][m] = Math.max(upDP[n+1][m], upDP[n][m-1]);
                upDP[n][m] += board[n][m];
            }

            // 하강 비행 : 우측부터 계산
            for(int m = M; m > 0; m--){
                downDP[n][m] = Math.max(downDP[n+1][m], downDP[n][m+1]);
                downDP[n][m] += board[n][m];
            }
        }

//        for(int[] b : board) System.out.println(Arrays.toString(b));
//        System.out.println("-----");
//        for(int[] up : upDP) System.out.println(Arrays.toString(up));
//        System.out.println("-----");
//        for(int[] down : downDP) System.out.println(Arrays.toString(down));

        // 정답 초기화
        int answer = -100_000_000;
        // - 모든 좌표 중 최대값인 경우 탐색
        for(int n = 1; n <= N; n++){
            for(int m = 1; m <= M; m++) answer = Math.max(answer, upDP[n][m] + downDP[n][m]);
        }

        // 정답 출력
        sb.append(answer);
        System.out.println(sb);
    }
}
