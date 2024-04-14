package _2024._04;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10710
// - DP : 각 도시로 이동하는 경우를 차례로 계산
public class _15_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_04/_15_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st= new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 최대 도시 번호
        int M = Integer.parseInt(st.nextToken());   // 이동할 수 있는 날짜

        // 도시 이동 거리
        int[] costArr = new int[N+1];
        for(int i = 1; i <= N; i++) costArr[i] = Integer.parseInt(in.readLine());

        // 날씨 정보
        int[] weatherArr = new int[M+1];
        for(int i = 1; i <= M; i++) weatherArr[i] = Integer.parseInt(in.readLine());

        // DP 초기화
        // - dp[n][m] : m 날짜까지 n번 도시로 이동하는 최소 피로도
        int[][] dp = new int[N+1][M+1];

        // 1번 도시로 이동하는 경우부터 차례로 계산
        for(int n = 1; n <= N; n++){
            dp[n][n-1] = Integer.MAX_VALUE;
            for(int m = n; m <= M; m++){
                // 이전 결과와 새로운 결과 중 최소값으로 설정
                dp[n][m] = Math.min(dp[n][m-1], dp[n-1][m-1] + costArr[n] * weatherArr[m]);
            }
        }
//        for(int[] d : dp){
//            System.out.println(Arrays.toString(d));
//        }

        // 정답 출력
        sb.append(dp[N][M]);
        System.out.println(sb.toString());
    }
}
