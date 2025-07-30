package _2025._07;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1988
// - DP : N번 구간에 잠을 선택, 미선택할 때의 경우를 갱신하여 최대값 탐색
public class _30_Solution_1 {
    static final int TYPE = 2, SELECTED = 0, UNSELECTED = 1;
    public static void main(String[] args) throws Exception {
        // 입출력 설정
        System.setIn(new FileInputStream("src/_2025/_07/_30_input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());   // 구간 개수
        int B = Integer.parseInt(st.nextToken());   // 선택 구간의 수

        // 회복량 정보
        int[] recovery = new int[N+1];
        for(int i = 1; i <= N; i++) recovery[i] = Integer.parseInt(in.readLine());

        // DP 초기화
        // - dp[b][n][type] : b번째에 n번 구간을 type(선택, 미선택)한 경우의 최대 회복량
        int[][][] dp = new int[B+1][N+1][TYPE];

        for(int n = 1; n <= N; n++){
            for(int b = 2; b <= B; b++){
                // n번을 선택하지 않는 경우
                dp[b][n][UNSELECTED] = Math.max(dp[b][n-1][UNSELECTED], dp[b][n-1][SELECTED]);
                // n번을 선택하는 경우
                dp[b][n][SELECTED] = Math.max(dp[b-1][n-1][UNSELECTED], dp[b-1][n-1][SELECTED] + recovery[n]);

                // System.out.print("[ " +dp[b][n][UNSELECTED]+", "+dp[b][n][SELECTED]+" ] | ");
            }
            // System.out.println();
        }

        // 정답 반환
        sb.append(Math.max(dp[B][N][UNSELECTED], dp[B][N][SELECTED]));
        System.out.println(sb);
    }
}