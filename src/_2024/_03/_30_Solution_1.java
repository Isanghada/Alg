package _2024._03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10476
// - DP : 각 갤러리를 방문할 수 있는 3가지 경우를 차례로 계산
public class _30_Solution_1 {
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2024/_03/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 미술관 길이
        int K = Integer.parseInt(st.nextToken());   // 닫아야 하는 방의 수

        // 방의 닫는 경우
        // - 0 : 왼쪽 미술관 닫기
        // - 1 : 오른쪽 미술관 닫기
        // - 2 : 모든 미술관 개방
        final int TYPE = 3;
        // DP 초기화
        // - DP[n][k][type] : n번째 줄의 미술관에서 k개의 미술관을 닫고 type일 경우의 최대값
        int[][][] DP = new int[N+1][K+1][TYPE];
        
        // 각 미술관 차례로 계산
        for(int n = 1; n <= N; n++){
            st = new StringTokenizer(in.readLine());
            // 미술관 가치 입력
            int[] v = new int[]{
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            };

            // 모든 미술관을 개방하는 경우 초기화
            DP[n][0][2] += DP[n-1][0][2] + v[0] + v[1];
            // 미술관 닫는 최대값
            int limit = Math.min(n, K);
            // 미술관을 닫는 경우 차례로 계산
            for(int k = 1; k <= limit; k++){
                // 현재 줄에서 왼쪽 미술관을 닫는 경우
                DP[n][k][0] = max(DP[n-1][k-1][0], DP[n-1][k-1][2]) + v[1];
                // 현재 줄에서 오른쪽 미술관을 닫는 경우
                DP[n][k][1] = max(DP[n-1][k-1][1], DP[n-1][k-1][2]) + v[0];
                // 현재 줄에서 모든 미술관을 개방하는 경우
                if(n != k) DP[n][k][2] = max(DP[n-1][k][0], DP[n-1][k][1], DP[n-1][k][2]) + v[0] + v[1];
            }
        }
//        for(int[][] dp : DP){
//            for(int[] d : dp){
//                System.out.println(Arrays.toString(d));
//            }
//            System.out.println("--------");
//        }

        // 정답 반환
        // - 마지막 경우에서 최대값 반환
        sb.append(max(DP[N][K][0], DP[N][K][1], DP[N][K][2]));
        System.out.println(sb);
    }
    // 가변 인자를 통한 최대값 계산 함수
    public static int max(int...values){
        int result = 0;
        for(int v : values) result = Math.max(result, v);
        return result;
    }
}